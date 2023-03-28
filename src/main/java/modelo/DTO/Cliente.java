package modelo.DTO;

import java.util.Date;

public class Cliente {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private Date fecha_Nacimineto;
	
	
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Date getFecha_nacimineto() {
		return fecha_Nacimineto;
	}
	public void setFecha_nacimineto(Date fecha_nacimineto) {
		this.fecha_Nacimineto = fecha_nacimineto;
	}

}
