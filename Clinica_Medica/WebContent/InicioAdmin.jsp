<%@page import="entidad.Usuarios" %>
<%@page import="entidad.Medicos" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	Medicos m= null;
	Usuarios u = null;
	String usuario = "";
	
	if((Medicos)session.getAttribute("Medico")!= null){
		u = (Medicos)session.getAttribute("Medico");
		usuario = u.getNombre() + " " + u.getApellido();
	}
	
	if((Usuarios)session.getAttribute("Admin")!= null){
		u = (Usuarios)session.getAttribute("Admin");
		usuario = u.getNombre() + " " + u.getApellido();
	} 
	
	if((Usuarios)session.getAttribute("Paciente")!= null){
		u = (Usuarios)session.getAttribute("Paciente");
		usuario = u.getNombre() + " " + u.getApellido();
	}
%>
		<%
		
		if((Usuarios)session.getAttribute("Admin")!=null){%>
			
			<a href="AsignacionTurnos.jsp"> Asignar turno</a>
			&emsp;
			
			<a href="servletABMLMedico?Param=1"> Listar médicos</a>
			&emsp;
			
			<a href="ABMLMedico.jsp"> Agregar médicos</a>
			&emsp;
			
			<a href="ABMLPaciente.jsp"> Agregar pacientes</a>
		<%}
		else if((Usuarios)session.getAttribute("Medico")!=null){%>
		
			<a href="VerTurnosMedico.jsp"> Visualizar Turnos</a>
		
		<%}	
		
		else if((Usuarios)session.getAttribute("Paciente")!=null){%>
		
			<a href="VerTurnosPaciente.jsp"> Visualizar Turnos</a>
			
		<%} %>
			<label><%=usuario %> <a href="servletInicioAdmin?Param=CS">Cerrar Sesion</a></label>
</body>
</html>