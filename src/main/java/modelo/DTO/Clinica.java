package modelo.DTO;

public class Clinica {
	
	private int id_Clinica;
	private String nombre_Clinica;
	private String direccion;
	private int telefono;
	
	
	
	public int getId_clinica() {
		return id_Clinica;
	}
	public void setId_clinica(int id_clinica) {
		this.id_Clinica = id_clinica;
	}
	public String getNombre_clinica() {
		return nombre_Clinica;
	}
	public void setNombre_clinica(String nombre_clinica) {
		this.nombre_Clinica = nombre_clinica;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
}
