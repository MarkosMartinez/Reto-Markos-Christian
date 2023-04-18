package modelo.DTO;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private String contrasena;
	private Date fecha_Nacimiento;
	private ArrayList<Telefonos> telefonos;
	//TODO ArrayList de los telefonos?
	//TODO Falta añadir telefono en todos los sitios
	
	
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Date getFecha_Nacimiento() {
		return fecha_Nacimiento;
	}
	public void setFecha_Nacimiento(Date fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}
	public ArrayList<Telefonos> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(ArrayList<Telefonos> telefonos) {
		this.telefonos = telefonos;
	}

}
