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
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado"); //TODO Hacer un refactor de las clases y la BBDD de plural/singular. Ej: Empleado/s...
		
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
		
			if(clienteLogueado == null && empleadoLogueado == null) {
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
			} else {
				
				ArrayList<Cita> citasPosteriores = new ArrayList<>();
				ArrayList<Cita> citasAnteriores = new ArrayList<>();
				String aviso = request.getParameter("aviso");
				ModeloCita mcita = new ModeloCita();
				ArrayList<Clinica> clinicas = new ArrayList<>();
				ModeloClinica mclinica = new ModeloClinica();
				clinicas = mclinica.getClinicas();
				
				ArrayList<Historial_Cliente> historiales = new ArrayList<>();
				ModeloHistorial_Cliente mhistorial = new ModeloHistorial_Cliente();
				historiales = mhistorial.getHistoriales(); //TODO enviar solo los del cliente si loguea el cliente?
				
				ModeloCliente mcliente = new ModeloCliente();
				ModeloEmpleado mempleado = new ModeloEmpleado();
				ArrayList<Empleado> empleados = mempleado.getEmpleados();
				ArrayList<Cliente> clientes = new ArrayList<>();
				clientes = mcliente.getClientes();
				if (empleadoLogueado != null) {
					citasPosteriores = mcita.citasPosteriores(empleadoLogueado.getId_Clinica());
					citasAnteriores = mcita.citasAnteriores(empleadoLogueado.getId_Clinica());
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
				
				ArrayList<Telefonos> telefonos = new ArrayList<>();
				ArrayList<String> listaTelefonosPosteriores = new ArrayList<>();
				ArrayList<String> listaTelefonosAnteriores = new ArrayList<>();
				telefonos = mcliente.cargarTelefonos();
				for (Cita cita : citasPosteriores) {
					for (Telefonos telefono : telefonos) {
						if(cita.getDni_Cliente().equals(telefono.getDni())) {
							listaTelefonosPosteriores.add(Integer.toString(telefono.getTelefono()));
						}
					}
				}
				for (Cita cita : citasAnteriores) {
					for (Telefonos telefono : telefonos) {
						if(cita.getDni_Cliente().equals(telefono.getDni())) {
							listaTelefonosAnteriores.add(Integer.toString(telefono.getTelefono()));
						}
					}
				}
				
				String tipoLogin = "ninguno";
				if(clienteLogueado != null) {
					tipoLogin = "cliente";
				} else if(empleadoLogueado != null) {
					tipoLogin = "empleado";
				}
				request.setAttribute("tipoLogin", tipoLogin);
				request.setAttribute("historiales", historiales);
				request.setAttribute("empleados", empleados);
				request.setAttribute("telefonosPosteriores", listaTelefonosPosteriores);
				request.setAttribute("telefonosAnteriores", listaTelefonosAnteriores);
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
		String editardni = request.getParameter("editardni");
		String editarclinica = request.getParameter("editarclinica");
		int editartelefono = Integer.parseInt(request.getParameter("editartelefono"));
		String fechaSinFormato = request.getParameter("editarfecha");
		Date editarfecha = null;
		try {
			editarfecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSinFormato);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 LocalTime editarhora = LocalTime.parse(request.getParameter("editarhora"));
		 String editarcliente = request.getParameter("editarcliente");
		 String editarempleado = request.getParameter("editarempleado");
		 String informe = request.getParameter("informe");
		 String equipamiento = request.getParameter("equipamiento"); /*null y on*/
		 
		 ModeloCita mcita = new ModeloCita();
		 Boolean actualizado = false;
		 actualizado = mcita.actualizarCita(editardni, editarfecha, editarhora, editarempleado, informe);
		 if(actualizado) {
		 if(equipamiento == null) {
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradocorrecto"); //TODO Cambiar el aviso correcto!
		 }else if(equipamiento == "on") {
			 response.sendRedirect(request.getContextPath() + "/EditarEquipamiento");
		 }
		 }else {
			 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
		 }
		 
	}

}
