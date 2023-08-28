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
	Medicos m = null;
	String medico = "";
	
	if((Medicos)session.getAttribute("Medico")!= null){
		m = (Medicos)session.getAttribute("Medico");
		medico = m.getNombre() + " " + m.getApellido();
	} 
%>


<a href="servletTurnosMedicos?Param=1">Visualizar Turnos</a>
&emsp;

<label><%= medico %> <a href="servletInicioAdmin?Param=CS">Cerrar Sesion</a></label>



</body>
</html>