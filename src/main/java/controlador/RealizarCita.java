package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DTO.Clinica;

/**
 * Servlet implementation class realizarCita
 */
@WebServlet("/realizarCita")
public class RealizarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarCita() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Clinica> clinicas = new ArrayList<>();
		ModeloClinica mclinica = new ModeloClinica();
		try {
			clinicas = mclinica.getClinicas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("clinicas", clinicas);
		//TODO a la hora de ver la listya, mostrar tambien el telefono
		request.getRequestDispatcher("realizarCita.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_Clinica = Integer.parseInt(request.getParameter("ID_Clinica"));
		String dni = request.getParameter("dni");
		ModeloCliente mcliente = new ModeloCliente();
		String dnibbdd = mcliente.getCliente(dni);
		
		doGet(request, response);
	}

}
