package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloCita;
import modelo.DTO.Cliente;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class ConfirmarCita
 */
@WebServlet("/ConfirmarCita")
public class AsignarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignarCita() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		boolean error = false;
			if(empleadoLogueado == null) {
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			} else {
				int id_clinica = -1;
				if(request.getParameter("id_clinica") != null) {
					id_clinica = Integer.parseInt(request.getParameter("id_clinica"));
				}else {
					error = true;
				}
				//TODO Obtener los datos del check y enviarlos al jsp
				
				if(error) {
					response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
				}else {
					
				}
			  }
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
