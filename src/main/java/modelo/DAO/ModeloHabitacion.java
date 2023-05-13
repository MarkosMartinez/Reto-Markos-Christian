package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Habitacion;

public class ModeloHabitacion {
	
	private Conector con;
	
	public ModeloHabitacion(Conector con) {
		this.con = con;
	}

	/**
	 * Obtiene la cantidad de habitaciones que tiene una clinica
	 * 
	 * @param id_Clinica es el ID de la clinica de la que se quiere obtener la cantidad de habitaciones
	 * @return devuelve un "int" con el total de habitaciones de la clinica
	 */
	public int getCantHabitaciones(int id_Clinica) {
		int cantidadDeHabitaciones = 0;
		PreparedStatement getCantHabitacion;
		try {
			getCantHabitacion = this.con.getCon().prepareStatement("SELECT COUNT(*) as 'Habitaciones' FROM habitaciones WHERE ID_Clinica =?");
			getCantHabitacion.setInt(1, id_Clinica);
			ResultSet resultado=getCantHabitacion.executeQuery();
			if(resultado.next()) {
				cantidadDeHabitaciones = resultado.getInt("habitaciones");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cantidadDeHabitaciones;
	}

	/**
	 * Sirve para obtener la lista de habitaciones de una clinica con el orden especificado
	 * 
	 * @param id_Clinica es el ID de la clinica de la que se quiere obtener las habitaciones
	 * @param ordenes el orden en el que se solicita que se muestren las habitaciones (el orden va por el "numero 
	 * de habitacion") de forma ascendente o descendente 
	 * @return devuelve un ArrayList con las habitaciones de la clinica que se ha solicitado en el orden en el que
	 * se ha pedido
	 */
	public ArrayList<Habitacion> getHabitaciones(int id_Clinica, String orden) {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		
		if(orden.toUpperCase() == "ASC") {
			orden = "ASC";
		}else {
			orden = "DESC";
		}
		
		try {
			PreparedStatement pSt = this.con.getCon().prepareStatement("SELECT * FROM habitaciones WHERE ID_Clinica = ? ORDER BY Num_Habitacion " + orden);
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Habitacion habitacion = new Habitacion();
				habitacion.setId_Clinica(resultado.getInt("ID_Clinica"));
				habitacion.setEspecialidad(resultado.getString("Especialidad"));
				habitacion.setNum_Habitacion(Integer.parseInt(resultado.getString("Num_Habitacion")));
				habitacion.setId_Habitacion(Integer.parseInt(resultado.getString("ID_Habitacion")));
				habitaciones.add(habitacion);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}

	/**
	 * Sirve para eliminar una habitacion
	 * @param id es el ID de la habitacion que se va a eliminar
	 * @return si la habitacion se elimina correctamente devuelve un boolean "eliminado" con el valor "true",
	 * en caso de que suceda algun problema en la eliminacion lo devolvera con el valor false"
	 */
	public boolean eliminarHabitacion(int id) {
		 boolean eliminado = false;
		    PreparedStatement pstDelete;
		    try {
		        pstDelete = this.con.getCon().prepareStatement("DELETE FROM habitaciones WHERE ID_Habitacion = ?");
		        pstDelete.setInt(1, id);
		        eliminado = pstDelete.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return eliminado;
	}

	/**
	 * Sirve para crear una nueva habitacion 
	 * @param nuevaHabitacion es un atributo de tipo "Habitacion" que contiene todos los atributos necesarios para insertarse
	 * en la base de datos, asi como el numero de habitacion, su especialidad y el ID de la clinica a la que pertenecera
	 * @return si la habitacion se inserta correctamente devuelve un boolean "creado" con el valor "true",
	 * en caso de que suceda algun problema en la insercion lo devolvera con el valor false"
	 */
	public boolean crearHabitacion(Habitacion nuevaHabitacion) {
		 boolean creado = false;
		    PreparedStatement pstInsert;
		    try {
		    	pstInsert = this.con.getCon().prepareStatement("INSERT INTO `habitaciones`(`Num_Habitacion`, `Especialidad`, `ID_Clinica`) VALUES (?, ?, ?)");
		    	pstInsert.setInt(1, nuevaHabitacion.getNum_Habitacion());
		    	pstInsert.setString(2, nuevaHabitacion.getEspecialidad());
		    	pstInsert.setInt(3, nuevaHabitacion.getId_Clinica());
		        creado = pstInsert.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return creado;
	}

	/**
	 * Sirve para que no haya dos habitaciones con el mismo numero de habitacion en la misma clinica
	 * @param nuevaHabitacion es un atributo de tipo "Habitacion" que lleva el numeor de habitacion y el ID de la 
	 * clinica de la nueva habitacion
	 * @return si no se encuentra esa habitacion en la clinica devuelve un boolean llamado "disponible" con el
	 * valor "true", en caso de que esa habitacion ya exista en esa clinica lo devolvera con el valor false"
	 */
	public boolean comprobarDisponibilidad(Habitacion nuevaHabitacion) {
	    boolean disponible = true;

	    PreparedStatement pSt;
	    ResultSet rs;
	    try {
	        pSt = this.con.getCon().prepareStatement("SELECT * FROM `habitaciones` WHERE Num_Habitacion = ? AND ID_Clinica = ?");
	        pSt.setInt(1, nuevaHabitacion.getNum_Habitacion());
	        pSt.setInt(2, nuevaHabitacion.getId_Clinica());
	        rs = pSt.executeQuery();
	        if (rs.next()) {
	            disponible = false;
	        }
	        rs.close();
	        pSt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return disponible;
	}

	/**
	 * Elimina todas las habitaciones de la clinica que se especifique
	 * @param id es el ID de la clinica de la cual se quieren borrar todas las habitaciones
	 * @return si las habitaciones se eliminan correctamente devuelve un boolean con el valor
	 * "true", en casod e que suceda algun problema en su eliminacion lo devolvera con el 
	 * valor "false" 
	 */
	public boolean eliminarHabitaciones(int id) {
		boolean eliminado = true;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM habitaciones WHERE ID_Clinica = ?");
	        pstDelete.setInt(1, id);
	        pstDelete.execute();
	    } catch (SQLException e) {
	    	eliminado = false;
	        e.printStackTrace();
	    }

	    return eliminado;
	}

	
	

}
