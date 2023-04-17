package modelo.DTO;

import java.time.LocalTime;
import java.util.Date;

public class Cita {

	private int id_Clinica;
	private String dni_Cliente;
	private Date fecha_Cita;
	private LocalTime hora_Cita;
	
	public int getId_Clinica() {
		return id_Clinica;
	}
	public void setId_Clinica(int id_Clinica) {
		this.id_Clinica = id_Clinica;
	}
	public String getDni_Cliente() {
		return dni_Cliente;
	}
	public void setDni_Cliente(String dni_Cliente) {
		this.dni_Cliente = dni_Cliente;
	}
	public Date getFecha_Cita() {
		return fecha_Cita;
	}
	public void setFecha_Cita(Date fecha_Cita) {
		this.fecha_Cita = fecha_Cita;
	}
	public LocalTime getHora_Cita() {
		return hora_Cita;
	}
	public void setHora_Cita(LocalTime hora_Cita) {
		this.hora_Cita = hora_Cita;
	}
	
	
	
}
