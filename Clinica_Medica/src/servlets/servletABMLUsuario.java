package servlets;

import entidad.Especialidades;
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

@WebServlet("/servletABMLUsuario")
public class servletABMLUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletABMLUsuario() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnCrearUsuario")!=null){
			
			String fechaTexto = request.getParameter("fechanacimiento").toString();
			UsuarioNegocio un= new UsuarioNegocioImpl();
			TipoDeUsuarioNegocio tdun = new TipoDeUsuarioNegocioImpl();
			
			String dni = (String)request.getAttribute("txtDni");
			
			if(!un.existeUsuario(dni)){
				Usuarios u = new Usuarios();
				u.setDni(request.getParameter("txtDni"));
				u.setNombre(request.getParameter("txtNombre"));
				u.setApellido(request.getParameter("txtApellido"));
				u.setNacionalidad(request.getParameter("txtNacionalidad"));
				u.setSexo(request.getParameter("slsexo"));	
				u.setTelefono(request.getParameter("txtTelefono"));
				u.setMail(request.getParameter("txtMail"));
				u.setProvincia(request.getParameter("txtProvincia"));
				u.setLocalidad(request.getParameter("txtLocalidad"));
				u.setDireccion(request.getParameter("txtDireccion"));
				u.setEstado(true);
				u.setFechaNacimiento(fechaTexto);
				u.setContraseña(request.getParameter("txtContrasenia"));
				u.setTiposDeUsuarios(tdun.obtenerTiposDeUsuariosDes("slTipo"));
				
				
				if(un.insertarUsuario(u)) {
					String mensaje = "El usuario se agrego correctamente";
					
					request.setAttribute("mensaje",mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/CrearUsuario.jsp");
					rd.forward(request,response);
					
				}else {
					String mensaje = "No se pudo agregar el usuario";
					
					request.setAttribute("mensaje",mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/CrearUsuario.jsp");
					rd.forward(request,response);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/CrearUsuario.jsp");
				rd.forward(request,response);
			}
			String dniRepetido="El DNI ingresado ya existe";
			
			request.setAttribute("dniRepetido",dniRepetido);
			RequestDispatcher rd = request.getRequestDispatcher("/CrearUsuario.jsp");
			rd.forward(request,response);
		}
		
	}

}
