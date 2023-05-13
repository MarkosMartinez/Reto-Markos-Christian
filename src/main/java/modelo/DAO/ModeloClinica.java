package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Clinica;

public class ModeloClinica {
	
	private Conector con;
	
	public ModeloClinica(Conector con) {
		this.con  = con;
	}

		public ArrayList<Clinica> getClinicas() {
		ArrayList<Clinica> clinicas = new ArrayList<>();

		PreparedStatement pSt;
		try {
			pSt = this.con.getCon().prepareStatement("SELECT * FROM clinicas");
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Clinica clinica = new Clinica();
				clinica.setId_clinica(resultado.getInt("ID_Clinica"));
				clinica.setNombre_clinica(resultado.getString("Nombre_Clinica"));
				clinica.setDireccion(resultado.getString("Direccion"));
				clinica.setTelefono(resultado.getInt("Telefono"));

				clinicas.add(clinica);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clinicas;
		
	}

		public boolean crearClinica(Clinica nuevaClinica) {
		    boolean creado = false;
		    PreparedStatement pstInsert;
		    try {
		        pstInsert = this.con.getCon().prepareStatement("INSERT INTO `clinicas`(`Nombre_Clinica`, `Direccion`, `Telefono`) VALUES (?, ?, ?)");
		        pstInsert.setString(1, nuevaClinica.getNombre_clinica());
		        pstInsert.setString(2, nuevaClinica.getDireccion());
		        pstInsert.setInt(3, nuevaClinica.getTelefono());
		        int filasAfectadas = pstInsert.executeUpdate();
		        creado = filasAfectadas > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return creado;
		}

		public boolean borrarClinica(int id) {
			boolean eliminado = true;
		    PreparedStatement pstDelete;
		    try {
		        pstDelete = this.con.getCon().prepareStatement("DELETE FROM clinicas WHERE ID_Clinica = ?");
		        pstDelete.setInt(1, id);
		        pstDelete.execute();
		    } catch (SQLException e) {
		    	eliminado = false;
		        e.printStackTrace();
		    }

		    return eliminado;
		}


}
