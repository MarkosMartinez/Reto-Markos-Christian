package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import modelo.DTO.Historial_Cliente;

public class ModeloHistorial_Cliente {
	
	public ModeloHistorial_Cliente() {
		
	}

	public ArrayList<Historial_Cliente> getHistoriales() {
		ArrayList<Historial_Cliente> historiales = new ArrayList<>();
		Conector conector = new Conector();
		conector.conectar();
		
		PreparedStatement pSt;
		try {
			pSt = conector.getCon().prepareStatement("SELECT * FROM historial_cliente;");
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
		
		conector.cerrar();
		return historiales;
	}

}
