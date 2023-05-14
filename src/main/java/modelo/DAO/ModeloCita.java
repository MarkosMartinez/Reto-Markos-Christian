package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;


import modelo.DTO.Cita;

public class ModeloCita {
	
	private Conector con;
	
	public ModeloCita(Conector con) {
		this.con  = con;
	}

	
	/**
	 * Recibe los atributos necesarios para insertar una cita en la tablas "citas" de la base de datos
	 * 
	 * @param id_Clinica este atributo se utilizara para saber a que clínica pertenecera la cita
	 * @param dni este es el dni del cliente que ha realizado la cita
	 * @param fecha esta fecha identificara que dia ha solicitado la cita el cliente
	 * @param hora esta hora identificara a que hora ha solicitado la cita el cliente
	 * @return Por defecto devolverá un atributo boolean llamado "creado" con el valor "true", pero en caso de que se haya intentado introducir un 
	 * dato inválido en la base de datos el valor de este cambiará a "false" y se devolverá así
	 */
	public boolean crearCita(int id_Clinica, String dni, java.util.Date fecha, LocalTime hora) {
		PreparedStatement insertar;
		boolean creado = true;
		try {
			insertar = this.con.getCon().prepareStatement("INSERT INTO citas (ID_Clinica, DNI_Cliente, Fecha_Cita, Hora_Cita) VALUES (?, ?, ?, ?);");
			insertar.setInt(1, id_Clinica);
			insertar.setString(2, dni);
			insertar.setDate(3, new java.sql.Date(fecha.getTime()));
			java.sql.Time horas = java.sql.Time.valueOf(hora);
			insertar.setObject(4, horas);
			insertar.execute();
		} catch (SQLException e) {
			creado = false;
			e.printStackTrace();
		}
		return creado;
	}

	/**
	 * Este metodo sirve para saber la cantidad de habitaciones de una clinica y de esta forma saber cuantas citas son posibles
	 * en esa clinica, con esa fecha y esa misma hora
	 * 
	 * @param id_Clinica es el ID de la clinica en la que ha solicitado el cliente la cita
	 * @param fecha es la fecha a la que ha solicitado el cliente la cita
	 * @param hora es la hora a la que ha solicitado el cliente la cita
	 * @param cantidadDeHabitaciones es la cantidad de habitaciones que hay en esa clinica
	 * @return Hay un atributo de tipo boolean llamado "disponible" que comienza con el valor "false", si la cantidad de citas con 
	 * los mismos datos superan la cantidad de habitaciones se mantendra con ese valor, en caso de que haya disponible una habitacion
	 * su valor se volvera "true" y sera este el que es devuelto
	 */
	public boolean disponible(int id_Clinica, java.util.Date fecha, LocalTime hora, int cantidadDeHabitaciones) {
		
		int numCitas = 0;
		boolean disponible = false;
        
		  java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
		  LocalTime horaInicio = hora.minusMinutes(30);
	      LocalTime horaFin = hora.plusMinutes(30);
         
             PreparedStatement pSt;
			try {
				pSt = this.con.getCon().prepareStatement("SELECT COUNT(*) FROM citas WHERE ID_Clinica =? AND Fecha_Cita = ? AND Hora_Cita BETWEEN ? AND ?");
				 pSt.setInt(1, id_Clinica);
				 pSt.setDate(2, sqlFecha);
	             pSt.setTime(3, Time.valueOf(horaInicio));
	             pSt.setTime(4, Time.valueOf(horaFin));
	            ResultSet resultado = pSt.executeQuery();
	            resultado.next();
	            numCitas = resultado.getInt(1);
	            pSt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	        if(numCitas < cantidadDeHabitaciones) {
	        	disponible = true;
	        }
	        
		return disponible;
	}

	/**
	 * Elimina una cita pendiente de un cliente
	 * 
	 * @param id_clinica es el ID de la clinica en la que el cliente tiene una cita y la cual va a ser borrada
	 * @param dni es el DNI del cliente que solicita eliminar la cita
	 * @param fecha es la fecha en la que el cliente tiene la cita
	 * @param hora es la hora en la que tiene el cliente la cita
	 * @return hay un atributo boolean llamado "eliminado" con el valor por defecto "false", si la cita se elimina correctamente
	 * su valor cambia a "true" y se devuelve, en cambio si hay algun error de permisos o de red y no se elimina la cita su valor
	 * seguira "false" y sera devuelto asi
	 */
	public boolean borrarCita(int id_clinica, String dni, java.util.Date fecha, LocalTime hora) {

	    boolean eliminado = false;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM citas WHERE ID_clinica = ? AND DNI_Cliente = ? AND Fecha_Cita = ? AND Hora_Cita =?");
	        pstDelete.setInt(1, id_clinica);
	        pstDelete.setString(2, dni);
	        pstDelete.setDate(3, new Date(fecha.getTime()));
	        pstDelete.setTime(4, Time.valueOf(hora));
	        eliminado = pstDelete.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return eliminado;
	}


	/**
	 * Obtiene las citas posteriores a la fecha y hora actuales
	 * 
	 * @param id_Clinica es el ID de la clinica de la que se obtienen las citas posteriores
	 * @return devuelve un ArrayList con todas las citas posteriores obtenidas
	 */
	public ArrayList<Cita> citasPosteriores(int id_Clinica) {
		ArrayList<Cita> citas = new ArrayList<>();
		
		try {
			PreparedStatement pSt = this.con.getCon().prepareStatement("SELECT * FROM citas WHERE ID_Clinica = ? AND (Fecha_Cita > CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) > TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return citas;
	}

	/**
	 * Obtiene las citas anteriores a la fecha y hora actuales
	 * 
	 * @param id_Clinica es el ID de la clinica de la que se obtienen las citas anteriores
	 * @return devuelve un ArrayList con todas las citas anteriores obtenidas
	 */
	public ArrayList<Cita> citasAnteriores(int id_Clinica) {
		ArrayList<Cita> citas = new ArrayList<>();
	
		try {
			PreparedStatement pSt = this.con.getCon().prepareStatement("SELECT * FROM citas WHERE ID_Clinica = ? AND (Fecha_Cita < CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) < TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return citas;
	}

	/**
	 * Obtiene todas las citas posteriores a la fecha y hora actuales de un cliente especifico
	 * 
	 * @param dni es el DNI del cliente del cual se quiere obtener las citas
	 * @return devuelve un ArrayList con todas las citas pendientes del cliente
	 */
	public ArrayList<Cita> getCitasClientePosteriores(String dni) {
		ArrayList<Cita> citas = new ArrayList<>();
	
		
		PreparedStatement pSt;
		try {
			pSt = this.con.getCon().prepareStatement("SELECT * FROM citas WHERE DNI_Cliente = ? AND (Fecha_Cita > CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) > TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setString(1, dni);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return citas;
	}
	
	/**
	 * Obtiene todas las citas anteriores a la fecha y hora actuales de un cliente especifico
	 * 
	 * @param dni es el DNI del cliente del cual se quiere obtener las citas
	 * @return devuelve un ArrayList con todas las citas anteriores del cliente
	 */
	public ArrayList<Cita> getCitasClienteAnteriores(String dni) {
		ArrayList<Cita> citas = new ArrayList<>();
		
		
		PreparedStatement pSt;
		try {
			pSt = this.con.getCon().prepareStatement("SELECT * FROM citas WHERE DNI_Cliente = ? AND (Fecha_Cita < CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) < TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setString(1, dni);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return citas;
	}


	/**
	 * sirve para eliminar todas las citas de una clinica
	 * @param id es el ID de la clinica de la cual se desean eliminar las citas
	 * @return si el borrado de las citas es correcto se devuelve un boolean "true", si es incorrecto se devuelve 
	 * como "false"
	 */
	public boolean borrarCitas(int id) {
		boolean eliminado = true;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM citas WHERE ID_Clinica = ?");
	        pstDelete.setInt(1, id);
	        pstDelete.execute();
	    } catch (SQLException e) {
	    	eliminado = false;
	        e.printStackTrace();
	    }

	    return eliminado;
	}

}
