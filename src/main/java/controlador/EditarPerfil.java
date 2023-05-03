package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

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
		//TODO EL FORMULARIO NO ENVIA O EL SERVLET NO RECIVE LOS DATOS PARA ACTUALIZAR LOS PERFILES
		String dni = request.getParameter("dni"); //TODO Comprobar si envia bien!
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String correo = request.getParameter("correo");
		String nuevoTelefono = request.getParameter("telefono");
		String telefonos = request.getParameter("telefonos");
		String contrasena = request.getParameter("contrasena");
		String nuevaCon = request.getParameter("nuevaCon");
		String confNuevaCon = request.getParameter("confNuevaCon");
		boolean modificado = true;
		ModeloCliente mcliente = new ModeloCliente();
		Cliente clienteModificado = new Cliente();
		
		if(nuevoTelefono != null) { //TODO Bajarlo debajo de modificar el usuario? o no hace falta?
			Telefonos telefono = new Telefonos();
			telefono.setDni(dni);
			telefono.setTelefono(Integer.parseInt(nuevoTelefono));
			mcliente.addTel(dni, telefono);
		}
		if(dni != null && nombre != null && apellidos != null && correo != null) { //TODO eliminar telefonos y las contraseñas
			clienteModificado.setNombre(nombre);
			clienteModificado.setApellidos(apellidos);
			clienteModificado.setCorreo(correo);
			
			
			if(nuevaCon != null || contrasena != null || confNuevaCon != null) {
				Cliente clienteLogueado = mcliente.comprobarLogin(dni, contrasena); //TODO Actualizarlo a un boolean.
				if(clienteLogueado.getDni() != "-1") {
					if(nuevaCon != contrasena && nuevaCon == confNuevaCon) {
						String passCifrada = DigestUtils.sha1Hex(nuevaCon);
						mcliente.cambiarContrasenia(dni, passCifrada);
					}else {
						modificado = false;
					}
				}else {
					modificado = false;
				}
			}
			
			if(modificado) {
			/*modificado = mcliente.modificarUsuario(clienteModificado);*/
			}else {
				response.sendRedirect(request.getContextPath() + "/EditarPerfil?aviso=error"); //TODO MSG De error / modificado
			}
		}
		
	}

}
