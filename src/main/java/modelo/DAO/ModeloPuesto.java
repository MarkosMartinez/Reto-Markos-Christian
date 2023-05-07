package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Puesto;

public class ModeloPuesto {
	
	public ModeloPuesto() {
		
	}

	public ArrayList<Puesto> getPuestos() {
		Conector conector = new Conector();
		conector.conectar();
		ArrayList<Puesto> puestos = new ArrayList<>();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM `puestos`");
			ResultSet resultado=gettear.executeQuery();
			while(resultado.next()) {
				Puesto puesto = new Puesto();
				puesto.setId_Puesto(resultado.getInt("ID_Puesto"));
				puesto.setNombre_Puesto(resultado.getString("Nombre_Puesto"));
				puestos.add(puesto);
			}
			gettear.close();
			conector.cerrar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return puestos;
	}

}
