package modelo.DTO;

import java.util.Date;

public class Empleados {
	
	private String dni_Emp;
	private String nombre;
	private String apellidos;
	private String correo;
	private Date fecha_Nacimiento;
	private int id_Puesto;
	private int id_Clinica;
	
	
	
	public String getDni_emp() {
		return dni_Emp;
	}
	public void setDni_emp(String dni_emp) {
		this.dni_Emp = dni_emp;
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
	public Date getFecha_nacimiento() {
		return fecha_Nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_Nacimiento = fecha_nacimiento;
	}
	public int getId_puesto() {
		return id_Puesto;
	}
	public void setId_puesto(int id_puesto) {
		this.id_Puesto = id_puesto;
	}
	public int getId_clinica() {
		return id_Clinica;
	}
	public void setId_clinica(int id_clinica) {
		this.id_Clinica = id_clinica;
	}
	
}
