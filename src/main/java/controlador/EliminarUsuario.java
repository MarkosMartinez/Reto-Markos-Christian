package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EliminarUsuario
 */
@WebServlet("/EliminarUsuario")
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarUsuario() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String dniAEliminar = request.getParameter("dni");
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(clienteLogueado != null && dniAEliminar.equals(clienteLogueado.getDni())) {
			//Se elimina a si mismo y sus citas pendientes (no las pasadas? Igual genera problemas)
			
			response.sendRedirect(request.getContextPath() + "/LoginYRegistro"); //TODO ?aviso=eliminado ?
		}else if(empleadoLogueado != null) {
			//Elimina a otro cliente y sus citas.
			//TODO Comprobar que si elimina a un empleado, sea el director.
			
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=eliminado");
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
