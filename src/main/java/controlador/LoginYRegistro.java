package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		HttpSession session = request.getSession();
		session.invalidate();
		String aviso = request.getParameter("aviso");
		String dni = request.getParameter("dni");
		String dnilogin = request.getParameter("dnilogin");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String fechanacimiento = request.getParameter("fechanacimiento");
		int telefono;
		if(request.getParameter("telefono") == null) {
			telefono = -1;
		}else {
		telefono = Integer.parseInt(request.getParameter("telefono"));
		}
		
		request.setAttribute("dnilogin", dnilogin);
		request.setAttribute("aviso", aviso);
		request.setAttribute("dni", dni);
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellido", apellido);
		request.setAttribute("correo", correo);
		request.setAttribute("telefono", telefono);
		request.setAttribute("fechanacimiento", fechanacimiento);
		request.getRequestDispatcher("LoginYRegistro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
			Conector con  = new Conector();
			con.conectar();
		if(tipo.equals("login")) {
			String dni = request.getParameter("dni");
			String passSinEncriptar = request.getParameter("pass");
			String password = DigestUtils.sha1Hex(passSinEncriptar);
			
			ModeloCliente mcliente = new ModeloCliente(con);
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			Cliente cliente = new Cliente();
			Empleado empleado = new Empleado();
			cliente = mcliente.comprobarLogin(dni, password);
			HttpSession session = request.getSession();
			if(cliente.getDni() == "-1") {
				empleado = mempleado.comprobarLogin(dni, password); 
				if(empleado.getDni_Emp() == "-1") {
					con.cerrar();
					response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=error&dnilogin=" + dni);
				}else {
					session.setAttribute("empleadoLogueado", empleado);
					con.cerrar();
					response.sendRedirect(request.getContextPath() + "/VerCitas");
				}
				
			}else {
				session.setAttribute("clienteLogueado", cliente);
				con.cerrar();
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
			int telefono = Integer.parseInt(request.getParameter("telefono"));
			HttpSession session = request.getSession();
			
			try {
				fechanacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechanacimientoSinFormato);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String password = DigestUtils.sha1Hex(passSinEncriptar);
			ModeloCliente mcliente = new ModeloCliente(con);
			if(passSinEncriptar.equals(passConfirmar)) {
				if(mcliente.comprobarDNI(dni)) {
					response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=dniexistente");
				}else {
					mcliente.registro(dni, nombre, apellido, correo, password, fechanacimiento);
					
					Cliente cliente = new Cliente();
					ArrayList<Telefonos> telefonos = new ArrayList<>();
					Telefonos oTelefono = new Telefonos(dni, telefono);
					telefonos.add(oTelefono);
					mcliente.addTel(dni, oTelefono);
					
					cliente.setDni(dni);
					cliente.setNombre(nombre);
					cliente.setApellidos(apellido);
					cliente.setCorreo(correo);
					cliente.setContrasena(password);
					cliente.setFecha_Nacimiento(fechanacimiento);
					cliente.setTelefonos(telefonos);
					
					session.setAttribute("clienteLogueado", cliente);
					response.sendRedirect(request.getContextPath() + "/VerCitas");
				}
				
			}else {
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro?aviso=contrasenanovalida&dni=" + dni + "&nombre=" + nombre + "&apellido=" + apellido + "&correo=" + correo + "&telefono=" + telefono + "&fechanacimiento=" + fechanacimientoSinFormato);
			}
		}
	}

}
