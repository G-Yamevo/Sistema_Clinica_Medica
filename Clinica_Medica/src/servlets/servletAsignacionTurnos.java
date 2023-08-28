package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Estados;
import entidad.Medicos;
import entidad.Turnos;
import dao.MedicosDao;
import negocio.MedicosNegocio;
import negocio.TurnosNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.MedicosNegocioImpl;
import negocioImpl.TurnosNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class servletAsignacionTurnos
 */
@WebServlet("/servletAsignacionTurnos")
public class servletAsignacionTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public servletAsignacionTurnos() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnSeleccionarEs") !=null) {
            MedicosNegocio mn = new MedicosNegocioImpl();
            TurnosNegocio tn = new TurnosNegocioImpl();

            String cod = (String)request.getParameter("slEspecialidad");

            ArrayList<Medicos> lm = (ArrayList<Medicos>) mn.buscarxEspecialidad(cod);
            ArrayList<Turnos> lt = (ArrayList<Turnos>) tn.readAll();
            ArrayList<Turnos> ltOcu = (ArrayList<Turnos>) tn.readAllOcupados();
            ArrayList<Turnos> ltLib = (ArrayList<Turnos>) tn.readAllLibres();

            request.setAttribute("cod",cod);
            request.setAttribute("listaMedicos",lm);
            request.setAttribute("listaTurnos",lt);
            request.setAttribute("listaTurnosOcu",ltOcu);
            request.setAttribute("listaTurnosLib",ltLib);

            for(Turnos t : lt){
                System.out.println(t.toString());
            }
            
            for(Turnos t : ltOcu){
                System.out.println(t.toString());
            }
            
            for(Turnos t : ltLib){
                System.out.println(t.toString());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/AsignacionTurnos.jsp");
            rd.forward(request,response);
        }
		if(request.getParameter("btnSeleccionarMedico") !=null) {
			UsuarioNegocio un = new UsuarioNegocioImpl();
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			String cod = (String)request.getParameter("slEspecialidad");
			
			ArrayList<Medicos> lm = (ArrayList<Medicos>) mn.buscarxEspecialidad(cod);
			
			request.setAttribute("cod",cod);
			request.setAttribute("listaMedicos",lm);
			RequestDispatcher rd = request.getRequestDispatcher("/AsignacionTurnos.jsp");
			rd.forward(request,response);
		}
		if(request.getParameter("btnGuardarTurno")!=null) {
			Turnos t= new Turnos();
			Medicos m= new Medicos();
			MedicosNegocioImpl neg= new MedicosNegocioImpl();
			TurnosNegocioImpl turnoneg=new TurnosNegocioImpl();
			Estados est= new Estados();
			est.setCod_estado("2");
			
			m = neg.obtenerMedico((String)request.getParameter("Dni"));
			System.out.println("entro");
			if(request.getParameter("txtDniPaciente")!="") {
			
				t.setDni_medico((String)request.getParameter("Dni"));
				t.setDni_paciente((String)request.getParameter("txtDniPaciente"));
				t.setCod_especialidad((String)request.getParameter("slEspecialidad"));
				t.setDia((String)m.getDiasAtencion());
				t.setHora((String)request.getParameter("hora"));
				t.setEstado(est);
				
				if(turnoneg.insertarTurno(t)) {
					String mensajeTurnoAgregado = "El Turno se agrego correctamente";
					System.out.println(t.toString());
					request.setAttribute("turnoAgregado",mensajeTurnoAgregado);
					RequestDispatcher rd = request.getRequestDispatcher("/AsignacionTurnos.jsp");
					rd.forward(request,response);
					
				}
			}else {
				String mensaje = "Ingrese el dni del paciente";
				System.out.println(t.toString());
				request.setAttribute("mensaje",mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/AsignacionTurnos.jsp");
				rd.forward(request,response);
			}
			
		}
	}

}