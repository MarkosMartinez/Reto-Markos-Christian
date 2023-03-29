package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloCliente;
import modelo.DAO.ModeloClinica;
import modelo.DTO.Cliente;
import modelo.DTO.Clinica;

/**
 * Servlet implementation class realizarCita
 */
@WebServlet("/RealizarCita")
public class RealizarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarCita() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Clinica> clinicas = new ArrayList<>();
		ModeloClinica mclinica = new ModeloClinica();
		try {
			clinicas = mclinica.getClinicas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String aviso = request.getParameter("aviso");
		if(aviso == null) {
			aviso = "ninguno";
		}
		request.setAttribute("aviso", aviso);
		request.setAttribute("clinicas", clinicas);
		//TODO a la hora de ver la listya, mostrar tambien el telefono
		request.getRequestDispatcher("realizarCita.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_Clinica = Integer.parseInt(request.getParameter("ID_Clinica"));
		String dni = request.getParameter("dni");
		ModeloCliente mcliente = new ModeloCliente();
		Cliente cliente = new Cliente();
		try {
			cliente = mcliente.getCliente(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cliente.getDni() == "-1") {
			response.sendRedirect(request.getContextPath() + "/RealizarCita?aviso=dninoregistrado");
		}else {
			String fechaSinFormato = request.getParameter("fecha");
			Date fecha = null;
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSinFormato);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 LocalTime hora = LocalTime.parse(request.getParameter("hora"));
			 //TODO aqui ahora creara la cita.
		}
		
	}

}
