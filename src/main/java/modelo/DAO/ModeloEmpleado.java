package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

	public boolean getDirector(int id_Puesto) {
		Conector conector = new Conector();
		conector.conectar();
		boolean director = false;
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT Nombre_Puesto FROM puestos WHERE ID_Puesto = ?");
			gettear.setInt(1, id_Puesto);
			ResultSet resultado=gettear.executeQuery();
			if(resultado.next()) {
				if(resultado.getString("Nombre_Puesto").equals("Director"))
				director = true;
			}
			gettear.close();
			conector.cerrar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return director;
	}

	public void cambiarClinica(String dniDirector, int idNuevaClinica) {
		Conector conector = new Conector();
		conector.conectar();
			PreparedStatement pstModificar;
			try {
	            pstModificar = conector.getCon().prepareStatement("UPDATE empleados SET ID_Clinica = ? WHERE DNI_Emp = ?;");
	            pstModificar.setInt(1, idNuevaClinica);
	            pstModificar.setString(2, dniDirector);
	            pstModificar.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		conector.cerrar();
		
	}

	public boolean addEmpleado(String dni, String nombre, String apellidos, String correo, String pass, Date fecha_nacimiento, int puesto, int clinica) {
		Conector conector = new Conector();
		conector.conectar();
		boolean insertado = false;
	
		PreparedStatement insertar;
		try {
			insertar = conector.getCon().prepareStatement("INSERT INTO `empleados`(`DNI_Emp`, `Nombre`, `Apellidos`, `Correo`, `Contraseña`, `Fecha_Nacimiento`, `ID_Puesto`, `ID_Clinica`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			insertar.setString(1, dni);
			insertar.setString(2, nombre);
			insertar.setString(3, apellidos);
			insertar.setString(4, correo);
			insertar.setString(5, pass);
			insertar.setDate(6, new java.sql.Date(fecha_nacimiento.getTime()));
			insertar.setInt(7, puesto);
			insertar.setInt(8, clinica);
			int filasAfectadas = insertar.executeUpdate();
			if (filasAfectadas == 1) {
			    insertado = true;
			} else {
			    insertado = false;
			}
			insertar.close();
			conector.cerrar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertado;
	}


}
