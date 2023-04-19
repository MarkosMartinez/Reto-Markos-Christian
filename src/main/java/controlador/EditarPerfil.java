package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EditarPerfil
 */
@WebServlet("/EditarPerfil")
public class EditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(clienteLogueado == null) { //TODO El empleado no se puede editar el perfil, pero podra editar la de los usuarios?
			response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
		}else {
		String tipoLogin = "ninguno";
			if(clienteLogueado != null) {
				tipoLogin = "cliente";
			} else if(empleadoLogueado != null) {
				tipoLogin = "empleado";
			}
			
		request.setAttribute("tipoLogin", tipoLogin);
		request.setAttribute("cliente", clienteLogueado);
		request.getRequestDispatcher("editarPerfil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
