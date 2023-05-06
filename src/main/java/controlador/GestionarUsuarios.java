package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DTO.Cliente;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;

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
			if(visualizar == null)
				visualizar = "cliente";
				
			ModeloEmpleado mempleado = new ModeloEmpleado();
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
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
					ArrayList<Empleado> empleados = new ArrayList<>();
					ArrayList<Clinica> clinicas = new ArrayList<>();
				
					clinicas = mclinica.getClinicas();
					empleados = mempleado.getEmpleados();
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
		String opcionGestion = request.getParameter("opcionGestion"); //TODO Aviso correcto / error
		String tipo = request.getParameter("tipo");
		if(tipo.equals("cambiomodo")) {
		if(opcionGestion.equals("empleados")) {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp");
		}else {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios");
		}
		}else if(tipo.equals("insertEmpleado")) {
			//TODO Hacer que inserte el nuevo empleado
		}else {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?aviso=error");
		}
	}

}
