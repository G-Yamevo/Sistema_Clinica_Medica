package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medicos;
import entidad.Turnos;
import negocio.EspecialidadesNegocio;
import negocio.MedicosNegocio;
import negocio.TurnosNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.EspecialidadesNegocioImpl;
import negocioImpl.MedicosNegocioImpl;
import negocioImpl.TurnosNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class servletTurnosMedicos
 */
@WebServlet("/servletTurnosMedicos")
public class servletTurnosMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletTurnosMedicos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			if(request.getParameter("Param").equals("1")) {
				TurnosNegocio tn = new TurnosNegocioImpl();	
				Medicos m = (Medicos)request.getSession().getAttribute("Medico");
				
				ArrayList<Turnos> lt = (ArrayList<Turnos>) tn.obtenerTurnosxMed(m.getDni());
				
				request.setAttribute("listaTurnos",lt);
				RequestDispatcher rd = request.getRequestDispatcher("/VerTurnosMedico.jsp");
				rd.forward(request,response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnCambiarEstado")!=null) {
			TurnosNegocio tn = new TurnosNegocioImpl();
			
			Turnos t = tn.obtenerTurnos((String)request.getParameter("codTurno"));
			t.setCod_especialidad((String)request.getParameter("slEstados"));
			
			if(tn.modificarEstado(t)) {
				String mensaje = "El estado se modifico correctamente";
				Medicos m = (Medicos)request.getSession().getAttribute("Medico");
				
				ArrayList<Turnos> lt = (ArrayList<Turnos>) tn.obtenerTurnosxMed(m.getDni());
				
				request.setAttribute("mensaje",mensaje);
				request.setAttribute("listaTurnos",lt);
				RequestDispatcher rd = request.getRequestDispatcher("/VerTurnosMedico.jsp");
				rd.forward(request,response);
			}else {
				
				String mensaje = "No se pudo modificar el estado";
				Medicos m = (Medicos)request.getSession().getAttribute("Medico");
				
				ArrayList<Turnos> lt = (ArrayList<Turnos>) tn.obtenerTurnosxMed(m.getDni());
				
				request.setAttribute("mensaje",mensaje);
				request.setAttribute("listaTurnos",lt);
				RequestDispatcher rd = request.getRequestDispatcher("/VerTurnosMedico.jsp");
				rd.forward(request,response);
			}
		}
	}

}
