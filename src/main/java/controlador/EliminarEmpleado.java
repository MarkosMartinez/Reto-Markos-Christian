package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloEmpleado;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EliminarEmpleado
 */
@WebServlet("/EliminarEmpleado")
public class EliminarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarEmpleado() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String dniAEliminar = request.getParameter("dni");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(empleadoLogueado == null || dniAEliminar == null) {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=error");
		}else {
			ModeloEmpleado mempleado = new ModeloEmpleado();
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
		if(director) {
			boolean eliminado = mempleado.eliminarEmpleado(dniAEliminar);
			if(!eliminado) {
				response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=error");
			}else {
				response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=eliminado");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=error");
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
