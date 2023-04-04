package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.DTO.Cliente;

public class ModeloCliente {

	public ModeloCliente() {
		
	}

	public Cliente getCliente(String dni){
		Cliente cliente = new Cliente();
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM cliente WHERE DNI = ?");
			gettear.setString(1, dni);
			ResultSet resultado=gettear.executeQuery();
			if(resultado.next()) {
			cliente.setDni(resultado.getString("DNI"));
			cliente.setNombre(resultado.getString("Nombre"));	
			cliente.setApellidos(resultado.getString("Apellidos"));	
			cliente.setCorreo(resultado.getString("Correo"));
			cliente.setFecha_nacimiento(resultado.getDate("Fecha_Nacimiento"));
			
			}else {
				cliente.setDni("-1");	
			}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.cerrar();
		return cliente;
		
	}
	
}
