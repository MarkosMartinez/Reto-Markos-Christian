package modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Conector {

	private static Conector instance;

	public static Conector instance()

	{
		if (instance == null)
			instance = new Conector();
		return instance;
	}

	private Connection conexion;
	private Session session;

	public void ssh(HttpServletRequest request) {
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

	public void conectar() {
		// MySQL Connection settings
		String dbuserName = "smiling"; // mysql username
		String dbpassword = "smiling"; // mysql password
		String url = "jdbc:mysql://localhost:3306/smilingbbdd"; // connect to local end of SSL tunnel
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			// mysql database connectivity
			Class.forName(driverName);
			System.out.println("-- Mysql connect to " + url + " " + dbuserName + " " + dbpassword);
			conexion = DriverManager.getConnection(url, dbuserName, dbpassword);

			System.out.println("-- Database connection established");

			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrar() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				System.out.println("Closing Database Connection");
				conexion.close();
			}
		} catch (Exception e2) {
		}
		/* Para desconectar el SSH, Actualmente sin uso: */
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
