package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloCita;
import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloHabitacion;
import modelo.DTO.Cliente;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class realizarCita
 */
@WebServlet("/RealizarCita")
public class RealizarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarCita() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		ArrayList<Clinica> clinicas = new ArrayList<>();
		Conector con  = new Conector();
		con.conectar();
		ModeloClinica mclinica = new ModeloClinica(con);
		clinicas = mclinica.getClinicas();
		boolean director = false; 
		String aviso = request.getParameter("aviso");
		String clinica = request.getParameter("clinica");
		String dni = request.getParameter("dni");
		String fecha = request.getParameter("fecha");
		String hora = request.getParameter("hora");
		
		if(clienteLogueado != null) {
			dni = clienteLogueado.getDni();
		}
		String tipoLogin = "ninguno";
		if(clienteLogueado != null) {
			tipoLogin = "cliente";
		} else if(empleadoLogueado != null) {
			tipoLogin = "empleado";
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
		}
		con.cerrar();
		
		request.setAttribute("director", director);
		request.setAttribute("tipoLogin", tipoLogin);
		request.setAttribute("aviso", aviso);
		request.setAttribute("clinica", clinica);
		request.setAttribute("dni", dni);
		request.setAttribute("fecha", fecha);
		request.setAttribute("hora", hora);
		request.setAttribute("clinicas", clinicas); 
		request.getRequestDispatcher("realizarCita.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_Clinica = Integer.parseInt(request.getParameter("ID_Clinica"));
		String dni = request.getParameter("dni");
		Conector con  = new Conector();
		 con.conectar();
		ModeloCliente mcliente = new ModeloCliente(con);
		Cliente cliente = new Cliente();
		cliente = mcliente.getCliente(dni);
		if(cliente.getDni() == "-1") {
			con.cerrar();
			response.sendRedirect(request.getContextPath() + "/RealizarCita?aviso=dninoregistrado");
		}else {
			String fechaSinFormato = request.getParameter("fecha");
			Date fecha = null;
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSinFormato);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 LocalTime hora = LocalTime.parse(request.getParameter("hora"));
			 
			 ModeloCita modeloCita = new ModeloCita(con);
			 ModeloHabitacion modeloHabitacion = new ModeloHabitacion(con);
			 int cantidadDeHabitaciones = modeloHabitacion.getCantHabitaciones(id_Clinica);
			 if(modeloCita.disponible(id_Clinica, fecha, hora, cantidadDeHabitaciones)) {
			 boolean creado = modeloCita.crearCita(id_Clinica, dni, fecha, hora);
			if(creado) {
			HttpSession session = request.getSession();
			Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
			Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
				if(clienteLogueado == null) {
					if(empleadoLogueado == null) {
						con.cerrar();
						response.sendRedirect(request.getContextPath() + "/Principal");
					}else {
						con.cerrar();
						response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=citacreada");
					}
				}else {
					con.cerrar();
					response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=citacreada");
				}
			 }else{
				 con.cerrar();
				 response.sendRedirect(request.getContextPath() + "/RealizarCita?aviso=demasiadascitas&clinica=" + id_Clinica + "&dni=" + dni + "&fecha=" + fechaSinFormato + "&hora=" + hora);
			 }
			 }else {
					response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			 }
		}
		
	}

}
