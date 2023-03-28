package modelo.DTO;

import java.time.LocalTime;
import java.util.Date;

public class Realizar_Citas {

	private int id_Clinica;
	private int id_Habitacion;
	private String dni_Cliente;
	private Date fecha_Cita;
	private LocalTime hora_Cita;
	
	public int getId_Clinica() {
		return id_Clinica;
	}
	public void setId_Clinica(int id_Clinica) {
		this.id_Clinica = id_Clinica;
	}
	public int getId_Habitacion() {
		return id_Habitacion;
	}
	public void setId_Habitacion(int id_Habitacion) {
		this.id_Habitacion = id_Habitacion;
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
