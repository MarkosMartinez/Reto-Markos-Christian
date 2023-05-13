package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Equipamiento;

public class ModeloEquipamiento {
	
	private Conector con;
	
	public ModeloEquipamiento(Conector con) {
		this.con = con;
	}

	/**
	 * Obtiene toda la lista de equipamiento de la clinica especificada que hay en la tabla "equipamiento" 
	 * 
	 * @param id_Clinica es el ID de la clinica de la cual se quiere obtener el equipamiento
	 * @return devuelve un ArrayList con todo el equipamiento botenido de la clinica que se ha especificado
	 */
	public ArrayList<Equipamiento> cargarEquipamiento(int id_Clinica) {
		ArrayList<Equipamiento> equipamiento = new ArrayList<>();
		try {
			PreparedStatement pSt = this.con.getCon().prepareStatement("SELECT * FROM equipamiento WHERE ID_Clinica = ?");
			pSt.setInt(1, id_Clinica);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Equipamiento equip = new Equipamiento();
				equip.setId_Clinica(resultado.getInt("ID_Clinica"));
				equip.setId_Equipamiento(resultado.getInt("ID_equipamiento"));
				equip.setNombre_Equipamiento(resultado.getString("Nombre_Equipamiento"));
				equip.setPrecio(resultado.getDouble("Precio"));
				equip.setStock(resultado.getInt("Stock"));
				equipamiento.add(equip);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipamiento;
	}

	/**
	 * Actualiza el stock de todo el equipamiento, tanto si se ha cambiaod el valor como si no
	 * 
	 * @param equipamiento es un ArrayList con los ID de cada equipamiento
	 * @return si se actualiza correctamente el stock del equipamiento devuelve un boolean llamado
	 * "actualizado" con el valor "true", en caso de que ocurra algun error en la actualizacion se 
	 * devolverá con el valor "false 
	 */
	public boolean actualizarTodos(ArrayList<Equipamiento> equipamiento) {
		boolean actualizado = true;
		for (Equipamiento equip : equipamiento) {
			PreparedStatement pstModificar;
			try {
	            pstModificar = this.con.getCon().prepareStatement("UPDATE equipamiento SET Stock = ? WHERE ID_Equipamiento = ?");
	            pstModificar.setInt(1, equip.getStock());
	            pstModificar.setInt(2, equip.getId_Equipamiento());
	            pstModificar.execute();
	            actualizado = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            actualizado = false;
	        }
	    }
		return actualizado;
	}
	
	/**
	 * Actualiza el stock de solo un equipamiento
	 * 
	 * @param id_Equipamiento es el ID del equipamiento del que se ha actualizado el stock
	 * @param nuevoStock es la nueva cantidad de stock del equipamiento que se ha cambiado
	 * @return si se actualiza correctamente el stock del equipamiento devuelve un boolean llamado
	 * "actualizado" con el valor "true", en caso de que ocurra algun error en la actualizacion se 
	 * devolverá con el valor "false 
	 */
	public boolean actualizarStock(int id_Equipamiento, int nuevoStock) {
		boolean actualizado = true;
			PreparedStatement pstModificar;
			try {
	            pstModificar = this.con.getCon().prepareStatement("UPDATE equipamiento SET Stock = ? WHERE ID_Equipamiento = ?;");
	            pstModificar.setInt(1, nuevoStock);
	            pstModificar.setInt(2, id_Equipamiento);
	            pstModificar.execute();
	            actualizado = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            actualizado = false;
	        }
		return actualizado;
	}

	/**
	 * Inserta nuevo equipamiento en la base de datos
	 * 
	 * @param nombre es el nombre del nuevo equipamiento
	 * @param precio es el precio del nuevo equipamiento
	 * @param stock es el stock del nuevo equipamiento
	 * @param idClinica es el ID de la clinica en la que se va a insertar el equipamiento
	 * @return si se inserta correctamente el equipamiento devuelve un boolean llamado
	 * "insertado" con el valor "true", en caso de que ocurra algun error en la insercion se 
	 * devolverá con el valor "false 
	 */
	public boolean insertar(String nombre, Double precio, int stock, int idClinica) {
			boolean insertado = true;
			PreparedStatement pstInsertar;
			try {
				pstInsertar = this.con.getCon().prepareStatement("INSERT INTO `equipamiento`(`Nombre_Equipamiento`, `Precio`, `Stock`, `ID_Clinica`) VALUES (?, ?, ?, ?)");
				pstInsertar.setString(1, nombre);
				pstInsertar.setDouble(2, precio);
				pstInsertar.setInt(3, stock);
				pstInsertar.setInt(4, idClinica);
				pstInsertar.execute();
	        } catch (SQLException e) {
	        	insertado = false;
	            e.printStackTrace();
	        }
			
			return insertado;
	}

	/**
	 * Sirve parta eliminar completamente un equipamiento
	 * 
	 * @param delete es el ID del equipamiento que se va a eliminar por completo
	 * @return si se elimina correctamente el equipamiento devuelve un boolean llamado
	 * "eliminado" con el valor "true", en caso de que ocurra algun error en la eliminacion se 
	 * devolverá con el valor "false 
	 */
	public boolean eliminarStock(int delete) {
	    boolean eliminado = false;
	    try {
	        PreparedStatement pstModificar = this.con.getCon().prepareStatement("DELETE FROM `equipamiento` WHERE ID_Equipamiento = ?");
	        pstModificar.setInt(1, delete);
	        int filasAfectadas = pstModificar.executeUpdate();
	        if(filasAfectadas > 0) {
	            eliminado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return eliminado;
	}

	/**
	 * Elimina todo el equipamiento de la clinica que se especifique
	 * 
	 * @param id ID de la clinica de la cual se va a eliminar todo el equipamiento
	 * @return si se elimina correctamente el equipamiento devuelve un boolean llamado
	 * "eliminado" con el valor "true", en caso de que ocurra algun error en la eliminacion se 
	 * devolverá con el valor "false 
	 */
	public boolean eliminarStocks(int id) {
		boolean eliminado = true;
	    PreparedStatement pstDelete;
	    try {
	        pstDelete = this.con.getCon().prepareStatement("DELETE FROM equipamiento WHERE ID_Clinica = ?");
	        pstDelete.setInt(1, id);
	        pstDelete.execute();
	    } catch (SQLException e) {
	    	eliminado = false;
	        e.printStackTrace();
	    }

	    return eliminado;
	}


}
