package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloHabitacion {
	
	public ModeloHabitacion() {
		
	}

	public int getCantHabitaciones(int id_Clinica) {
		int cantidadDeHabitaciones = 0;
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement getCantHabitacion;
		try {
			getCantHabitacion = conector.getCon().prepareStatement("SELECT COUNT(*) as 'Habitaciones' FROM habitaciones WHERE ID_Clinica =?");
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
	
	

}
