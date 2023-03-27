package modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
	private Connection conexion;
	
	private static final String HOST = "localhost";
	private static final String BBDD = "reto-3eva";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public void conectar() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Getters Y Setters
	
	public Connection getCon() {
		return conexion;
	}

	public void setCon(Connection con) {
		this.conexion = con;
	}
	//Fin de Getters y Setters
}
