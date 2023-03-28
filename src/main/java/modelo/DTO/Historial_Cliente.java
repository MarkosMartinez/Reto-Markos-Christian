package modelo.DTO;

import java.util.Date;

public class Historial_Cliente {

	private String DNI;
	private Date fecha_Revision;
	private String observaciones;
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
	
	
	
}
