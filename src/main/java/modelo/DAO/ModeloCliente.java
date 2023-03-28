package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloCliente {

	public ModeloCliente() {
		
	}

	public String getCliente(String dni) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*	public Usuario getUsuario(int id) throws SQLException {
		Usuario usuario = new Usuario();
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement gettear =conector.getCon().prepareStatement("SELECT * FROM usuarios WHERE id=?");
		gettear.setInt(1, id);
		ResultSet resultado=gettear.executeQuery();
		if(resultado.next()) {
		usuario.setId(resultado.getInt("id"));
		usuario.setNombre(resultado.getString("nombre_apellido"));
		usuario.setFechaNacimineto(resultado.getDate("fecha_nacimiento"));
		usuario.setPassword(resultado.getString("password"));
		}
		gettear.close();
		conector.cerrar();
		return usuario;
		
	}*/
	
}
