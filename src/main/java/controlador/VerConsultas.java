package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloCita;
import modelo.DTO.Cita;
import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class VerConsultas
 */
@WebServlet("/VerConsultas")
public class VerConsultas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerConsultas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado"); //TODO Hacer un refactor de las clases y la BBDD de plural/singular. Ej: Empleado/s...
			if(clienteLogueado == null && empleadoLogueado == null) {
				response.sendRedirect("Principal");
			}
			
			ArrayList<Cita> citas = new ArrayList<>();
			
			if(empleadoLogueado != null) {
				ModeloCita mcita = new ModeloCita();
				citas = mcita.getCitas(empleadoLogueado.getId_clinica());
			}//TODO Comprobar!
		
		request.setAttribute("citas", citas);
		request.getRequestDispatcher("verCitas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
