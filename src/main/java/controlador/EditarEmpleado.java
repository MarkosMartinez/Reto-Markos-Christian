package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloPuesto;
import modelo.DTO.Clinica;
import modelo.DTO.Empleado;
import modelo.DTO.Puesto;

/**
 * Servlet implementation class EditarEmpleado
 */
@WebServlet("/EditarEmpleado")
public class EditarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarEmpleado() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String dniAEditar = request.getParameter("dni");
		String aviso = request.getParameter("aviso");
		request.setAttribute("aviso", aviso);
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		if(empleadoLogueado == null || dniAEditar == null) {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=error");
		}else {
			ModeloEmpleado mempleado = new ModeloEmpleado();
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
		if(director || empleadoLogueado.getDni_Emp().equals(dniAEditar)) {
			Empleado empleado = new Empleado();
			empleado = mempleado.getEmpleado(dniAEditar);
			ModeloPuesto mpuesto = new ModeloPuesto();
			ModeloClinica mclinica = new ModeloClinica();
			ArrayList<Puesto> puestos = mpuesto.getPuestos();
			ArrayList<Clinica> clinicas = mclinica.getClinicas();
			
			request.setAttribute("empleado", empleado);
			request.setAttribute("clinicas", clinicas);
			request.setAttribute("puestos", puestos);
			request.getRequestDispatcher("editarPerfilE.jsp").forward(request, response);
		}else {
			if(director) {
			response.sendRedirect(request.getContextPath() + "/GestionarUsuarios?v=emp&aviso=error");
			}else {
				response.sendRedirect(request.getContextPath() + "/VerCitas?aviso=error");
			}
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String correo = request.getParameter("correo");
		String idPuesto = request.getParameter("puesto");
		String idClinica = request.getParameter("clinica");
		String nuevaCon = request.getParameter("nuevaPass");
		String confNuevaCon = request.getParameter("confNuevaCon");
		String passCifrada = "2be88ca4242c76e8253ac62474851065032d6833"; /*null por defecto por si algo fallase, aunque no deberia*/
		boolean cambiarpass = false;
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
		
		ModeloEmpleado mempleado = new ModeloEmpleado();
		Empleado empleadoModificado = new Empleado();
		empleadoModificado.setDni_Emp(dni);
		empleadoModificado.setNombre(nombre);
		empleadoModificado.setApellidos(apellidos);
		empleadoModificado.setCorreo(correo);
		empleadoModificado.setId_Clinica(Integer.parseInt(idClinica));
		empleadoModificado.setId_Puesto(Integer.parseInt(idPuesto));
		
		boolean modificado = mempleado.modificarEmpleado(empleadoModificado);
		
		if(nuevaCon != null) { //TODO Comprobar que esto no salta cuando esta vacio!
			if(nuevaCon.equals(confNuevaCon)) {
			cambiarpass = true;
			passCifrada = DigestUtils.sha1Hex(nuevaCon);
			}else {
				modificado = false;
			}
		}
		if(modificado) {
			if(cambiarpass) {
				mempleado.cambiarContrasenia(dni, passCifrada);
			}
			if(empleadoLogueado.getDni_Emp().equals(dni)) {
				empleadoLogueado.setNombre(nombre);
				empleadoLogueado.setApellidos(apellidos);
				empleadoLogueado.setCorreo(correo);
				empleadoLogueado.setId_Clinica(Integer.parseInt(idClinica));
				empleadoLogueado.setId_Puesto(Integer.parseInt(idPuesto));
				session.setAttribute("empleadoLogueado", empleadoLogueado);
				response.sendRedirect(request.getContextPath() + "/EditarEmpleado?dni=" + dni + "&aviso=actualizado");
			}else {
				response.sendRedirect(request.getContextPath() + "/EditarEmpleado?dni=" + dni + "&aviso=actualizado");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/EditarEmpleado?dni=" + dni + "&aviso=error");
		}
	}

}
