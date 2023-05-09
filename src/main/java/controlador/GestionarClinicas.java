package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloHabitacion;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class GestionarClinicas
 */
@WebServlet("/GestionarClinicas")
public class GestionarClinicas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionarClinicas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(empleadoLogueado == null) {
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
		}else {
			Conector con = new Conector();
			con.conectar();
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			if(director) {
				ModeloClinica mclinica = new ModeloClinica(con);
				ModeloHabitacion mhabitacion = new ModeloHabitacion(con);
				ArrayList<Clinica> clinicas = mclinica.getClinicas();
				/*ArrayList<Habitacion> habitaciones = mhabitacion.getHabitaciones(empleadoLogueado.getId_Clinica());*/
				
				request.setAttribute("clinicas", clinicas);
				/*request.setAttribute("habitaciones", habitaciones);*/
				con.cerrar();
				request.getRequestDispatcher("listaHabitacionesClinicas.jsp").forward(request, response);
			}else {
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
