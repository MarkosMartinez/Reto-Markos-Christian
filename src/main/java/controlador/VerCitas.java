package controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
				
				ArrayList<Cita> citasSinOrden = new ArrayList<>();
				ModeloCita mcita = new ModeloCita();
				ArrayList<Clinica> clinicas = new ArrayList<>();
				ModeloClinica mclinica = new ModeloClinica();
				clinicas = mclinica.getClinicas();
				ModeloCliente mcliente = new ModeloCliente();
				ArrayList<Cliente> clientes = new ArrayList<>();
				clientes = mcliente.getClientes();
				if (empleadoLogueado != null) {
					citasSinOrden = mcita.getCitas(empleadoLogueado.getId_Clinica());
				}else if(clienteLogueado != null) {
					citasSinOrden = mcita.getCitasCliente(clienteLogueado.getDni());
				}
				ArrayList<Cita> citasOrdenadas = mcita.ordenarCitas(citasSinOrden);
				/*ArrayList<Cita> citasPosteriores = mcita.citasPosteriores(citasOrdenadas);
				//TODO obtener citas ateriores*/
				//TODO Intentar obtener las citas anteriores, y las posteriores con SQL: SELECT * FROM citas WHERE fecha > NOW();
				
				ArrayList<String> horas = new ArrayList<>();
				for (Cita cita : citasOrdenadas) {
					horas.add(cita.getHora_Cita().toString());
					
				}
				ArrayList<Telefonos> telefonos = new ArrayList<>();
				ArrayList<String> listatelefonos = new ArrayList<>();
				telefonos = mcliente.cargarTelefonos();
				for (Cita cita : citasOrdenadas) {
					for (Telefonos telefono : telefonos) {
						if(cita.getDni_Cliente().equals(telefono.getDni())) {
							listatelefonos.add(Integer.toString(telefono.getTelefono()));
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
				
				request.setAttribute("telefonos", listatelefonos);
				request.setAttribute("horas", horas);
				request.setAttribute("clinicas", clinicas);
				request.setAttribute("citas", citasOrdenadas);
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
