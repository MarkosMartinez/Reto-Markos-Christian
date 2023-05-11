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
import modelo.DAO.ModeloHistorial_Cliente;
import modelo.DTO.Cita;
import modelo.DTO.Cliente;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;
import modelo.DTO.Historial_Cliente;
import modelo.DTO.Telefonos;

/**
 * Servlet implementation class VerConsultas
 */
@WebServlet("/VerCitas")
public class VerCitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCitas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		
		if(clienteLogueado == null && empleadoLogueado == null) {
			response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
		} else {
			
			String editarid_clinica = request.getParameter("editarid_clinica");
			String editardni = request.getParameter("editardni");
			String editarclinica = request.getParameter("editarclinica");
			String editartelefono = request.getParameter("editartelefono");
			String editarfecha = request.getParameter("editarfecha");
			String editarhora = request.getParameter("editarhora");
			String editarcliente = request.getParameter("editarcliente");
			
			request.setAttribute("editarid_clinica", editarid_clinica);
			request.setAttribute("editardni", editardni);
			request.setAttribute("editarclinica", editarclinica);
			request.setAttribute("editartelefono", editartelefono);
			request.setAttribute("editarfecha", editarfecha);
			request.setAttribute("editarhora", editarhora);
			request.setAttribute("editarcliente", editarcliente);
			
				ArrayList<Cita> citasPosteriores = new ArrayList<>();
				ArrayList<Cita> citasAnteriores = new ArrayList<>();
				String aviso = request.getParameter("aviso");
				
				Conector con = new Conector();
				con.conectar();

				ModeloCita mcita = new ModeloCita(con);
				ArrayList<Clinica> clinicas = new ArrayList<>();
				ModeloClinica mclinica = new ModeloClinica(con);
				clinicas = mclinica.getClinicas();
				
				ArrayList<Historial_Cliente> historiales = new ArrayList<>();
				ModeloHistorial_Cliente mhistorial = new ModeloHistorial_Cliente(con);
				historiales = mhistorial.getHistoriales();
				
				boolean director = false;
				ModeloCliente mcliente = new ModeloCliente(con);
				ModeloEmpleado mempleado = new ModeloEmpleado(con);
				ArrayList<Empleado> empleados = mempleado.getEmpleados();
				ArrayList<Cliente> clientes = new ArrayList<>();
				clientes = mcliente.getClientes();
				if (empleadoLogueado != null) {
					citasPosteriores = mcita.citasPosteriores(empleadoLogueado.getId_Clinica());
					citasAnteriores = mcita.citasAnteriores(empleadoLogueado.getId_Clinica());
					director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
				}else if(clienteLogueado != null) {
					citasPosteriores = mcita.getCitasClientePosteriores(clienteLogueado.getDni());
					citasAnteriores = mcita.getCitasClienteAnteriores(clienteLogueado.getDni());
				}
				
				ArrayList<String> horasPosteriores = new ArrayList<>();
				for (Cita cita : citasPosteriores) {
					horasPosteriores.add(cita.getHora_Cita().toString());
					
				}
				ArrayList<String> horasAnteriores = new ArrayList<>();
				for (Cita cita : citasAnteriores) {
					horasAnteriores.add(cita.getHora_Cita().toString());
					
				}
				ArrayList<Telefonos> telefonos = mcliente.cargarTelefonos();
				
				con.cerrar();
				
				String tipoLogin = "ninguno";
				if(clienteLogueado != null) {
					tipoLogin = "cliente";
				} else if(empleadoLogueado != null) {
					tipoLogin = "empleado";
				}
				
				request.setAttribute("director", director);
				request.setAttribute("tipoLogin", tipoLogin);
				request.setAttribute("historiales", historiales);
				request.setAttribute("empleados", empleados);
				request.setAttribute("telefonos", telefonos);
				request.setAttribute("horasPosteriores", horasPosteriores);
				request.setAttribute("horasAnteriores", horasAnteriores);
				request.setAttribute("clinicas", clinicas);
				request.setAttribute("aviso", aviso);
				request.setAttribute("citasPosteriores", citasPosteriores);
				request.setAttribute("citasAnteriores", citasAnteriores);
				request.setAttribute("clientes", clientes);
				request.getRequestDispatcher("verCitas.jsp").forward(request, response);
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String editardni = request.getParameter("editardni"); //TODO MSG Cita pedida / Cita error
		String fechaSinFormato = request.getParameter("editarfecha");
		String tipo = request.getParameter("tipo");
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		Conector con  = new Conector();
		 con.conectar();
		if(tipo.equals("formcita")) {
		Date editarfecha = null;
		try {
			editarfecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSinFormato);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 LocalTime editarhora = LocalTime.parse(request.getParameter("editarhora"));
		 String editarempleado = request.getParameter("editarempleado");
		 String informe = request.getParameter("informe");
		 String equipamiento = request.getParameter("equipamiento"); /*null y on*/
		 
		 ModeloCita mcita = new ModeloCita(con);
		 Boolean actualizado = false;
		 actualizado = mcita.actualizarCita(editardni, editarfecha, editarhora, editarempleado, informe, empleadoLogueado.getId_Clinica());
		 if(actualizado) {
		 if(equipamiento == null) {
			 con.cerrar();
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=citaactualizada");
		 }else if(equipamiento.equals("on")) {
			 con.cerrar();
			 response.sendRedirect(request.getContextPath() + "/EditarEquipamiento");
		 }
		 }else {
			 con.cerrar();
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
		 }
		}else if (tipo.equals("modclinica")) {
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			int idNuevaClinica = Integer.parseInt(request.getParameter("clinica"));
			String dniDirector = request.getParameter("dnidirector");
			mempleado.cambiarClinica(dniDirector, idNuevaClinica);
			empleadoLogueado.setId_Clinica(idNuevaClinica);
			session.setAttribute("empleadoLogueado", empleadoLogueado);
			con.cerrar();
			response.sendRedirect(request.getContextPath() + "/VerCitas");
		}else {
			con.cerrar();
			response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
		}
	}

}
