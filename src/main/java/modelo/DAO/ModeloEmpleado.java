package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Empleado;

public class ModeloEmpleado {
	
	public ModeloEmpleado() {
		
	}

	public Empleado comprobarLogin(String dni, String password) {
		Empleado empleado = new Empleado();
		
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM empleados WHERE DNI_Emp= ? AND Contraseña = ?"); //TODO empleado o empleados?
			gettear.setString(1, dni);
			gettear.setString(2, password);
			ResultSet resultado=gettear.executeQuery();
			if(resultado.next()) {
				empleado.setDni_Emp(resultado.getString("DNI_Emp"));
				empleado.setNombre(resultado.getString("Nombre"));
				empleado.setApellidos(resultado.getString("Apellidos"));
				empleado.setCorreo(resultado.getString("Correo"));
				empleado.setContrasena(resultado.getString("Contraseña"));
				empleado.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
				empleado.setId_Puesto(resultado.getInt("ID_Puesto"));
				empleado.setId_Clinica(resultado.getInt("ID_Clinica"));
			}else {
				empleado.setDni_Emp("-1");
			}
			gettear.close();
			conector.cerrar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleado;
	}
	
	public Empleado getEmpleado(String dni) { //TODO Eliminar esto?
		Empleado empleado = new Empleado();
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM empleados WHERE DNI_Emp= ?");
			gettear.setString(1, dni);
			ResultSet resultado=gettear.executeQuery();
			if(resultado.next()) {
				empleado.setDni_Emp(resultado.getString("DNI_Emp"));
				empleado.setNombre(resultado.getString("Nombre"));
				empleado.setApellidos(resultado.getString("Apellidos"));
				empleado.setCorreo(resultado.getString("Correo"));
				empleado.setContrasena(resultado.getString("Contraseña"));
				empleado.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
				empleado.setId_Puesto(resultado.getInt("ID_Puesto"));
				empleado.setId_Clinica(resultado.getInt("ID_Clinica"));
			}else {
				empleado.setDni_Emp("-1");
			}
			gettear.close();
			conector.cerrar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleado;
	}
	
	public ArrayList<Empleado> getEmpleados() {
		Conector conector = new Conector();
		conector.conectar();
		ArrayList<Empleado> empleados = new ArrayList<>();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM empleados");
			ResultSet resultado=gettear.executeQuery();
			while(resultado.next()) {
				Empleado empleado = new Empleado();
				empleado.setDni_Emp(resultado.getString("DNI_Emp"));
				empleado.setNombre(resultado.getString("Nombre"));
				empleado.setApellidos(resultado.getString("Apellidos"));
				empleado.setCorreo(resultado.getString("Correo"));
				empleado.setContrasena(resultado.getString("Contraseña"));
				empleado.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
				empleado.setId_Puesto(resultado.getInt("ID_Puesto"));
				empleado.setId_Clinica(resultado.getInt("ID_Clinica"));
				empleados.add(empleado);
			}
			gettear.close();
			conector.cerrar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleados;
	}

}
