package modelo.DTO;

public class Equipamiento {
	
	private int id_Equipamiento;
	private String nombre_Equipamiento;
	private double precio;
	private int stock;
	private int id_Clinica;
	
	
	
	public int getId_Equipamiento() {
		return id_Equipamiento;
	}
	public void setId_Equipamiento(int id_Equipamiento) {
		this.id_Equipamiento = id_Equipamiento;
	}
	public String getNombre_Equipamiento() {
		return nombre_Equipamiento;
	}
	public void setNombre_Equipamiento(String nombre_Equipamiento) {
		this.nombre_Equipamiento = nombre_Equipamiento;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getId_Clinica() {
		return id_Clinica;
	}
	public void setId_Clinica(int id_Clinica) {
		this.id_Clinica = id_Clinica;
	}
	
}
