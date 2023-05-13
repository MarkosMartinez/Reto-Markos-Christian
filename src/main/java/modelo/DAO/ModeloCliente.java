package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modelo.DTO.Cliente;
import modelo.DTO.Telefonos;

public class ModeloCliente {
	private Conector con;

	public ModeloCliente(Conector con) {
		this.con = con;
	}

	/**
	 * Obtiene todos los datos del cliente gracias el dni que recibe.
	 * @param dni Este es el dni del cliente que recibe.
	 * @return devuelve un objeto de tipo cliente con los datos obtenidos.
	 */
	public Cliente getCliente(String dni){
		Cliente cliente = new Cliente();
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM clientes WHERE DNI = ?");
			gettear.setString(1, dni);
			ResultSet resultado=gettear.executeQuery();
				if(resultado.next()) {
					cliente.setDni(resultado.getString("DNI"));
					cliente.setNombre(resultado.getString("Nombre"));	
					cliente.setApellidos(resultado.getString("Apellidos"));	
					cliente.setCorreo(resultado.getString("Correo"));
					cliente.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
					
				}else {
					cliente.setDni("-1");	
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
		
	}

	/**
	 * Comprueba si el login es valido o no con los datos del cliente obtenidos.
	 * @param dni Este sera el dni cliente que recibira y tendra que comprobar.
	 * @param password Este sera las contraseña del cliente que recibira y tendra que comprobar.
	 * @return Devuelve un Cliente con los datos obtenidos. Y en caso de que algun parametro no sea valido, devuelve un cliente con el dni a -1
	 */
	public Cliente comprobarLogin(String dni, String password) {
		Cliente cliente = new Cliente();
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM clientes WHERE DNI = ? AND Contraseña = ?");
			gettear.setString(1, dni);
			gettear.setString(2, password);
			ResultSet resultado=gettear.executeQuery();
				if(resultado.next()) {
					cliente.setDni(resultado.getString("Dni"));
					cliente.setNombre(resultado.getString("Nombre"));
					cliente.setApellidos(resultado.getString("Apellidos"));
					cliente.setCorreo(resultado.getString("Correo"));
					cliente.setContrasena(resultado.getString("Contraseña"));
					cliente.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
				}else {
					cliente.setDni("-1");
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	/**
	 * Sirve para realizar un registro de un nuevo cliente con los datos recibidos.
	 * @param dni Este sera el dni que recibira del cliente que tiene que registrar.
	 * @param nombre Este sera el nombre que recibira del cliente que tiene que registrar.
	 * @param apellido Este sera el apellido que recibira del cliente que tiene que registrar.
	 * @param correo Este sera el correo que recibira del cliente que tiene que registrar.
	 * @param password Este sera el password que recibira del cliente que tiene que registrar.
	 * @param fechanacimiento Este sera la fecha de nacimiento que recibira del cliente que tiene que registrar.
	 */
	public void registro(String dni, String nombre, String apellido, String correo, String password, Date fechanacimiento) {
		PreparedStatement insertar;
		try {
			insertar = this.con.getCon().prepareStatement("INSERT INTO `clientes`(`DNI`, `Nombre`, `Apellidos`, `Correo`, `Contraseña`, `Fecha_Nacimiento`) VALUES (?, ?, ?, ?, ?, ?)");
			insertar.setString(1, dni);
			insertar.setString(2, nombre);
			insertar.setString(3, apellido);
			insertar.setString(4, correo);
			insertar.setString(5, password);
			insertar.setDate(6, new java.sql.Date(fechanacimiento.getTime()));
			insertar.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sirve para poder comprobar si el DNI que recibe, esta registrado o no.
	 * @param dni Este es el DNI que recibe y tendra que comprobar.
	 * @return Devuelve un boolean de true o false en caso de encontrar a un cliente/empleado con ese DNI o no.
	 */
	public boolean comprobarDNI(String dni) {
		boolean encontrado = false;
		
		try {
			PreparedStatement comprobardni = this.con.getCon().prepareStatement("SELECT * FROM clientes WHERE dni = ?");
			comprobardni.setString(1, dni);
			ResultSet resultado=comprobardni.executeQuery();
				if(resultado.next()) {
					encontrado = true;
				}
			comprobardni.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!encontrado) {
		try {
			PreparedStatement comprobardni = this.con.getCon().prepareStatement("SELECT * FROM empleados WHERE DNI_Emp = ?");
			comprobardni.setString(1, dni);
			ResultSet resultado=comprobardni.executeQuery();
				if(resultado.next()) {
					encontrado = true;
				}
			comprobardni.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return encontrado;
	}

	/**
	 * Sirve para añadir un telefono a un cliente.
	 * @param dni Este es el dni que recibe para saber a que cliente hay que añadir el telefono.
	 * @param oTelefono Este es el objeto con el numero de telefono que recibe.
	 */
	public void addTel(String dni, Telefonos oTelefono) {
		PreparedStatement insertar;

			try {
				insertar = this.con.getCon().prepareStatement("INSERT INTO `telefonos`(`DNI`, `Telefono`) VALUES (?, ?)");
				insertar.setString(1, dni);
				insertar.setInt(2, oTelefono.getTelefono());
				insertar.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Este metodo sirve para obtener todos los clientes.
	 * @return Devuelve un ArrayList con todos los clientes obtenidos.
	 */
	public ArrayList<Cliente> getClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM clientes");
			ResultSet resultado=gettear.executeQuery();
			while(resultado.next()) {
			Cliente cliente = new Cliente();
			cliente.setDni(resultado.getString("DNI"));
			cliente.setNombre(resultado.getString("Nombre"));	
			cliente.setApellidos(resultado.getString("Apellidos"));	
			cliente.setCorreo(resultado.getString("Correo"));
			cliente.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
			clientes.add(cliente);
			}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	/**
	 * Este metodo sirve para obtener todos los telefonos de los clientes.
	 * @return Devuelve un ArrayList con todos los telefonos obtenidos.
	 */
	public ArrayList<Telefonos> cargarTelefonos() {
		ArrayList<Telefonos> telefonos = new ArrayList<>();
		
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM telefonos");
			ResultSet resultado=gettear.executeQuery();
				while(resultado.next()) {
					Telefonos telefono = new Telefonos();
					telefono.setDni(resultado.getString("DNI"));
					telefono.setTelefono(resultado.getInt("Telefono"));	
					telefonos.add(telefono);
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return telefonos;
	}
	
	/**
	 * Este metodo sirve para devolver todos los telefonos de un cliente en especifico.
	 * 
	 * @param dni Este es el DNI del cliente que recive para poder obtener sus telefonos.
	 * @return Devuelve un ArrayList con todos los telefonos de ese cliente.
	 */
	public ArrayList<Telefonos> getTelefonos(String dni) {
		ArrayList<Telefonos> telefonos = new ArrayList<>();
		
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM telefonos WHERE DNI = ?");
			gettear.setString(1, dni);
			ResultSet resultado=gettear.executeQuery();
				while(resultado.next()) {
					Telefonos telefono = new Telefonos();
					telefono.setDni(resultado.getString("DNI"));
					telefono.setTelefono(resultado.getInt("Telefono"));	
					telefonos.add(telefono);
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return telefonos;
	}

	/**
	 * Este metodo sirve para cambiar la contraseña de un cliente.
	 * @param dni Este es el DNI del cliente al que se le cambiara la contraseña.
	 * @param passCifrada Este es la nueva contraseña ya cifrada a la que se tendra que actualizar.
	 */
	public void cambiarContrasenia(String dni, String passCifrada) {
			PreparedStatement pstModificar;
			try {
	            pstModificar = this.con.getCon().prepareStatement("UPDATE clientes SET Contraseña = ? WHERE DNI = ?;");
	            pstModificar.setString(1, passCifrada);
	            pstModificar.setString(2, dni);
	            pstModificar.execute();
	            pstModificar.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	/**
	 * Este metodo elimina el telefono de un cliente.
	 * @param telefono Este es el objeto del telefono que recive con el dni del cliente y numero a eliminar.
	 */
	public void eliminarTel(Telefonos telefono) {
			PreparedStatement pstModificar;
			try {
	            pstModificar = this.con.getCon().prepareStatement("DELETE FROM `telefonos` WHERE DNI = ? AND Telefono = ?");
	            pstModificar.setString(1, telefono.getDni());
	            pstModificar.setInt(2, telefono.getTelefono());
	            pstModificar.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * Este metodo comprueba la cantidad de telefonos que tiene un cliente para que siempre tenga 1 minimo.
	 * @param dni Este es el DNI del cliente que recive y al que le cuenta la cantidad de telefonos que tiene.
	 * @return Devuelve un boolean de true o false, en caso de que la cantidad de telefonos sea superior a uno o igual a uno.
	 */
	public boolean comprobarTelefonos(String dni) {
	    PreparedStatement pstContar;
	    ResultSet rs;
	    try {
	        pstContar = this.con.getCon().prepareStatement("SELECT COUNT(*) FROM `telefonos` WHERE DNI = ?");
	        pstContar.setString(1, dni);
	        rs = pstContar.executeQuery();
	        rs.next();
	        int cantidad = rs.getInt(1);
	        
	        return cantidad > 1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	/**
	 * Este metodo modifica los datos de un cliente.
	 * @param clienteModificado Este es el objeto de tipo cliente que recive con los nuevos datos (aunque el dni siempre sea el mismo)
	 * @return Devuelve un boolean de true o false en caso de modificar o no el cliente.
	 */
	public boolean modificarUsuario(Cliente clienteModificado) {
	    boolean modificado = false;
	    PreparedStatement pstModificar;
	    try {
	        pstModificar = this.con.getCon().prepareStatement("UPDATE clientes SET Nombre = ?, Apellidos = ?, Correo = ? WHERE DNI = ?;");
	        pstModificar.setString(1, clienteModificado.getNombre());
	        pstModificar.setString(2, clienteModificado.getApellidos());
	        pstModificar.setString(3, clienteModificado.getCorreo());
	        pstModificar.setString(4, clienteModificado.getDni());
	        int filasModificadas = pstModificar.executeUpdate();
		        if (filasModificadas > 0) {
		          modificado = true;
		        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return modificado;
	}

	/**
	 * Este metodo elimina el cliente de la Base de Datos de manera ordenada.
	 * Este es el otrden para eliminar un cliente, y en caso de que algo falle no seguira eliminandose.
	 * el historial del cliente, sus telefonos, sus citas y ya por ultimo el cliente
	 * @param dni Este es el DNI del cliente que recivira para eliminar.
	 * @return Devuelve un boolean de true o false en caso de eliminar o no el cliente satisfactoriamente.
	 */
	public boolean eliminarCliente(String dni) {
	    boolean eliminado = true;
	    
	    PreparedStatement pstEliminarHistorial;
		try {
			pstEliminarHistorial = this.con.getCon().prepareStatement("DELETE FROM `historiales_clientes` WHERE DNI = ?");
			pstEliminarHistorial.setString(1, dni);
			pstEliminarHistorial.executeUpdate();
			pstEliminarHistorial.close();
        } catch (SQLException e) {
        	eliminado = false;
            e.printStackTrace();
        }
		
			if(eliminado) {
				
			PreparedStatement pstEliminarTelefonos;
			try {
				pstEliminarTelefonos = this.con.getCon().prepareStatement("DELETE FROM `telefonos` WHERE DNI = ?");
				pstEliminarTelefonos.setString(1, dni);
				int filasAfectadas = pstEliminarTelefonos.executeUpdate();
		        if(filasAfectadas == 0) {
		        	eliminado = false;
		        }
		        pstEliminarTelefonos.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
				if(eliminado) {
					
					PreparedStatement pstEliminarCitas;
					try {
						pstEliminarCitas = this.con.getCon().prepareStatement("DELETE FROM `citas` WHERE DNI_Cliente = ?");
						pstEliminarCitas.setString(1, dni);
						pstEliminarCitas.executeUpdate();
				        pstEliminarCitas.close();
			        } catch (SQLException e) {
			        	eliminado = false;
			            e.printStackTrace();
			        }
					
						if(eliminado) {
							PreparedStatement pstEliminarCliente;
							try {
								pstEliminarCliente = this.con.getCon().prepareStatement("DELETE FROM `clientes` WHERE DNI = ?");
								pstEliminarCliente.setString(1, dni);
								int filasAfectadas = pstEliminarCliente.executeUpdate();
						        if(filasAfectadas == 0) {
						        	eliminado = false;
						        }
						        pstEliminarCliente.close();
					        } catch (SQLException e) {
					            e.printStackTrace();
					        }
						}
		
				}
			}
		return eliminado;
	}
	
	/**
	 * Este metodo comprueba si el nuevo telefono a agregar, no lo tiene ya agregado el mismo cliente.
	 * @param dni Este es dni del cliente que tendra que comrpobar
	 * @param nuevoTelefono Este el nuevo telefono que quiere añadir, pero que antes tendra que comrpobar si ya lo tiene añadido.
	 * @return Devuelve un boolean de true o false en caso de encontrar o no el nuevo telefono en el cliente.
	 */
	public boolean comprobarDisponibilidadTelefono(String dni, int nuevoTelefono) {
	    PreparedStatement pstContar;
	    ResultSet rs;
	    try {
	        pstContar = this.con.getCon().prepareStatement("SELECT COUNT(*) FROM `telefonos` WHERE DNI = ? AND Telefono = ?");
	        pstContar.setString(1, dni);
	        pstContar.setInt(2, nuevoTelefono);
	        rs = pstContar.executeQuery();
	        rs.next();
	        int cantidad = rs.getInt(1);

	        return cantidad == 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}


}
