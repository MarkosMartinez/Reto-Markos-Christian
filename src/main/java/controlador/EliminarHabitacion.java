package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloHabitacion;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EliminarHabitacion
 */
@WebServlet("/EliminarHabitacion")
public class EliminarHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarHabitacion() {
        super();
    }

	/**
	 * Obtiene el id de la habitacion a eliminar, y en caso de ser valido y tener permisos, elimina la habitacion.
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
				ModeloHabitacion mhabitacion = new ModeloHabitacion(con);
				String id = request.getParameter("id");
				if(id != null) {
					boolean eliminado = mhabitacion.eliminarHabitacion(Integer.parseInt(id));
					con.cerrar();
				if(eliminado) {
					response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=habitacioneliminada");
				}else {
					response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error"); 
				}
				}else {
					con.cerrar();
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
