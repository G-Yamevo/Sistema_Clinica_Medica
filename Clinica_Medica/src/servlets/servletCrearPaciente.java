package servlets;

import entidad.TiposDeUsuarios;
import entidad.Usuarios;
import negocio.TipoDeUsuarioNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.TipoDeUsuarioNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletCrearPaciente")
public class servletCrearPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletCrearPaciente() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAgregarPaciente")!=null){
			
			UsuarioNegocio un= new UsuarioNegocioImpl();
			TipoDeUsuarioNegocio tdun = new TipoDeUsuarioNegocioImpl();
			
			String dni = (String)request.getAttribute("txtDni");
			
			if(!un.existeUsuario(dni)){
				Usuarios u = new Usuarios();
				u.setDni(request.getParameter("txtDni"));
				u.setNombre(request.getParameter("txtNombre"));
				u.setApellido(request.getParameter("txtApellido"));
				u.setNacionalidad(request.getParameter("txtNacionalidad"));
				u.setSexo(request.getParameter("slSexo"));	
				u.setTelefono(request.getParameter("txtTelefono"));
				u.setMail(request.getParameter("txtMail"));
				u.setProvincia(request.getParameter("txtProvincia"));
				u.setLocalidad(request.getParameter("txtLocalidad"));
				u.setDireccion(request.getParameter("txtDireccion"));
				u.setEstado(true);
				
				String fechaTexto = request.getParameter("fechanacimiento").toString();
				u.setFechaNacimiento(fechaTexto);
				
				u.setContraseña(request.getParameter("txtPassword"));
				
				TiposDeUsuarios tu = new TiposDeUsuarios();
				tu.setCod_tipo("3");
				
				u.setTiposDeUsuarios(tu);
				
				u.toString();
				
				if(un.insertarUsuario(u)) {
					
					if(request.getSession()!=null) {
						
						String mensaje="El paciente se agrego correctamente";
					
						request.setAttribute("pacienteAgregado",mensaje);
						RequestDispatcher rd = request.getRequestDispatcher("/CrearPaciente.jsp");
						rd.forward(request,response);
					}
					else {
						String mensaje = request.getSession().toString();
						
						request.setAttribute("pacienteAgregado",mensaje);
						RequestDispatcher rd = request.getRequestDispatcher("/CrearPaciente.jsp");
						rd.forward(request,response);
					}
				}else {
					String mensaje = "No se pudo agregar el usuario";
					
					request.setAttribute("mensaje",mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/CrearPaciente.jsp");
					rd.forward(request,response);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/CrearPaciente.jsp");
				rd.forward(request,response);
			}
			String dniRepetido="El DNI ingresado ya existe";
			
			request.setAttribute("dniRepetido",dniRepetido);
			RequestDispatcher rd = request.getRequestDispatcher("/CrearPaciente.jsp");
			rd.forward(request,response);
		}
		
	}

}
