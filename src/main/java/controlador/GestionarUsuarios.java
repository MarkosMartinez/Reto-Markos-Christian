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

import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloPuesto;
import modelo.DTO.Cliente;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;
import modelo.DTO.Puesto;

/**
 * Servlet implementation class GestionarUsuarios
 */
@WebServlet("/GestionarUsuarios")
public class GestionarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionarUsuarios() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(empleadoLogueado == null) {
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
		}else {
			String visualizar = request.getParameter("v");
			if(visualizar == null) {
				visualizar = "cliente";
			}
			ModeloEmpleado mempleado = new ModeloEmpleado();
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			String aviso = request.getParameter("aviso");
			request.setAttribute("aviso", aviso);
			request.setAttribute("director", director);
			request.setAttribute("visualizar", visualizar);
			if(!visualizar.equals("emp")) {
				ModeloCliente mcliente = new ModeloCliente();
				ArrayList<Cliente> clientes = new ArrayList<>();
			
				clientes = mcliente.getClientes();
			
				request.setAttribute("clientes", clientes);
				request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
			}else {
				if(director) {
					ModeloClinica mclinica = new ModeloClinica();
					ModeloPuesto mpuesto = new ModeloPuesto();
					ArrayList<Empleado> empleados = new ArrayList<>();
					ArrayList<Clinica> clinicas = new ArrayList<>();
					ArrayList<Puesto> puestos = new ArrayList<>();
					
					puestos = mpuesto.getPuestos();
					clinicas = mclinica.getClinicas();
					empleados = mempleado.getEmpleados();
					request.setAttribute("puestos", puestos);
					request.setAttribute("empleadoLogueado", empleadoLogueado);
					request.setAttribute("empleados", empleados);
					request.setAttribute("clinicas", clinicas);
					request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
				}else {
					response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=error");
				}
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcionGestion = request.getParameter("opcionGestion");
		String tipo = request.getParameter("tipo");
		if(tipo.equals("cambiomodo")) {
		if(opcionGestion.equals("empleados")) {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp");
		}else {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios");
		}
		}else if(tipo.equals("insertEmpleado")) {
			ModeloEmpleado mempleado = new ModeloEmpleado();
			String dni = request.getParameter("DNI_Emp");
			String nombre = request.getParameter("Nombre");
			String apellidos = request.getParameter("Apellidos");
			String correo = request.getParameter("Correo");
			String passSinCifrar = request.getParameter("pass");
			String fechaSinFormato = request.getParameter("Fecha_Nacimiento");
			String puesto = request.getParameter("Puesto");
			String clinica = request.getParameter("Clinica");
			String password = DigestUtils.sha1Hex(passSinCifrar);
			Date fecha_nacimiento = null;
			try {
				fecha_nacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSinFormato);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			boolean creado = mempleado.addEmpleado(dni, nombre, apellidos, correo, password, fecha_nacimiento, Integer.parseInt(puesto), Integer.parseInt(clinica));
			
			if(creado) {
				response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=usucreado");
			}else {
				response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=error");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=error");
		}
	}

}
