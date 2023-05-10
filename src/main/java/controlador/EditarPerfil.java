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

import modelo.DAO.Conector;
import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloEmpleado;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean error = false;
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		boolean director = false;
		if(clienteLogueado == null && empleadoLogueado == null) {
			response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
		}else {
		 Conector con  = new Conector();
		 con.conectar();
		Cliente cliente = new Cliente();
		ModeloCliente mcliente = new ModeloCliente(con);
		String tipoLogin = "ninguno";
			if(clienteLogueado != null) {
				tipoLogin = "cliente";
				cliente = clienteLogueado;
			} else if(empleadoLogueado != null) {
				ModeloEmpleado mempleado = new ModeloEmpleado(con);
				director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
				Cliente clienteAModificar = new Cliente();
				if(request.getParameter("dni") == null) {
					error = true;
				}else {
				String dni = request.getParameter("dni");
				clienteAModificar= mcliente.getCliente(dni);
				if(clienteAModificar.getDni() == "-1") {
					error = true;
				}
				cliente = clienteAModificar;
				tipoLogin = "empleado";
				}
			}
			
			ArrayList<Telefonos> telefonos = new ArrayList<>();
			telefonos = mcliente.getTelefonos(cliente.getDni());
			con.cerrar();
			if(!error) {
		String aviso = request.getParameter("aviso");
		request.setAttribute("aviso", aviso);
		request.setAttribute("director", director);
		request.setAttribute("tipoLogin", tipoLogin);
		request.setAttribute("cliente", cliente);
		request.setAttribute("telefonos", telefonos);
		request.getRequestDispatcher("editarPerfilC.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Conector con  = new Conector();
		 con.conectar();
		
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String correo = request.getParameter("correo");
		String nuevoTelefono = request.getParameter("telefono");
		String telefonos = request.getParameter("telefonos");
		String contrasena = request.getParameter("contrasena");
		String nuevaCon = request.getParameter("nuevaCon");
		String confNuevaCon = request.getParameter("confNuevaCon");
		boolean modificado = true;
		boolean cambiarpass = false;
		ModeloCliente mcliente = new ModeloCliente(con);
		Cliente clienteModificado = new Cliente();
		String passCifrada = "2be88ca4242c76e8253ac62474851065032d6833"; /*null por defecto por si algo fallase, aunque no deberia*/
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");

		if(dni != null && nombre != null && apellidos != null && correo != null) {
			clienteModificado.setNombre(nombre);
			clienteModificado.setApellidos(apellidos);
			clienteModificado.setCorreo(correo);
			clienteModificado.setDni(dni);
			
			
			if(nuevaCon != "" || contrasena != "" || confNuevaCon != "") {
				if(empleadoLogueado != null) {
					if(nuevaCon.equals(confNuevaCon)) {
						cambiarpass = true;
						passCifrada = DigestUtils.sha1Hex(nuevaCon);
					}else {
						modificado = false;
					}
				}else {
				Cliente clienteLogueado = mcliente.comprobarLogin(dni, DigestUtils.sha1Hex(contrasena));
				if(clienteLogueado.getDni() != "-1") {
					if(nuevaCon != contrasena && nuevaCon.equals(confNuevaCon)) {
						passCifrada = DigestUtils.sha1Hex(nuevaCon);
						cambiarpass = true;
					}else {
						modificado = false;
					}
				}else {
					modificado = false;
				}
				}
			}
			
			if(telefonos != null) {
				boolean eliminartel = mcliente.comprobarTelefonos(dni);
				if(!eliminartel)
					modificado = false;
			}
			
			if(nuevoTelefono != "") {
				boolean telefonodisponible = mcliente.comprobarDisponibilidadTelefono(dni, Integer.parseInt(nuevoTelefono));
				if(!telefonodisponible)
					modificado = false;
			}
			
			if(modificado) {
			modificado = mcliente.modificarUsuario(clienteModificado);
			if(modificado){
				if(cambiarpass) {
					mcliente.cambiarContrasenia(dni, passCifrada);
				}
				if(telefonos != null) {
					Telefonos telefono = new Telefonos(); 
					telefono.setDni(dni);
					telefono.setTelefono(Integer.parseInt(telefonos));
					mcliente.eliminarTel(telefono);
				}
				if(nuevoTelefono != "") {
					Telefonos telefono = new Telefonos();
					telefono.setDni(dni);
					telefono.setTelefono(Integer.parseInt(nuevoTelefono));
					mcliente.addTel(dni, telefono);
				}
				Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
				if(clienteLogueado != null) {
				clienteLogueado.setNombre(nombre);
				clienteLogueado.setApellidos(apellidos);
				clienteLogueado.setCorreo(correo);
				clienteLogueado.setDni(dni);
				session.setAttribute("clienteLogueado", clienteLogueado);
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/EditarPerfil?aviso=actualizado");
				}else if(empleadoLogueado != null) {
					con.cerrar();
					response.sendRedirect(request.getContextPath() + "/EditarPerfil?dni=" + dni + "&aviso=actualizado");
				}
			}else {
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/EditarPerfil?aviso=error");
			}
			}else {
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/EditarPerfil?aviso=error");
			}
		}else {
			con.cerrar();
			response.sendRedirect(request.getContextPath() + "/EditarPerfil?aviso=error");
		}
		
	}

}
