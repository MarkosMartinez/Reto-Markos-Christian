package modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Conector {

	private Connection conexion;
	private Session session;

	/**
	 * Sirve para conectarse al SSH y en caso de estar ya conectado, evitar volver a conectarse.
	 * En Docker, el SSH no es necesario ya que conectamos directamente a la base de datos.
	 * @param request
	 */
	public void ssh(HttpServletRequest request) {
		// Skip SSH in Docker environment
		String dockerEnv = System.getenv("DOCKER_ENV");
		if ("true".equals(dockerEnv)) {
			System.out.println("-- Docker environment detected, skipping SSH tunnel");
			return;
		}
		
		HttpSession sessionssh = request.getSession();
		if(sessionssh.getAttribute("ssh") == null) {
			String host = "91.200.117.27"; // Remote host to connect to
			String user = "1daw3"; // Remote shell username
			String password = "1daw3"; // Remote shell password
			int lport = 3306; // Local port to create
			int rport = 3306; // Destination port
			String rhost = "192.168.100.170"; // Destination address
	
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			try {
				session = jsch.getSession(user, host, 10022);
			} catch (JSchException e) {
				e.printStackTrace();
			}
			session.setPassword(password);
			session.setConfig(config);
			try {
				session.connect();
			} catch (JSchException e) {
				e.printStackTrace();
			}
			System.out.println("-- SSH connection successful");
			int assinged_port = 0;
			try {
				assinged_port = session.setPortForwardingL(lport, rhost, rport);
			} catch (JSchException e) {
				e.printStackTrace();
			}
			System.out.println("-- localhost:" + assinged_port + " tunneled to " + host + ":" + rport);
		}
	}

	/**
	 * Sirve para conectarse a la base de datos en MySQL.
	 * Soporta variables de entorno para configuración Docker.
	 */
	public void conectar() {
		// Configuración de base de datos - soporta variables de entorno para Docker
		String dbHost = System.getenv("DB_HOST");
		String dbPort = System.getenv("DB_PORT");
		String dbName = System.getenv("DB_NAME");
		String dbuserName = System.getenv("DB_USER");
		String dbpassword = System.getenv("DB_PASSWORD");
		
		// Valores por defecto si no están las variables de entorno (ambiente original)
		if (dbHost == null) dbHost = "localhost";
		if (dbPort == null) dbPort = "3306";
		if (dbName == null) dbName = "smilingbbdd";
		if (dbuserName == null) dbuserName = "smiling";
		if (dbpassword == null) dbpassword = "smiling";
		
		String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Europe/Madrid";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			// mysql database connectivity
			Class.forName(driverName);
			System.out.println("-- Conectando a MySQL: " + url + " con usuario: " + dbuserName);
			conexion = DriverManager.getConnection(url, dbuserName, dbpassword);

			System.out.println("-- Conexión a base de datos establecida correctamente");
		} catch (Exception e) {
			System.err.println("Error al conectar a la base de datos:");
			e.printStackTrace();
		}
	}

	/**
	 * Sirve para desconectarse de la Base de Datos.
	 */
	public void cerrar() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				/*System.out.println("Closing Database Connection");*/
				conexion.close();
			}
		} catch (Exception e2) {
		}
		/* Para desconectar el SSH (SIN USO)*/
		/*
		 * if (session != null && session.isConnected()) {
		 * System.out.println("Closing SSH Connection"); session.disconnect(); }
		 */
	}

	// Getters Y Setters

	public Connection getCon() {
		return conexion;
	}

	public void setCon(Connection con) {
		this.conexion = con;
	}
	// Fin de Getters y Setters
}
