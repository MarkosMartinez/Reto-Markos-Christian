package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloHabitacion;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;
import modelo.DTO.Habitacion;

/**
 * Servlet implementation class GestionarClinicas
 */
@WebServlet("/GestionarClinicas")
public class GestionarClinicas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionarClinicas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); //TODO Añadir los avisos de error y correctos (Tambien recibe avisos al eliminar/crear clinicas y habitaciones)
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(empleadoLogueado == null) {
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
		}else {
			Conector con = new Conector();
			con.conectar();
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			if(director) {
				String aviso = request.getParameter("aviso");
				if(aviso == null)
					aviso = "ninguno";
				ModeloClinica mclinica = new ModeloClinica(con);
				ModeloHabitacion mhabitacion = new ModeloHabitacion(con);
				ArrayList<Clinica> clinicas = mclinica.getClinicas(); //TODO Añadir un link para poder ver con distinto orden
				ArrayList<Habitacion> habitaciones = mhabitacion.getHabitaciones(empleadoLogueado.getId_Clinica());
				
				request.setAttribute("clinicas", clinicas);
				request.setAttribute("habitaciones", habitaciones);
				request.setAttribute("aviso", aviso);
				con.cerrar();
				request.getRequestDispatcher("listaHabitacionesClinicas.jsp").forward(request, response);
			}else {
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		Conector con  = new Conector();
		 con.conectar();
		if(tipo.equals("modclinica")) { //TODO Arreglar que se vea bien despues de cambiar de clinica, para no tener que cerrar sesion
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			int idNuevaClinica = Integer.parseInt(request.getParameter("clinica"));
			HttpSession session = request.getSession();
			Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
			mempleado.cambiarClinica(empleadoLogueado.getDni_Emp(), idNuevaClinica);
			empleadoLogueado.setId_Clinica(idNuevaClinica);
			session.setAttribute("empleadoLogueado", empleadoLogueado);
			con.cerrar();
			response.sendRedirect(request.getContextPath() + "/GestionarClinicas");
			
		}else if(tipo.equals("addHabitacion")) {
			String numHabitacion = request.getParameter("numHabitacion");
			String especialidad = request.getParameter("especialidad");
			String idClinica = request.getParameter("clinica");
			
			boolean añadido = false;
			
			Habitacion nuevaHabitacion = new Habitacion();
			nuevaHabitacion.setNum_Habitacion(Integer.parseInt(numHabitacion));
			nuevaHabitacion.setEspecialidad(especialidad);
			nuevaHabitacion.setId_Clinica(Integer.parseInt(idClinica));
			
			ModeloHabitacion mhabitacion = new ModeloHabitacion(con);
			boolean disponible = mhabitacion.comprobarDisponibilidad(nuevaHabitacion);
			if(disponible)
			añadido = mhabitacion.crearHabitacion(nuevaHabitacion);
			
			con.cerrar();
			if(añadido){
				response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=habitacioncreada");
			}else {
				response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
			}
			
		}else if(tipo.equals("addClinica")) {
			String nombre = request.getParameter("nombre");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			
			Clinica nuevaClinica = new Clinica();
			nuevaClinica.setNombre_clinica(nombre);
			nuevaClinica.setDireccion(direccion);
			nuevaClinica.setTelefono(Integer.parseInt(telefono));
			
			ModeloClinica mclinica = new ModeloClinica(con);
			boolean creado = mclinica.crearClinica(nuevaClinica);
			
			con.cerrar();
			if(creado) {
				response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=clinicacreada");
			}else {
				response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
			}
		}else {
			con.cerrar();
			response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
		}
	}

}
