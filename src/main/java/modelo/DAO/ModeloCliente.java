package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modelo.DTO.Cliente;
import modelo.DTO.Telefonos;

public class ModeloCliente {

	public ModeloCliente() {
		
	}

	public Cliente getCliente(String dni){
		Cliente cliente = new Cliente();
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM cliente WHERE DNI = ?");
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
		conector.cerrar();
		return cliente;
		
	}

	public Cliente comprobarLogin(String dni, String password) {
		Cliente cliente = new Cliente();
		
		Conector conector = new Conector();
		conector.conectar();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM cliente WHERE DNI = ? AND Contraseña = ?");
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
		conector.cerrar();
		return cliente;
	}
	
	public void registro(String dni, String nombre, String apellido, String correo, String password, Date fechanacimiento) {
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement insertar;
		try {
			insertar = conector.getCon().prepareStatement("INSERT INTO `cliente`(`DNI`, `Nombre`, `Apellidos`, `Correo`, `Contraseña`, `Fecha_Nacimiento`) VALUES (?, ?, ?, ?, ?, ?)");
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
		conector.cerrar();	
	}

	public boolean comprobarDNI(String dni) {
		boolean encontrado = false;
		Conector conector = new Conector();
		conector.conectar();
		
		try {
			PreparedStatement comprobardni = conector.getCon().prepareStatement("SELECT * FROM cliente WHERE dni = ?");
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
			PreparedStatement comprobardni = conector.getCon().prepareStatement("SELECT * FROM empleados WHERE DNI_Emp = ?");
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
		
		
		conector.cerrar();
		return encontrado;
	}

	public void addTel(String dni, Telefonos oTelefono) {
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement insertar;

			try {
				insertar = conector.getCon().prepareStatement("INSERT INTO `telefonos`(`DNI`, `Telefono`) VALUES (?, ?)");
				insertar.setString(1, dni);
				insertar.setInt(2, oTelefono.getTelefono());
				insertar.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		conector.cerrar();	
	}

	public ArrayList<Cliente> getClientes() {
		Conector conector = new Conector();
		conector.conectar();
		ArrayList<Cliente> clientes = new ArrayList<>();
	
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM cliente");
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
		conector.cerrar();
		return clientes;
	}

	public ArrayList<Telefonos> cargarTelefonos() {
		Conector conector = new Conector();
		conector.conectar();
		ArrayList<Telefonos> telefonos = new ArrayList<>();
		
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM telefonos");
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
		conector.cerrar();
		
		return telefonos;
	}
	
	public ArrayList<Telefonos> getTelefonos(String dni) {
		Conector conector = new Conector();
		conector.conectar();
		ArrayList<Telefonos> telefonos = new ArrayList<>();
		
		PreparedStatement gettear;
		try {
			gettear = conector.getCon().prepareStatement("SELECT * FROM telefonos WHERE DNI = ?");
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
		conector.cerrar();
		
		return telefonos;
	}

	public void cambiarContrasenia(String dni, String passCifrada) {
		Conector conector = new Conector();
		conector.conectar();
			PreparedStatement pstModificar;
			try {
	            pstModificar = conector.getCon().prepareStatement("UPDATE cliente SET Contraseña = ? WHERE DNI = ?;");
	            pstModificar.setString(1, passCifrada);
	            pstModificar.setString(2, dni);
	            pstModificar.execute();
	            pstModificar.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		conector.cerrar();
		
	}

	public void eliminarTel(Telefonos telefono) {
		Conector conector = new Conector();
		conector.conectar();
			PreparedStatement pstModificar;
			try {
	            pstModificar = conector.getCon().prepareStatement("DELETE FROM `telefonos` WHERE DNI = ? AND Telefono = ?");
	            pstModificar.setString(1, telefono.getDni());
	            pstModificar.setInt(2, telefono.getTelefono());
	            pstModificar.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		conector.cerrar();
	}

	public boolean modificarUsuario(Cliente clienteModificado) {
	    Conector conector = new Conector();
	    conector.conectar();
	    boolean modificado = false;
	    PreparedStatement pstModificar;
	    try {
	        pstModificar = conector.getCon().prepareStatement("UPDATE cliente SET Nombre = ?, Apellidos = ?, Correo = ? WHERE DNI = ?;");
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
	    conector.cerrar();
	    return modificado;
	}

	public boolean eliminarCliente(String dni) {
		Conector conector = new Conector();
	    conector.conectar();
	    boolean eliminado = true;
	    
	    PreparedStatement pstEliminarCitas;
		try {
			pstEliminarCitas = conector.getCon().prepareStatement("DELETE FROM `realizacitas` WHERE DNI_Cliente = ?");
			pstEliminarCitas.setString(1, dni);
			pstEliminarCitas.executeUpdate();
	        pstEliminarCitas.close();
        } catch (SQLException e) {
        	eliminado = false;
            e.printStackTrace();
        }
		
		if(eliminado) {
			
		PreparedStatement pstEliminarTelefonos;
		try {
			pstEliminarTelefonos = conector.getCon().prepareStatement("DELETE FROM `telefonos` WHERE DNI = ?");
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
			
			PreparedStatement pstEliminarHistorial;
			try {
				pstEliminarHistorial = conector.getCon().prepareStatement("DELETE FROM `historial_cliente` WHERE DNI = ?");
				pstEliminarHistorial.setString(1, dni);
				pstEliminarHistorial.executeUpdate();
				pstEliminarHistorial.close();
	        } catch (SQLException e) {
	        	eliminado = false;
	            e.printStackTrace();
	        }
			
			if(eliminado) {
				PreparedStatement pstEliminarCliente;
				try {
					pstEliminarCliente = conector.getCon().prepareStatement("DELETE FROM `cliente` WHERE DNI = ?");
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
		conector.cerrar();
		return eliminado;
	}

}
