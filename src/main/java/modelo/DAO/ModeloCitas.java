package modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
/*import java.util.Date;*/
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.sql.Time;


import modelo.DTO.Citas;

public class ModeloCitas {
	
	public ModeloCitas() {
		
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

	public ArrayList<Citas> getCitas() {
		ArrayList<Citas> citas = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement pSt;
		try {
			pSt = conector.getCon().prepareStatement("SELECT * FROM realizacitas");
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Citas cita = new Citas();
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

	public boolean disponible(int id_Clinica, java.util.Date fecha, LocalTime hora) {
		Conector conector = new Conector();
		conector.conectar();
		
		int numCitas = 0;
		boolean disponible = false;
        
		  java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
		  LocalTime horaInicio = hora.minusMinutes(30);
	      LocalTime horaFin = hora.plusMinutes(30);
         
        // Ejecutar la consulta y comprobar si hay resultados
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
        if(numCitas < 4) {
        	disponible = true;
        }
        
		return disponible;
	}

}
