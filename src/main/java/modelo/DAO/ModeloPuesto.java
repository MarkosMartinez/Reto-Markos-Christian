package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Puesto;

public class ModeloPuesto {
	
	private Conector con;
	
	public ModeloPuesto(Conector con) {
		this.con = con;
	}

	/**
	 * Este metodo devuelve todos los puestos de la Base de datos.
	 * @return Devuelve un ArrayList con todos los puestos.
	 */
	public ArrayList<Puesto> getPuestos() {
		ArrayList<Puesto> puestos = new ArrayList<>();
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM `puestos`");
			ResultSet resultado=gettear.executeQuery();
				while(resultado.next()) {
					Puesto puesto = new Puesto();
					puesto.setId_Puesto(resultado.getInt("ID_Puesto"));
					puesto.setNombre_Puesto(resultado.getString("Nombre_Puesto"));
					puestos.add(puesto);
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return puestos;
	}

}
