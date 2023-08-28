<%@page import="entidad.Usuarios" %>
<%@page import="entidad.Medicos" %>
<%@page import="entidad.Especialidades" %>
<%@page import="entidad.Turnos" %>
<%@page import="daoImpl.TurnosDaoImpl" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="javascript.js"></script>
</head>
<body>
<%
	
	ArrayList<Turnos> lt = null;
	if(request.getAttribute("listaTurnos")!=null){
		lt = (ArrayList<Turnos>)request.getAttribute("listaTurnos");
	}
%>

<%
	Medicos m = null;
	String medico = "";
	
	if((Medicos)session.getAttribute("Medico")!= null){
		m = (Medicos)session.getAttribute("Medico");
		medico = m.getNombre() + " " + m.getApellido();
	}
	
	String mensaje = "";
	if(request.getAttribute("mensaje")!= null){
		mensaje = (String) request.getAttribute("mensaje");
	} 
%>

<a href="servletTurnosMedicos?Param=1">Visualizar Turnos</a>
&emsp;

<label><%= medico %> <a href="servletInicioAdmin?Param=CS">Cerrar Sesion</a></label>

<form action="servletAsignacionTurnos" method="post">
	<h1>Turnos</h1>
	<fieldset>
		<label><%=mensaje %></label>
		<table id="tablaMedicosxTurnosDisponibles" class="display" border =2>
			    <thead>
			        <tr>
			       		<th>CodigoTurno</th>
			            <th>Medico</th>
			            <th>Especialidad</th>
			            <th>Paciente</th>
			            <th>Hora</th>
			            <th>Estado</th>
			            <th>Cambiar Estado</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<%
			    	for(Turnos t : lt){
			    	%><form action="servletTurnosMedicos" method="post">
			    	   	<tr> 
				    		<td><%= t.getCod_turno() %> <input type="hidden" name="codTurno" value="<%= t.getCod_turno() %>"></td>
				    		<td><%= t.getDni_medico() %></td>
				    		<td><%= t.getCod_especialidad() %></td>
				    		<td><%= t.getDni_paciente() %></td>
				    		<td><%= t.getHora() %></td>
				    		<td>
					    		<select name="slEstados">
					    			<option value="3">AUSENTE </option>
					    			<option value="4">PRESENTE</option>
					    		</select>
				    		</td>
				    		<td><input type="submit" value="Cambiar Estado" name="btnCambiarEstado"></td>
						</tr>
						</form>
					<%
			    	}
			    	%>
				</tbody>
			</table>
	</fieldset>
</form>


</body>
</html>