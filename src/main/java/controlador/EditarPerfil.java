package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloCliente;
import modelo.DTO.Cliente;
import modelo.DTO.Empleado;
import modelo.DTO.Telefonos;

/**
 * Servlet implementation class EditarPerfil
 */
@WebServlet("/EditarPerfil")
public class EditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused") /*Para quitar el warning de clases que no se usan (aunque si se usan)*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean error = false;
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(clienteLogueado == null && empleadoLogueado == null) { //TODO El empleado no se puede editar el perfil, pero podra editar la de los usuarios?
			response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
		}else {
		Cliente cliente = new Cliente();
		ModeloCliente mcliente = new ModeloCliente();
		String tipoLogin = "ninguno";
			if(clienteLogueado != null) {
				tipoLogin = "cliente";
				cliente = clienteLogueado;
			} else if(empleadoLogueado != null) {
				Cliente clienteAModificar = new Cliente();
				if(request.getParameter("dni") == null) {
					error = true;
				}else {
				String dni = request.getParameter("dni");
				clienteAModificar= mcliente.getCliente(dni);
				cliente = clienteAModificar;
				tipoLogin = "empleado";
				}
			}
			
			ArrayList<Telefonos> telefonos = new ArrayList<>();
			telefonos = mcliente.getTelefonos(cliente.getDni());
			
			if(!error) {
		request.setAttribute("tipoLogin", tipoLogin);
		request.setAttribute("cliente", cliente);
		request.setAttribute("telefonos", telefonos);
		request.getRequestDispatcher("editarPerfil.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String correo = request.getParameter("correo");
		String nuevoTelefono = request.getParameter("telefono");
		String telefonos = request.getParameter("telefonos");
		String contrasena = request.getParameter("contrasena");
		String nuevaCon = request.getParameter("nuevaCon");
		String confNuevaCon = request.getParameter("confNuevaCon");
		
		if(nuevoTelefono != null) {
			ModeloCliente mcliente = new ModeloCliente();
			/*mcliente.addTel(dni, null);*/ //TODO comrpobar modelocliente.addTel
			
		}
		
	}

}
