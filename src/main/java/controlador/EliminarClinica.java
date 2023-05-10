package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Conector;
import modelo.DAO.ModeloCita;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloEquipamiento;
import modelo.DAO.ModeloHabitacion;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;

/**
 * Servlet implementation class EliminarClinica
 */
@WebServlet("/EliminarClinica")
public class EliminarClinica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarClinica() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		
		if(empleadoLogueado != null) {
			Conector con = new Conector();
			con.conectar();
			ModeloEmpleado mempleado = new ModeloEmpleado(con);
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			if(director) {
				String id = request.getParameter("id");
				if(id != null) {
					
					ModeloCita mcita = new ModeloCita(con);
					boolean borrado = mcita.borrarCitas(Integer.parseInt(id));
					if(borrado) {
						ModeloClinica mclinica = new ModeloClinica(con);
						ArrayList<Clinica> clinicas = mclinica.getClinicas();
						boolean encontrado = false;
						int nuevoID = 0;
						for (int i = 0; i < clinicas.size() || !encontrado; i++) {
							if(clinicas.get(i).getId_clinica() != empleadoLogueado.getId_Clinica()) {
								nuevoID = clinicas.get(i).getId_clinica();
								encontrado = true;
							}
						}
						mempleado.cambiarClinica(empleadoLogueado.getDni_Emp(),nuevoID);
						empleadoLogueado.setId_Clinica(nuevoID);
						session.setAttribute("empleadoLogueado", empleadoLogueado);
						if(encontrado) {
						borrado = mempleado.eliminarEmpleados(Integer.parseInt(id));
						}else {
							borrado = false;
						}
						if(borrado) {
							ModeloEquipamiento mequipamiento = new ModeloEquipamiento(con);
							borrado = mequipamiento.eliminarStocks(Integer.parseInt(id));
							if(borrado) {
								ModeloHabitacion mhabitacion = new ModeloHabitacion(con);
								borrado = mhabitacion.eliminarHabitaciones(Integer.parseInt(id));
								if(borrado) {
									borrado = mclinica.borrarClinica(Integer.parseInt(id));
								}
							}
						}
					}
					con.cerrar();
					if(borrado) {
						response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=clinicaeliminada");
					}else {
						response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/GestionarClinicas?aviso=error");
				}
			}else {
				con.cerrar();
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error"); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
