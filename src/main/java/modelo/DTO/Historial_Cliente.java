package modelo.DTO;

import java.time.LocalTime;
import java.util.Date;

public class Historial_Cliente {

	private String DNI;
	private Date fecha_Revision;
	private LocalTime hora_Revision;
	private String observaciones;
	private String atendido;
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public Date getFecha_Revision() {
		return fecha_Revision;
	}
	public void setFecha_Revision(Date fecha_Revision) {
		this.fecha_Revision = fecha_Revision;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getAtendido() {
		return atendido;
	}
	public void setAtendido(String atendido) {
		this.atendido = atendido;
	}
	public LocalTime getHora_Revision() {
		return hora_Revision;
	}
	public void setHora_Revision(LocalTime hora_Revision) {
		this.hora_Revision = hora_Revision;
	}
	
	
	
}
