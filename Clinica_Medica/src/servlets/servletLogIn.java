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
import entidad.Usuarios;
import entidad.Especialidades;
import negocio.EspecialidadesNegocio;
import negocio.MedicosNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.EspecialidadesNegocioImpl;
import negocioImpl.MedicosNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/servletLogIn")
public class servletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public servletLogIn() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnIniciarSesion")!=null) {
			UsuarioNegocio un = new UsuarioNegocioImpl();
			MedicosNegocio mn = new MedicosNegocioImpl();
			EspecialidadesNegocio en = new EspecialidadesNegocioImpl();
			
			String dni = request.getParameter("txtDNI");
			String contra = request.getParameter("txtContraseña");
			
			int control = un.comprobarLogin(dni, contra);
			
			if(control == 0) {	//El DNI ingresado no es correctos
				
				String dniIncorrecto = "El DNI ingresado no se encuentra en el sistema";
				
				request.setAttribute("dniIncorrecto", dniIncorrecto);
				
				RequestDispatcher rd = request.getRequestDispatcher("/LogIn.jsp");
				rd.forward(request, response);
			}
			if(control == 1) {	// La contraseña y el DNI son correctos
				
				int tdu = un.comprobarTipoDeUsuario(dni);
				
				if(tdu == 0) { 
					String dniIncorrecto = "Entro aca";
					
					request.setAttribute("dniIncorrecto", dniIncorrecto);
					
					RequestDispatcher rd = request.getRequestDispatcher("/LogIn.jsp");
					rd.forward(request, response);
					
				}
				if(tdu == 1) {	//El usuario es de tipo Administrador
					Usuarios u = un.obtenerUsuario(dni);
					ArrayList<Especialidades> le = (ArrayList<Especialidades>) en.readAll();
					
					getServletContext().setAttribute("listaEs", le);
					request.getSession().setAttribute("Admin", (Usuarios)u);
					
					RequestDispatcher rd = request.getRequestDispatcher("/InicioAdmin.jsp");
					rd.forward(request, response);
				}
				if(tdu == 2) {	//El usuario es de tipo Medico
					Usuarios u = un.obtenerUsuario(dni);
					Medicos m = mn.obtenerMedico(dni);
					request.getSession().setAttribute("Medico", m);
					
					RequestDispatcher rd = request.getRequestDispatcher("/InicioMedico.jsp");
					rd.forward(request, response);
				}
				if(tdu == 3) {	//El usuario es de tipo Paciente
					String dniIncorrecto = "Usuario Paciente";
					
					request.setAttribute("dniIncorrecto", dniIncorrecto);
					
					RequestDispatcher rd = request.getRequestDispatcher("/LogIn.jsp");
					rd.forward(request, response);
				}
			}
			
			
				
			if(control == 2) {	// La contraseña es incorrecta
				
				String contIncorrecta = "La contraseña ingresada es incorrecta";
				
				request.setAttribute("contIncorrecta", contIncorrecta);
				RequestDispatcher rd = request.getRequestDispatcher("/LogIn.jsp");
				rd.forward(request, response);
			}
		}
	}

}
