package modelo.DTO;

public class Telefonos {

	private String dni;
	private int telefono;
	
	public Telefonos() {
		
	}
	
	public Telefonos(String dni, int telefono) {
		this.dni = dni;
		this.telefono = telefono;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
}
