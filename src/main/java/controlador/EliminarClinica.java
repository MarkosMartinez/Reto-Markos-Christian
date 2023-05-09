package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloCita;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EliminarClinica
 */
@WebServlet("/EliminarClinica")
public class EliminarClinica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarClinica() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		
		if(empleadoLogueado != null) {
			Conector con = new Conector();
			con.conectar();
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			if(director) {
				String id = request.getParameter("id");
				if(id != null) {
					
					ModeloCita mcita = new ModeloCita(con);
					boolean borrado = mcita.borrarCitas(Integer.parseInt(id)); //TODO Arreglar desde aqui.
					if(borrado) {
						borrado = mempleado.eliminarEmpleados(Integer.parseInt(id));
						if(borrado) {
							ModeloClinica mclinica = new ModeloClinica(con);
							borrado = mclinica.borrarClinica(Integer.parseInt(id));
						}
					}
					con.cerrar();
					if(borrado) {
						response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=clinicaeliminada");
					}else {
						response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
				}
			}else {
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error"); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
