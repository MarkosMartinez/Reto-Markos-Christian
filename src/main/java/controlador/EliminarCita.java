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
 * Servlet implementation class eliminarCita
 */
@WebServlet("/EliminarCita")
public class EliminarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCita() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		boolean eliminado = false;
			if(clienteLogueado == null && empleadoLogueado == null) {
				response.sendRedirect(request.getContextPath() + "/LoginYRegistro");
			} else {
				int id_clinica = -1;
				if(request.getParameter("id_clinica") != null) {
					id_clinica = Integer.parseInt(request.getParameter("id_clinica"));
				}
				String dni = request.getParameter("dni");
				if(clienteLogueado != null) {
					if(!dni.equals(clienteLogueado.getDni())) {
						response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradoincorrecto");
						//TODO Despues de esto, tiene que salir!
						//TODO Arreglar el Bug de que deje de funconar despues de esto!
					}else {
						/**/
						
						String fechaSinFormato = request.getParameter("fecha");
						SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
						Date fecha = null;
						if(fechaSinFormato != null) {
							try {
								fecha=formatoFecha.parse(fechaSinFormato);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					    LocalTime hora = null;
					    if(request.getParameter("hora") != null) {
					    	hora = LocalTime.parse(request.getParameter("hora"));
					    }
					    if(id_clinica == -1 || dni == null || fecha == null || hora == null) {
					    	response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradoincorrecto");
					    }else {
					    	ModeloCita mcita = new ModeloCita();
					    	eliminado = mcita.borrarCita(id_clinica, dni, fecha, hora);
					    	if(eliminado) {
					    	response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradocorrecto");
					    	}else {
					    	 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradoincorrecto");
					    	}
					    }
						
						/**/
					}
				}else {
				String fechaSinFormato = request.getParameter("fecha");
				SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
				Date fecha = null;
				if(fechaSinFormato != null) {
					try {
						fecha=formatoFecha.parse(fechaSinFormato);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			    LocalTime hora = null;
			    if(request.getParameter("hora") != null) {
			    	hora = LocalTime.parse(request.getParameter("hora"));
			    }
			    if(id_clinica == -1 || dni == null || fecha == null || hora == null) {
			    	response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradoincorrecto");
			    }else {
			    	ModeloCita mcita = new ModeloCita();
			    	eliminado = mcita.borrarCita(id_clinica, dni, fecha, hora);
			    	if(eliminado) {
			    	response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradocorrecto");
			    	}else {
			    	 response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=borradoincorrecto");
			    	}
			    }
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
