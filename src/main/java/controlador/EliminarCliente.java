package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloCliente;
import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EliminarUsuario
 */
@WebServlet("/EliminarCliente")
public class EliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCliente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); //TODO Solo para clientes! Para empleados hacer otro
		String dniAEliminar = request.getParameter("dni");
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		ModeloCliente mcliente = new ModeloCliente();
		if(clienteLogueado != null){
			boolean eliminado = mcliente.eliminarCliente(clienteLogueado.getDni());
			if(!eliminado) {
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}else {
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro");//TODO ?aviso=eliminado ?
			}
			
		}else if(empleadoLogueado != null && dniAEliminar != null) {
			boolean eliminado = mcliente.eliminarCliente(dniAEliminar);
			if(!eliminado) {
				response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=error");
			}else {
				response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=eliminado");
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
