package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloHistorial_Cliente;
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
	 * Se utiliza para a los clientes, se llama a este servlet desde una etiqueta "a" de html de la pagina "Editar cliente" o "Gestionar Usuarios", se elimina el cliente de la bbdd y 
	 * se redirige de vuelta a la pagina anterior
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String dniAEliminar = request.getParameter("dni");
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		Conector con  = new Conector();
		 con.conectar();
		ModeloCliente mcliente = new ModeloCliente(con);
		if(clienteLogueado != null){
			boolean eliminado = mcliente.eliminarCliente(clienteLogueado.getDni());
			con.cerrar();
			if(!eliminado) {
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}else {
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
			}
			
		}else if(empleadoLogueado != null && dniAEliminar != null) {
			boolean eliminado = mcliente.eliminarCliente(dniAEliminar);
			con.cerrar();
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
