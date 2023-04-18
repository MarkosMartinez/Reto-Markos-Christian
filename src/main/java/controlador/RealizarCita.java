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

import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloHabitacion;
import modelo.DAO.ModeloCita;
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
		ArrayList<Clinica> clinicas = new ArrayList<>();
		ModeloClinica mclinica = new ModeloClinica();
		clinicas = mclinica.getClinicas();	
		String aviso = request.getParameter("aviso");
		String clinica = request.getParameter("clinica");
		String dni = request.getParameter("dni");
		String fecha = request.getParameter("fecha");
		String hora = request.getParameter("hora");
		
		request.setAttribute("aviso", aviso);
		request.setAttribute("clinica", clinica);
		request.setAttribute("dni", dni);
		request.setAttribute("fecha", fecha);
		request.setAttribute("hora", hora);
		request.setAttribute("clinicas", clinicas);
		//TODO a la hora de ver la lista, mostrar tambien el telefono o direccion.
		request.getRequestDispatcher("realizarCita.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_Clinica = Integer.parseInt(request.getParameter("ID_Clinica"));
		String dni = request.getParameter("dni");
		ModeloCliente mcliente = new ModeloCliente();
		Cliente cliente = new Cliente();
		cliente = mcliente.getCliente(dni);
		if(cliente.getDni() == "-1") {
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
			 
			 ModeloCita modeloCita = new ModeloCita();
			 ModeloHabitacion modeloHabitacion = new ModeloHabitacion();
			 int cantidadDeHabitaciones = modeloHabitacion.getCantHabitaciones(id_Clinica); //TODO check o algo en las habitaciones para no poder crear mismo num en la misma clinica. PK no vale.
			 if(modeloCita.disponible(id_Clinica, fecha, hora, cantidadDeHabitaciones)) {
			 modeloCita.crearCita(id_Clinica, dni, fecha, hora);
			HttpSession session = request.getSession();
			Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
			Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
				if(clienteLogueado == null) {
					if(empleadoLogueado == null) {
						response.sendRedirect("Principal");
					}else {
						response.sendRedirect("VerConsultas"); //TODO Cambiar esto?
					}
				}else {
					response.sendRedirect("VerConsultas");
				}
			 }else{
				 response.sendRedirect(request.getContextPath() + "/RealizarCita?aviso=demasiadascitas&clinica=" + id_Clinica + "&dni=" + dni + "&fecha=" + fechaSinFormato + "&hora=" + hora);
			 }
		}
		
	}

}
