package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloEmpleado;
import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class LoginYRegistro
 */
@WebServlet("/LoginYRegistro")
public class LoginYRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginYRegistro() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aviso = request.getParameter("aviso");
		if(aviso == null) {
			aviso = "ninguno";
		}
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String fechanacimiento = request.getParameter("fechanacimiento");
		
		
		request.setAttribute("aviso", aviso);
		request.setAttribute("dni", dni);
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellido", apellido);
		request.setAttribute("correo", correo);
		request.setAttribute("fechanacimiento", fechanacimiento);
		request.getRequestDispatcher("LoginYRegistro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		if(tipo.equals("login")) {
			String dni = request.getParameter("dni");
			String passSinEncriptar = request.getParameter("pass");
			String password = DigestUtils.sha1Hex(passSinEncriptar);
			ModeloCliente mcliente = new ModeloCliente();
			ModeloEmpleado mempleado = new ModeloEmpleado();
			Cliente cliente = new Cliente();
			Empleado empleado = new Empleado();
			cliente = mcliente.comprobarLogin(dni, password);
			HttpSession session = request.getSession();
			if(cliente.getDni() == "-1") {
				empleado = mempleado.comprobarLogin(dni, password); 
				if(empleado.getDni_Emp() == "-1") {
					response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=error");
				}else {
					session.setAttribute("empleadoLogueado", empleado);
					response.sendRedirect(request.getContextPath() + "/VerCitas");
				}
				
			}else {
				session.setAttribute("clienteLogueado", cliente);
				response.sendRedirect(request.getContextPath() + "/VerCitas");
			}
			
			
		}else if(tipo.equals("registro")){
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String correo = request.getParameter("correo");
			String fechanacimientoSinFormato = request.getParameter("fechanacimiento");
			Date fechanacimiento = null;
			String passSinEncriptar = request.getParameter("pass");
			String passConfirmar = request.getParameter("confpass");
			
			try {
				fechanacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechanacimientoSinFormato);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String password = DigestUtils.sha1Hex(passSinEncriptar);
			
			if(passSinEncriptar.equals(passConfirmar)) { //TODO Comprobar y arreglar esto!
				ModeloCliente mcliente = new ModeloCliente();
				if(mcliente.comprobarDNI(dni)) {
					response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=dniexistente");
				}else if(!mcliente.comprobarPass(passSinEncriptar)){
					response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=passnovalida");
				}else {
					mcliente.registro(dni, nombre, apellido, correo, password, fechanacimiento);
				response.sendRedirect(request.getContextPath() + "/VerCitas");
				}
			}else {
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=contrasenadiferente&dni=" + dni + "&nombre=" + nombre + "&apellido=" + apellido + "&correo=" + correo + "&fechanacimiento=" + fechanacimientoSinFormato);
			}
		}
	}

}
