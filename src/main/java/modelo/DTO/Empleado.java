package modelo.DTO;

import java.util.Date;

public class Empleado {
	
	private String dni_Emp;
	private String nombre;
	private String apellidos;
	private String correo;
	private String contrasena;
	private Date fecha_Nacimiento;
	private int id_Puesto;
	private int id_Clinica;
	
	
	
	public String getDni_Emp() {
		return dni_Emp;
	}
	public void setDni_Emp(String dni_Emp) {
		this.dni_Emp = dni_Emp;
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
	public int getId_Puesto() {
		return id_Puesto;
	}
	public void setId_Puesto(int id_Puesto) {
		this.id_Puesto = id_Puesto;
	}
	public int getId_Clinica() {
		return id_Clinica;
	}
	public void setId_Clinica(int id_Clinica) {
		this.id_Clinica = id_Clinica;
	}
	
}
