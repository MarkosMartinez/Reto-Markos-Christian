package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Equipamiento;

public class ModeloEquipamiento {
	
	public ModeloEquipamiento() {
		
	}

	public ArrayList<Equipamiento> cargarEquipamiento(int id_Clinica) {
		ArrayList<Equipamiento> equipamiento = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		try {
			PreparedStatement pSt = conector.getCon().prepareStatement("SELECT * FROM equipamiento WHERE ID_Clinica = ?");
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
		conector.cerrar();
		return equipamiento;
	}

	public boolean actualizarTodos(ArrayList<Equipamiento> equipamiento) {
		boolean actualizado = true;
		Conector conector = new Conector();
		conector.conectar();
		for (Equipamiento equip : equipamiento) {
			PreparedStatement pstModificar;
			try {
	            pstModificar = conector.getCon().prepareStatement("UPDATE equipamiento SET Stock = ? WHERE ID_Equipamiento = ?;");
	            pstModificar.setInt(1, equip.getStock());
	            pstModificar.setInt(2, equip.getId_Equipamiento());
	            pstModificar.execute();
	            actualizado = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            actualizado = false;
	        }
	    }
		conector.cerrar();
		return actualizado;
	}
	
	public boolean actualizarStock(int id_Equipamiento, int nuevoStock) {
		boolean actualizado = true;
		Conector conector = new Conector();
		conector.conectar();
			PreparedStatement pstModificar;
			try {
	            pstModificar = conector.getCon().prepareStatement("UPDATE equipamiento SET Stock = ? WHERE ID_Equipamiento = ?;");
	            pstModificar.setInt(1, nuevoStock);
	            pstModificar.setInt(2, id_Equipamiento);
	            pstModificar.execute();
	            actualizado = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            actualizado = false;
	        }
		conector.cerrar();
		return actualizado;
	}

}