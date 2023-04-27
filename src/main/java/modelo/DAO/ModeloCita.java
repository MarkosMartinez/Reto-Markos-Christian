package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;


import modelo.DTO.Cita;

public class ModeloCita {
	
	public ModeloCita() {
		
	}

	public void crearCita(int id_Clinica, String dni, java.util.Date fecha, LocalTime hora) {
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement insertar;
		try {
			insertar = conector.getCon().prepareStatement("INSERT INTO realizacitas (ID_Clinica, DNI_Cliente, Fecha_Cita, Hora_Cita) VALUES (?, ?, ?, ?);");
			insertar.setInt(1, id_Clinica);
			insertar.setString(2, dni);
			insertar.setDate(3, new java.sql.Date(fecha.getTime()));
			java.sql.Time horas = java.sql.Time.valueOf(hora);
			insertar.setObject(4, horas);
			insertar.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.cerrar();	
		
	}

	public ArrayList<Cita> getCitas(int id_Clinica) { //TODO Eliminar esto?
		ArrayList<Cita> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		try {
			PreparedStatement pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas WHERE ID_Clinica = ?");
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				cita.setAtendido(resultado.getString("Atendido"));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.cerrar();
		return citas;
	}

	public boolean disponible(int id_Clinica, java.util.Date fecha, LocalTime hora, int cantidadDeHabitaciones) {
		Conector conector = new Conector();
		conector.conectar();
		
		int numCitas = 0;
		boolean disponible = false;
        
		  java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
		  LocalTime horaInicio = hora.minusMinutes(30);
	      LocalTime horaFin = hora.plusMinutes(30);
         
             PreparedStatement pSt;
			try {
				pSt = conector.getCon().prepareStatement("SELECT COUNT(*) FROM realizacitas WHERE ID_Clinica =? AND Fecha_Cita = ? AND Hora_Cita BETWEEN ? AND ?");
				 pSt.setInt(1, id_Clinica);
				 pSt.setDate(2, sqlFecha);
	             pSt.setTime(3, Time.valueOf(horaInicio));
	             pSt.setTime(4, Time.valueOf(horaFin));
	            ResultSet resultado = pSt.executeQuery();
	            resultado.next();
	            numCitas = resultado.getInt(1);
	            pSt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conector.cerrar();
		
        if(numCitas < cantidadDeHabitaciones) {
        	disponible = true;
        }
        
		return disponible;
	}

	public ArrayList<Cita> getCitasCliente(String dni) { //TODO Eliminar esto?
		ArrayList<Cita> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		
		PreparedStatement pSt;
		try {
			pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas WHERE DNI_Cliente = ?");
			pSt.setString(1, dni);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				cita.setAtendido(resultado.getString("Atendido"));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conector.cerrar();
		return citas;
	}

	public boolean borrarCita(int id_clinica, String dni, java.util.Date fecha, LocalTime hora) {
	    Conector conector = new Conector();
	    conector.conectar();
	    boolean eliminado = false;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = conector.getCon().prepareStatement("DELETE FROM realizacitas WHERE ID_clinica = ? AND DNI_Cliente = ? AND Fecha_Cita = ? AND Hora_Cita =?");
	        pstDelete.setInt(1, id_clinica);
	        pstDelete.setString(2, dni);
	        pstDelete.setDate(3, new Date(fecha.getTime()));
	        pstDelete.setTime(4, Time.valueOf(hora));
	        eliminado = pstDelete.executeUpdate() > 0; // Comprueba si se ha eliminado alguna fila
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    conector.cerrar();
	    return eliminado;
	}


	public ArrayList<Cita> citasPosteriores(int id_Clinica) {
		ArrayList<Cita> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		try {
			PreparedStatement pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas WHERE ID_Clinica = ? AND (Fecha_Cita > CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) > TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.cerrar();
		return citas;
	}

	public ArrayList<Cita> citasAnteriores(int id_Clinica) {
		ArrayList<Cita> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		try {
			PreparedStatement pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas WHERE ID_Clinica = ? AND (Fecha_Cita < CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) < TIME(NOW())))ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				cita.setAtendido(resultado.getString("Atendido"));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.cerrar();
		return citas;
	}

	public ArrayList<Cita> getCitasClientePosteriores(String dni) {
		ArrayList<Cita> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		
		PreparedStatement pSt;
		try {
			pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas WHERE DNI_Cliente = ? AND (Fecha_Cita > CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) > TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setString(1, dni);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conector.cerrar();
		return citas;
	}
	
	public ArrayList<Cita> getCitasClienteAnteriores(String dni) {
		ArrayList<Cita> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		
		PreparedStatement pSt;
		try {
			pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas WHERE DNI_Cliente = ? AND (Fecha_Cita < CURDATE() OR (Fecha_Cita = CURDATE() AND TIME(Hora_Cita) < TIME(NOW()))) ORDER BY Fecha_Cita, Hora_Cita;");
			pSt.setString(1, dni);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Cita cita = new Cita();
				cita.setId_Clinica(resultado.getInt("ID_Clinica"));
				cita.setDni_Cliente(resultado.getString("DNI_Cliente"));
				cita.setFecha_Cita(resultado.getDate("Fecha_Cita"));
				cita.setHora_Cita(LocalTime.parse(resultado.getString("Hora_Cita")));
				cita.setAtendido(resultado.getString("Atendido"));
				citas.add(cita);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conector.cerrar();
		return citas;
	}

}
