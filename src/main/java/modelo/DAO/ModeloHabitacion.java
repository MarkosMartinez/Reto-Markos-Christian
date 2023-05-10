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
