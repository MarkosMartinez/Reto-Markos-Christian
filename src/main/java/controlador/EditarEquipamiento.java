package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import modelo.DTO.Clinica;
import modelo.DTO.Empleado;
import modelo.DAO.ModeloClinica;
import modelo.DAO.ModeloEmpleado;
import modelo.DAO.ModeloEquipamiento;
import modelo.DTO.Equipamiento;

/**
 * Servlet implementation class EditarEquipamiento
 */
@WebServlet("/EditarEquipamiento")
public class EditarEquipamiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarEquipamiento() {
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
		} else {
			ArrayList<Clinica> clinicas = new ArrayList<>();
			ModeloClinica mclinica = new ModeloClinica();
			clinicas = mclinica.getClinicas();
			ModeloEmpleado mempleado = new ModeloEmpleado();
			boolean director = mempleado.getDirector(empleadoLogueado.getId_Puesto());
			ModeloEquipamiento mequipamiento = new ModeloEquipamiento();
			ArrayList<Equipamiento> equipamiento = new ArrayList<>(); 
			equipamiento = mequipamiento.cargarEquipamiento(empleadoLogueado.getId_Clinica());
			
			if(request.getParameter("c") != null && request.getParameter("v") != null) {
				int cantidad = Integer.parseInt(request.getParameter("c"));
				int valor = Integer.parseInt(request.getParameter("v"));
				if(valor>= 0) {
					mequipamiento.actualizarStock(equipamiento.get(cantidad).getId_Equipamiento(), valor);
					equipamiento = mequipamiento.cargarEquipamiento(empleadoLogueado.getId_Clinica());
				}else {
					request.setAttribute("equipamiento", equipamiento);
					request.getRequestDispatcher("editarEquipamiento.jsp?aviso=error").forward(request, response);
				}
				}
			
			if(request.getParameter("d") != null && director) {
				int delete = Integer.parseInt(request.getParameter("d"));
				boolean eliminado = mequipamiento.eliminarStock(delete);
				if(!eliminado) {
					request.setAttribute("equipamiento", equipamiento);
					request.getRequestDispatcher("editarEquipamiento.jsp?aviso=error").forward(request, response);
				}else {
					equipamiento = mequipamiento.cargarEquipamiento(empleadoLogueado.getId_Clinica());
				}
				}
			
			String aviso = request.getParameter("aviso");
			request.setAttribute("aviso", aviso);
			
			request.setAttribute("empleadoLogueado", empleadoLogueado);
			request.setAttribute("equipamiento", equipamiento);
			request.setAttribute("director", director);
			if(director) {
				request.setAttribute("clinicas", clinicas);
			}
			request.getRequestDispatcher("editarEquipamiento.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		ModeloEquipamiento mequipamiento = new ModeloEquipamiento();
		if(tipo.equals("insert")) {
			
			if(request.getParameter("nombre") != null && request.getParameter("precio") != null && request.getParameter("stock") !=null) {
			String nombre = request.getParameter("nombre");
			Double precio = Double.parseDouble(request.getParameter("precio"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			int idClinica = Integer.parseInt(request.getParameter("idClinica"));
			mequipamiento.insertar(nombre, precio, stock, idClinica);
			response.sendRedirect(request.getContextPath() + "/EditarEquipamiento?aviso=insertado");
			}else {
				response.sendRedirect(request.getContextPath() + "/EditarEquipamiento?aviso=error");
			}
		}else if(tipo.equals("actualizar")){
		int cantidadStock = Integer.parseInt(request.getParameter("cantidad"));
		
		HttpSession session = request.getSession();
		Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
			ArrayList<Equipamiento> equipamiento = new ArrayList<>(); 
			equipamiento = mequipamiento.cargarEquipamiento(empleadoLogueado.getId_Clinica());
		
		ArrayList<Integer> nuevoStock = new ArrayList<>();
		for (int i = 0; i < cantidadStock; i++) {
			nuevoStock.add(Integer.parseInt(request.getParameter("stock-" + i)));
		}
		
		ArrayList<Equipamiento> equipamientoFinal = new ArrayList<>(); //TODO Optimizar esto?
		for (int j = 0; j < equipamiento.size(); j++) {
				equipamiento.get(j).setStock(nuevoStock.get(j));
				equipamientoFinal.add(equipamiento.get(j));
			
		}
		
		boolean actualizado = false;
		actualizado = mequipamiento.actualizarTodos(equipamientoFinal);
		if(actualizado) {
			 response.sendRedirect(request.getContextPath() + "/EditarEquipamiento?aviso=actualizado");
		}else {
			 response.sendRedirect(request.getContextPath() + "/EditarEquipamiento?aviso=error");

		}
		}else if(tipo.equals("modclinica")){
			ModeloEmpleado mempleado = new ModeloEmpleado();
			int idNuevaClinica = Integer.parseInt(request.getParameter("clinica"));
			String dniDirector = request.getParameter("dnidirector");
			mempleado.cambiarClinica(dniDirector, idNuevaClinica);
			HttpSession session = request.getSession();
			Empleado empleadoLogueado = (Empleado) session.getAttribute("empleadoLogueado");
			empleadoLogueado.setId_Clinica(idNuevaClinica);
			session.setAttribute("empleadoLogueado", empleadoLogueado);
			response.sendRedirect(request.getContextPath() + "/EditarEquipamiento");
		}else {
			 response.sendRedirect(request.getContextPath() + "/EditarEquipamiento?aviso=error");

		}
	}

}
