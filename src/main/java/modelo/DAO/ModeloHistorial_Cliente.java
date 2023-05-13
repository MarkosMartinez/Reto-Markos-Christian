package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import modelo.DTO.Historial_Cliente;

public class ModeloHistorial_Cliente {

	private Conector con;
	
	public ModeloHistorial_Cliente(Conector con) {
		this.con = con;
	}

	/**
	 * Este metodo devuelve todos los historiales de los clientes.
	 * @return Devuelve un ArrayList con todos los historiales de los clientes.
	 */
	public ArrayList<Historial_Cliente> getHistoriales() {
		ArrayList<Historial_Cliente> historiales = new ArrayList<>();
		
		PreparedStatement pSt;
		try {
			pSt = this.con.getCon().prepareStatement("SELECT * FROM historiales_clientes;");
			ResultSet resultado = pSt.executeQuery();
				while(resultado.next()) {
					Historial_Cliente historial = new Historial_Cliente();
					historial.setDNI(resultado.getString("DNI"));
					historial.setFecha_Revision(resultado.getDate("Fecha_Revision"));
					historial.setHora_Revision(LocalTime.parse(resultado.getString("Hora_Revision")));
					historial.setObservaciones(resultado.getString("Observaciones"));
					historial.setAtendido(resultado.getString("Atendido"));
					historiales.add(historial);
				}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return historiales;
	}

}
