package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modelo.DTO.Empleado;

public class ModeloEmpleado {
	
	private Conector con;
	
	public ModeloEmpleado(Conector con) {
		this.con = con;
	}

	/**
	 * Este metodo comprueba el login de un empleado y devuelve el empleado logueado en caso de encontrarlo.
	 * @param dni Este es el dni del empleado al que tiene que comrpobar.
	 * @param password Esta es la contraseña del empleado que tiene que comprobar.
	 * @return Devuelve un objeto de tipo Empleado con el empleado obtenido. En caso de no encontrar ninguno, devuelve un empleado con el dni a -1.
	 */
	public Empleado comprobarLogin(String dni, String password) {
		Empleado empleado = new Empleado();

		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM empleados WHERE DNI_Emp= ? AND Contraseña = ?");
			gettear.setString(1, dni);
			gettear.setString(2, password);
			ResultSet resultado=gettear.executeQuery();
			if(resultado.next()) {
				empleado.setDni_Emp(resultado.getString("DNI_Emp"));
				empleado.setNombre(resultado.getString("Nombre"));
				empleado.setApellidos(resultado.getString("Apellidos"));
				empleado.setCorreo(resultado.getString("Correo"));
				empleado.setContrasena(resultado.getString("Contraseña"));
				empleado.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
				empleado.setId_Puesto(resultado.getInt("ID_Puesto"));
				empleado.setId_Clinica(resultado.getInt("ID_Clinica"));
			}else {
				empleado.setDni_Emp("-1");
			}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleado;
	}
	
	/**
	 * Este metodo obtiene los datos de un empleado gracias a su dni.
	 * @param dni Este es el DNI que recive para obtener sus datos.
	 * @return Devuelve un objeto de tipo Empleado con los datos obtenidos, y en caso de no encontrarlos, devuelve un empleado con el dni a -1.
	 */
	public Empleado getEmpleado(String dni) {
		Empleado empleado = new Empleado();
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM empleados WHERE DNI_Emp= ?");
			gettear.setString(1, dni);
			ResultSet resultado=gettear.executeQuery();
			if(resultado.next()) {
				empleado.setDni_Emp(resultado.getString("DNI_Emp"));
				empleado.setNombre(resultado.getString("Nombre"));
				empleado.setApellidos(resultado.getString("Apellidos"));
				empleado.setCorreo(resultado.getString("Correo"));
				empleado.setContrasena(resultado.getString("Contraseña"));
				empleado.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
				empleado.setId_Puesto(resultado.getInt("ID_Puesto"));
				empleado.setId_Clinica(resultado.getInt("ID_Clinica"));
			}else {
				empleado.setDni_Emp("-1");
			}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleado;
	}
	
	/**
	 * Este metodo obtiene todos los empleados.
	 * @return Devuelve un ArrayList con todos los empleados obtenidos.
	 */
	public ArrayList<Empleado> getEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<>();
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT * FROM empleados");
			ResultSet resultado=gettear.executeQuery();
				while(resultado.next()) {
					Empleado empleado = new Empleado();
					empleado.setDni_Emp(resultado.getString("DNI_Emp"));
					empleado.setNombre(resultado.getString("Nombre"));
					empleado.setApellidos(resultado.getString("Apellidos"));
					empleado.setCorreo(resultado.getString("Correo"));
					empleado.setContrasena(resultado.getString("Contraseña"));
					empleado.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
					empleado.setId_Puesto(resultado.getInt("ID_Puesto"));
					empleado.setId_Clinica(resultado.getInt("ID_Clinica"));
					empleados.add(empleado);
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleados;
	}

	/**
	 * Este metodo obtiene el ID del puesto del director.
	 * @param id_Puesto este es id del puesto que tendra que comprobar.
	 * @return Devuelve un boolean de true o false de si es o no un director.
	 */
	public boolean getDirector(int id_Puesto) {
		boolean director = false;
	
		PreparedStatement gettear;
		try {
			gettear = this.con.getCon().prepareStatement("SELECT Nombre_Puesto FROM puestos WHERE ID_Puesto = ?");
			gettear.setInt(1, id_Puesto);
			ResultSet resultado=gettear.executeQuery();
				if(resultado.next()) {
					if(resultado.getString("Nombre_Puesto").equals("Director"))
						director = true;
				}
			gettear.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return director;
	}

	/**
	 * Este metodo permite cambiar de clinica a un Empleado.
	 * @param dni Este es el dni del Empleado que recibe para poder actualizarlo.
	 * @param idNuevaClinica Este es el id que recibe de la nueva clinica a la que actualizara
	 */
	public void cambiarClinica(String dni, int idNuevaClinica) {
			PreparedStatement pstModificar;
			try {
	            pstModificar = this.con.getCon().prepareStatement("UPDATE empleados SET ID_Clinica = ? WHERE DNI_Emp = ?;");
	            pstModificar.setInt(1, idNuevaClinica);
	            pstModificar.setString(2, dni);
	            pstModificar.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	/**
	 * Este metodo añade un nuevo empleado a la base de datos
	 * @param dni Este es el DNI que recibira del nuevo cliente
	 * @param nombre Este es el nombre que recibira del nuevo cliente
	 * @param apellidos Este es el apellidos que recibira del nuevo cliente
	 * @param correo Este es el correo que recibira del nuevo cliente
	 * @param pass Este es el pass que recibira del nuevo cliente
	 * @param fecha_nacimiento Este es la fecha de nacimiento que recibira del nuevo cliente
	 * @param puesto Este es el puesto que recibira del nuevo cliente
	 * @param clinica Este es la clinica que recibira del nuevo cliente
	 * @return Devuelve un boolean true o false si consigue o no registrar al nuevo empleado.
	 */
	public boolean addEmpleado(String dni, String nombre, String apellidos, String correo, String pass, Date fecha_nacimiento, int puesto, int clinica) {
		boolean insertado = false;
	
		PreparedStatement insertar;
		try {
			insertar = this.con.getCon().prepareStatement("INSERT INTO `empleados`(`DNI_Emp`, `Nombre`, `Apellidos`, `Correo`, `Contraseña`, `Fecha_Nacimiento`, `ID_Puesto`, `ID_Clinica`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			insertar.setString(1, dni);
			insertar.setString(2, nombre);
			insertar.setString(3, apellidos);
			insertar.setString(4, correo);
			insertar.setString(5, pass);
			insertar.setDate(6, new java.sql.Date(fecha_nacimiento.getTime()));
			insertar.setInt(7, puesto);
			insertar.setInt(8, clinica);
			int filasAfectadas = insertar.executeUpdate();
				if (filasAfectadas == 1) {
				    insertado = true;
				} else {
				    insertado = false;
				}
			insertar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertado;
	}

	/**
	 * Este meotodo elimina un empleado
	 * @param dni Este es el DNI del empleado que recibira para eliminar.
	 * @return Devuelve un boolean true o false si consigue o no eliminar al Empleado.
	 */
	public boolean eliminarEmpleado(String dni) {
	    boolean eliminado = true;
	    
	    PreparedStatement pstEliminarEmpleado;
		try {
			pstEliminarEmpleado = this.con.getCon().prepareStatement("DELETE FROM `empleados` WHERE DNI_Emp = ?");
			pstEliminarEmpleado.setString(1, dni);
			int filasAfectadas = pstEliminarEmpleado.executeUpdate();
		        if(filasAfectadas == 0) {
		        	eliminado = false;
		        }
	        pstEliminarEmpleado.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	   
		return eliminado;
	}

	/**
	 * Este metodo sirve para cambiar la contraseña de un empleado.
	 * @param dni Este es el DNI del empleado al que se le cambiara la contraseña.
	 * @param passCifrada Este es la nueva contraseña que recibira ya cifrado.
	 */
	public void cambiarContrasenia(String dni, String passCifrada) {
			PreparedStatement pstModificar;
			try {
	            pstModificar = this.con.getCon().prepareStatement("UPDATE empleados SET Contraseña = ? WHERE DNI_Emp = ?;");
	            pstModificar.setString(1, passCifrada);
	            pstModificar.setString(2, dni);
	            pstModificar.execute();
	            pstModificar.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	/**
	 * Este metodo sirve para modificar un empleado.
	 * @param empleadoModificado Este el el empleado que recibira con los datos ya actualizados. (Excepto el DNI y la Fecha de nacimiento)
	 * @return Devuelve un boolean de true o false en caso de actualizar o no al empleado.
	 */
	public boolean modificarEmpleado(Empleado empleadoModificado) {
		    boolean modificado = false;
		    PreparedStatement pstModificar;
		    try {
		        pstModificar = this.con.getCon().prepareStatement("UPDATE `empleados` SET `Nombre`= ?,`Apellidos`= ?,`Correo`= ?,`ID_Puesto`= ?,`ID_Clinica`= ? WHERE DNI_Emp = ?");
		        pstModificar.setString(1, empleadoModificado.getNombre());
		        pstModificar.setString(2, empleadoModificado.getApellidos());
		        pstModificar.setString(3, empleadoModificado.getCorreo());
		        pstModificar.setInt(4, empleadoModificado.getId_Puesto());
		        pstModificar.setInt(5, empleadoModificado.getId_Clinica());
		        pstModificar.setString(6, empleadoModificado.getDni_Emp());
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
	 * Este metodo sirve para a los empleados cuando una clinica vaya a ser eliminada y el empleado este trabajando en el.
	 * @param id Este es el id de la clinica que recibe para poder eliminar a todos los empleados donde trabajen en esa clinica.
	 * @return Devuelve un boolean de true o false en caso de eliminar o no a los empleados.
	 */
	public boolean eliminarEmpleados(int id) {
		boolean eliminado = true;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM empleados WHERE ID_Clinica = ?");
	        pstDelete.setInt(1, id);
	        pstDelete.execute();
	    } catch (SQLException e) {
	    	eliminado = false;
	        e.printStackTrace();
	    }

	    return eliminado;
	}


}
