package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloCita;
import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DTO.Cita;
import modelo.DTO.Cliente;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;
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
			if(clienteLogueado == null && empleadoLogueado == null) {
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
			} else {
				
				ArrayList<Cita> citasPosteriores = new ArrayList<>();
				ArrayList<Cita> citasAnteriores = new ArrayList<>();
				ModeloCita mcita = new ModeloCita();
				ArrayList<Clinica> clinicas = new ArrayList<>();
				ModeloClinica mclinica = new ModeloClinica();
				clinicas = mclinica.getClinicas();
				ModeloCliente mcliente = new ModeloCliente();
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
				
				request.setAttribute("telefonosPosteriores", listaTelefonosPosteriores);
				request.setAttribute("telefonosAnteriores", listaTelefonosAnteriores);
				request.setAttribute("horasPosteriores", horasPosteriores);
				request.setAttribute("horasAnteriores", horasAnteriores);
				request.setAttribute("clinicas", clinicas);
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
		doGet(request, response);
	}

}
