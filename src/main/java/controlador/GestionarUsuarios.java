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
			ModeloEmpleado mempleado = new ModeloEmpleado();
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			ModeloCliente mcliente = new ModeloCliente();
			ModeloClinica mclinica = new ModeloClinica();
			ArrayList<Cliente> clientes = new ArrayList<>();
			ArrayList<Empleado> empleados = new ArrayList<>();
			ArrayList<Clinica> clinicas = new ArrayList<>();
			clientes = mcliente.getClientes();
			clinicas = mclinica.getClinicas();
			
			if(director) {
				empleados = mempleado.getEmpleados();
				request.setAttribute("empleados", empleados);
			}
			
			request.setAttribute("clinicas", clinicas);
			request.setAttribute("clientes", clientes);
			request.setAttribute("director", director);
			request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
