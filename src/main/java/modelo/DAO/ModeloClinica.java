package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Clinica;

public class ModeloClinica {
	
	public ModeloClinica() {
	}

		public ArrayList<Clinica> getClinicas() throws SQLException {
		ArrayList<Clinica> clinicas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement pSt = conector.getCon().prepareStatement("SELECT * FROM clinica");
		ResultSet resultado = pSt.executeQuery();
		while(resultado.next()) {
			Clinica clinica = new Clinica();
			clinica.setId_clinica(resultado.getInt("ID_Clinica"));
			clinica.setNombre_clinica(resultado.getString("Nombre_Clinica"));
			clinica.setDireccion(resultado.getString("Direccion"));
			clinica.setTelefono(resultado.getInt("Telefono"));

			clinicas.add(clinica);
		}
		pSt.close();
		conector.cerrar();
		return clinicas;
		
	}

}
