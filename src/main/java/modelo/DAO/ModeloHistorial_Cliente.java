package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import modelo.DTO.Historial_Cliente;

public class ModeloHistorial_Cliente {

	private Conector con;
	
	public ModeloHistorial_Cliente(Conector con) {
		this.con = con;
	}

	/**
	 * Este metodo devuelve todos los historiales de los clientes.
	 * @return Devuelve un ArrayList con todos los historiales de los clientes.
	 */
	public ArrayList<Historial_Cliente> getHistoriales() {
		ArrayList<Historial_Cliente> historiales = new ArrayList<>();
		
		PreparedStatement pSt;
		try {
			pSt = this.con.getCon().prepareStatement("SELECT * FROM historiales_clientes;");
			ResultSet resultado = pSt.executeQuery();
				while(resultado.next()) {
					Historial_Cliente historial = new Historial_Cliente();
					historial.setDNI(resultado.getString("DNI"));
					historial.setFecha_Revision(resultado.getDate("Fecha_Revision"));
					historial.setHora_Revision(LocalTime.parse(resultado.getString("Hora_Revision")));
					historial.setObservaciones(resultado.getString("Observaciones"));
					historial.setAtendido(resultado.getString("Atendido"));
					historiales.add(historial);
				}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return historiales;
	}

	/**
	 * Este metodo evita que se dupliquen los historiales de los clientes.
	 * @param editardni Este es el dni del cliente que tendra que comprobar.
	 * @param editarfecha Este es la fecha de la cita del cliente que tendra que comprobar.
	 * @param editarhora Este es la hora de la cita del cliente que tendra que comprobar.
	 * @return Devuelve un boolean de true o false en caso de eliminar u obtener un error al intentar eliminarlo.
	 */
	public Boolean evitarDuplicado(String editardni, Date editarfecha, LocalTime editarhora) {
		boolean eliminado = true;
	    PreparedStatement pstDelete;
	    Time hora = Time.valueOf(editarhora);
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM historiales_clientes WHERE DNI = ? AND Fecha_Revision = ? AND Hora_Revision = ?");
	        pstDelete.setString(1, editardni);
	        pstDelete.setDate(2, new java.sql.Date(editarfecha.getTime()));
	        pstDelete.setTime(3, hora);
	        pstDelete.execute();
	    } catch (SQLException e) {
	    	eliminado = false;
	        e.printStackTrace();
	    }

	    return eliminado;
	}
	
	/**
	 * Obtiene los datos e una cita anterior junto al empleado que ha antendido la cita y un informe escrito por este mismo y lo
	 * inserta en la tabla "historiales_clientes" de la base de datos
	 * 
	 * @param editardni es el DNI del cliente que ha acudido a la cita
	 * @param editarfecha es la fecha en la que se llevo a cabo la cita
	 * @param editarhora es la hora a la que se llevo a cabo la cita
	 * @param editarempleado es el DNI del empleado que atendio la cita
	 * @param informe es el informe escrito por el empleado en relacion a la cita
	 * @param ID_Clinica es la clinica en la que que se llevo a cabo la cita
	 * @return hay un atributo de tipo "boolean" con el valor por defecto "false", si la insercion de los datos en la tabla historiales_clientes
	 * es correcta su valor cambiara a "true" y ser a devuelto con ese valor, en caso de que ocurra algun error su valor se mantendra en "false"
	 * y sera devuuelto asi
	 */
	public Boolean insertarCita(String editardni, java.util.Date editarfecha, LocalTime editarhora, String editarempleado, String informe, int ID_Clinica) {
		boolean insertado = true;
		Time hora = Time.valueOf(editarhora);
		
		PreparedStatement pSt;
		
		try {
			pSt = this.con.getCon().prepareStatement("INSERT INTO historiales_clientes(DNI, Fecha_Revision, Hora_Revision, Observaciones, Atendido, ID_Clinica) VALUES (?, ?, ?, ?, ?, ?)");
			pSt.setString(1, editardni);
			pSt.setDate(2, new java.sql.Date(editarfecha.getTime()));			
			pSt.setTime(3, hora);
			pSt.setString(4, informe);
			pSt.setString(5, editarempleado);
			pSt.setInt(6, ID_Clinica);
			pSt.executeUpdate();
		}catch (SQLException e) {
			insertado = false;
			e.printStackTrace();
		}
		
		return insertado;
	}

	/**
	 * Este metodo elimina todos los historiales de las citas en una clinica en concreto.
	 * @param idClinica Este es el id de la clinica donde eliminara los historiales.
	 * @return Devuelve un boolean true o false en caso de conseguir eliminar u obtener un error.
	 */
	public boolean eliminarHistoriales(int idClinica) {
		boolean eliminado = true;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM historiales_clientes WHERE ID_Clinica = ?");
	        pstDelete.setInt(1, idClinica);
	        pstDelete.execute();
	    } catch (SQLException e) {
	    	eliminado = false;
	        e.printStackTrace();
	    }

	    return eliminado;
	}

}
