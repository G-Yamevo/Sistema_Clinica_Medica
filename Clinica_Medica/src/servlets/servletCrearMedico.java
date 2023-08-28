package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EspecialidadesDao;
import daoImpl.EspecialidadesDaoImpl;
import entidad.Especialidades;
import entidad.Medicos;
import entidad.FechaActual;
import negocio.EspecialidadesNegocio;
import negocio.MedicosNegocio;
import negocio.TipoDeUsuarioNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.MedicosNegocioImpl;
import negocioImpl.EspecialidadesNegocioImpl;
import negocioImpl.TipoDeUsuarioNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import excepciones.HorarioAtencionExcepcion;
import excepciones.FechaMayorExcepcion;

@WebServlet("/servletCrearMedico")
public class servletCrearMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletCrearMedico() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			if(request.getParameter("Param").equals("1")) {

				EspecialidadesNegocioImpl en = new EspecialidadesNegocioImpl();
				
				ArrayList<Especialidades> le = en.readAll();
				
				request.setAttribute("listaEsp",le);
				RequestDispatcher rd = request.getRequestDispatcher("/CrearMedico.jsp");
				rd.forward(request,response);
			}
		}
		if(request.getParameter("Param")!=null) {
			if(request.getParameter("Param").equals("CS")) {
			
				request.getSession().removeAttribute("Admin");
				
				RequestDispatcher rd = request.getRequestDispatcher("/LogIn.jsp");
				rd.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if(request.getParameter("btnAgregarMedico") !=null) {
			UsuarioNegocio un = new UsuarioNegocioImpl();
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			String dni = (String)request.getAttribute("txtDni");
			
			if(!un.existeUsuario(dni)) {
				
				if(mn.insertarMedico(cargarMedico(request))) {
					
					String mensaje = "El Médico se agrego correctamente";
						
					request.setAttribute("medicoAgregado",mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/CrearMedico.jsp");
					rd.forward(request,response);

					
				}else {
					String mensaje = "No se pudo agregar el medico";
					
					request.setAttribute("mensaje",mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/CrearMedico.jsp");
					rd.forward(request,response);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/CrearMedico.jsp");
				rd.forward(request,response);
			}
			String dniRepetido="El DNI ingresado ya existe";
			
			request.setAttribute("dniRepetido",dniRepetido);
			RequestDispatcher rd = request.getRequestDispatcher("/CrearMedico.jsp");
			rd.forward(request,response);
		}
		
		if(request.getParameter("btnBusquedaPersonalizada")!=null) {
			
			String consulta = "";
			boolean sumaFiltros = false;
			boolean where = false;
			
			String campoNombre = request.getParameter("txtNombreOApellido").toString();
			if(campoNombre.length() != 0) {
				consulta += " INNER JOIN usuarios ON nombre LIKE '%"+ campoNombre +"%' OR apellido LIKE '%"+ campoNombre +"%' WHERE medicos.dni = usuarios.dni";
				where = true;
				sumaFiltros = true;
			}
			
			String campoEspecialidad = request.getParameter("slEspecialidad").toString();
			if(campoEspecialidad.length()!=0) {
				if (sumaFiltros == true) {
					consulta += " AND ";
				}
				if(!where){
					consulta += " WHERE ";
				}
				consulta += "cod_Especialidad = '" + campoEspecialidad + "'";
				sumaFiltros = true;
			}
			
			String campoDia = request.getParameter("slDia").toString();
			if(campoDia.length()!=0) {
				if (sumaFiltros == true) {
					consulta += " AND ";
				} 
				if(!where){
					consulta += " WHERE ";
				}
				consulta += "dias_Atencion = '" + campoDia + "'";
				sumaFiltros = true;
			}
			
			/*String campoHorario = request.getParameter("slHorario").toString();
			if(campoHorario.length()!=0) {
				if (sumaFiltros == true) {
					consulta += " AND ";
				}
				if(!where){
					consulta += " WHERE ";
				}
				consulta += "horarios_Atencion = " + campoHorario;
				sumaFiltros = true;
			}*/ //CORREGIR LA BBDD PARA  COMPARAR
			
			consulta += ";";
			System.out.println(consulta);
			
			
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			ArrayList<Medicos> lm = (ArrayList<Medicos>) mn.busquedaPersonalizada(consulta);
			
			request.setAttribute("listaMedicos",lm);
			RequestDispatcher rd = request.getRequestDispatcher("/ListadoMedicos.jsp");
			rd.forward(request,response);
		}
		
		if(request.getParameter("btnLimpiar") != null) {
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			ArrayList<Medicos> lm = (ArrayList<Medicos>) mn.readAll();
			
			request.setAttribute("listaMedicos",lm);
			RequestDispatcher rd = request.getRequestDispatcher("/ListadoMedicos.jsp");
			rd.forward(request,response);
		}
		
		
		if(request.getParameter("btnEliminar")!=null)
		{
			String dni = (String) request.getParameter("dniMedico");
			
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			mn.eliminarMedico(mn.obtenerMedico(dni));
			
			ArrayList<Medicos> lm = (ArrayList<Medicos>) mn.readAll();
			
			request.setAttribute("listaMedicos",lm);
			RequestDispatcher rd = request.getRequestDispatcher("/ListadoMedicos.jsp");
			rd.forward(request,response);
		}
		
		if(request.getParameter("btnModificar")!=null)
		{
			String dni = (String) request.getParameter("dniMedico");
			
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			Medicos m = mn.obtenerMedico(dni);
			
			request.setAttribute("MedicoMod",m);
			RequestDispatcher rd = request.getRequestDispatcher("/ModificarMedico.jsp");
			rd.forward(request,response);
		}
		
		if(request.getParameter("rModificacion")!=null){
			MedicosNegocio mn = new MedicosNegocioImpl();
			String dni = (String) request.getParameter("txtDni");
			
			Medicos m = mn.obtenerMedico(dni);
			m = cargarMedicoModificar(m,request);
			
			if(mn.modificarMedico(m)) {
				String mensaje = "El medico se modifico correctamente";
				
				m = mn.obtenerMedico(dni);
				
				request.setAttribute("MedicoMod",m);
				
				request.setAttribute("mensaje",mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/ModificarMedico.jsp");
				rd.forward(request,response);
				
			}else {
				String mensaje = "No se pudo modificar el medico";
				
				m = mn.obtenerMedico(dni);
				
				request.setAttribute("MedicoMod",m);
				
				request.setAttribute("mensaje",mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/ModificarMedico.jsp");
				rd.forward(request,response);
			}
		}
		
		if(request.getParameter("Cancelar")!=null)
		{
			MedicosNegocio mn = new MedicosNegocioImpl();
			
			ArrayList<Medicos> lm = (ArrayList<Medicos>) mn.readAll();
			
			request.setAttribute("listaMedicos",lm);
			RequestDispatcher rd = request.getRequestDispatcher("/ListadoMedicos.jsp");
			rd.forward(request,response);
		}
	}
	
	public Medicos cargarMedico(HttpServletRequest request) {

		String fechaTexto = request.getParameter("fechanacimiento").toString();
		String horario= (String)request.getParameter("horaI")+" - " +(String)request.getParameter("horaF");
		
		FechaActual fecha= new FechaActual();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.US);
		LocalDate date = LocalDate.parse(fechaTexto, formatter);
		
		TipoDeUsuarioNegocio tdun = new TipoDeUsuarioNegocioImpl();
		Medicos m = new Medicos();
		
		m.setDni((String)request.getParameter("txtDni"));
		m.setNombre((String)request.getParameter("txtNombre"));
		m.setApellido((String)request.getParameter("txtApellido"));
		m.setNacionalidad((String)request.getParameter("txtNacionalidad"));
		m.setSexo((String)request.getParameter("slSexo"));	
		m.setTelefono((String)request.getParameter("txtTelefono"));
		m.setMail((String)request.getParameter("txtMail"));
		m.setProvincia((String)request.getParameter("txtProvincia"));
		m.setLocalidad((String)request.getParameter("txtLocalidad"));
		m.setDireccion((String)request.getParameter("txtDireccion"));
		m.setEstado(true);
		
		try {
			fecha.verificarFecha(date);
		}
		catch(FechaMayorExcepcion e){
			e.printStackTrace();
		}
		
		m.setFechaNacimiento(fechaTexto);
		m.setDiasAtencion((String)request.getParameter("slDia"));
		
		try {
			m.validarHorario(horario);
		}
		catch(HorarioAtencionExcepcion e){
			e.printStackTrace();
		}
		
		m.setHorariosAtencion(horario);
		Especialidades especialidad = new Especialidades();
		especialidad.setCod_especialidad((String)request.getParameter("slEspecialidad"));
		m.setEspecialidad(especialidad);
		
		m.setContraseña((String)request.getParameter("txtContrasenia"));
		m.setTiposDeUsuarios(tdun.obtenerTiposDeUsuariosCod("2"));
		
		System.out.println("El usuario: " + m.toString());
		
		return m;
	}
	
	public Medicos cargarMedicoModificar(Medicos m, HttpServletRequest request) {

		String fechaTexto = request.getParameter("sldia") + "/" + request.getParameter("slmes") + "/" + request.getParameter("slanio");
		EspecialidadesDao ed = new EspecialidadesDaoImpl();
		
		m.setNombre((String)request.getParameter("txtNombre"));
		m.setApellido((String)request.getParameter("txtApellido"));
		m.setNacionalidad((String)request.getParameter("txtNacionalidad"));
		m.setSexo((String)request.getParameter("slSexo"));	
		m.setTelefono((String)request.getParameter("txtTelefono"));
		m.setMail((String)request.getParameter("txtMail"));
		m.setProvincia((String)request.getParameter("txtProvincia"));
		m.setLocalidad((String)request.getParameter("txtLocalidad"));
		m.setDireccion((String)request.getParameter("txtDireccion"));
		m.setFechaNacimiento((String)request.getParameter("txtFecha"));
		m.setDiasAtencion((String)request.getParameter("slDia"));
		m.setHorariosAtencion((String)request.getParameter("horaI")+" - " +(String)request.getParameter("horaF"));
		
		m.setEspecialidad(ed.obtenerEspecialidad((String)request.getParameter("slEspecialidad")));
		
		return m;
	}
}
