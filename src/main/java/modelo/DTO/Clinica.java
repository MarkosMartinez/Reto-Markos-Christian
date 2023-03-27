package modelo.DTO;

public class Clinica {
	
	private int id_clinica;
	private String nombre_clinica;
	private String direccion;
	private int telefono;
	
	public Clinica() {
		
	}
	
	public int getId_clinica() {
		return id_clinica;
	}
	public void setId_clinica(int id_clinica) {
		this.id_clinica = id_clinica;
	}
	public String getNombre_clinica() {
		return nombre_clinica;
	}
	public void setNombre_clinica(String nombre_clinica) {
		this.nombre_clinica = nombre_clinica;
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
