package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloEmpleado;
import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class Principal
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //EMPEZAR SIEMPRE DESDE AQUI PARA EVITAR ERRORES CON LA BBDD
		Conector conector = new Conector();
		HttpSession session = request.getSession();
		if (session.getAttribute("ssh") == null) {
			conector.ssh(request);
			session.setAttribute("ssh", true);
		}
		
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		boolean director = false;
		String tipoLogin = "ninguno";
			if(clienteLogueado != null) {
				tipoLogin = "cliente";
			} else if(empleadoLogueado != null) {
				Conector con  = new Conector();
				con.conectar();
				ModeloEmpleado mempleado = new ModeloEmpleado(con);
				director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
				con.cerrar();
				tipoLogin = "empleado";
			}
		request.setAttribute("director", director);
		request.setAttribute("tipoLogin", tipoLogin);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
