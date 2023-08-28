<%@page import="entidad.Usuarios" %>
<%@page import="entidad.Medicos" %>
<%@page import="entidad.Especialidades" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
  
  <script type="text/javascript">
    $(document).ready( function () {
        $('#tablaMedicos').DataTable();
    } );
    </script>
    
    
</head>

<%
	ArrayList<Especialidades> le = null;
	if(application.getAttribute("listaEs")!=null){
		le = (ArrayList<Especialidades>)application.getAttribute("listaEs");
	}
	
	ArrayList<Medicos> lm = null;
	if(request.getAttribute("listaMedicos")!=null){
		lm = (ArrayList<Medicos>)request.getAttribute("listaMedicos");
	}
%>

<%
	Usuarios u = null;
	String admin = "";
	
	if((Usuarios)session.getAttribute("Admin")!= null){
		u = (Usuarios)session.getAttribute("Admin");
		admin = u.getNombre() + " " + u.getApellido();
	} 
%>

<a href="AsignacionTurnos.jsp">Asignar Turno</a>
&emsp;

<a href="servletABMLMedico?Param=1"> Listar médicos</a>
&emsp;

<a href="ABMLMedico.jsp">Agregar Médicos</a>
&emsp;

<a href="ABMLPaciente.jsp">Agregar Pacientes</a>
&emsp;

<label><%= admin %> <a href="servletInicioAdmin?Param=CS">Cerrar Sesion</a></label>

<body>
<form action="servletABMLMedico" method="post">
	<h1>Listado de Medicos</h1>
	<fieldset>
	<h2>Filtros</h2>
	

		<label for="txtNombreOApellido">Busqueda por Nombre/Apellido:</label>
		<input id="txtNombreOApellido" name="txtNombreOApellido" type="text">
		<label for="slEspecialidad">Especialidad:</label>
      	<select id="especialidad" name="slEspecialidad">
      		<option value="" hidden>-Seleccione-</option>
      		<%
				if(le !=null){
					for(Especialidades es : le){
						%>
						<option value="<%= es.getCod_especialidad()%>"><%= es.getDescripcion() %></option>
						<%
					}
				}
			%>
      	</select>
      	<label for="slDia">Dia de atencion:</label>
       	<select id="dia" name="slDia">
       		<option value="" hidden>-Seleccione-</option>
       		<option>Lunes</option>
       		<option>Martes</option>
       		<option>Miercoles</option>
       		<option>Jueves</option>
       		<option>Viernes</option>
       	</select>
       	
		<input type="submit" value="Buscar" name="btnBusquedaPersonalizada">
		<input type="submit" value="Limpiar" name="btnLimpiar">

	

		<table id="tablaMedicos" class="display" border =2>
			<thead>
	        <tr>
	            <th>DNI</th>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>sexo</th>
	            <th>Nacionalidad</th>
	            <th>Provincia</th>
	            <th>Localidad</th>
	            <th>Fecha de Nacimineto</th>
	            <th>Direccion</th>
	            <th>Correo Electrónico</th>
	            <th>Telefono</th>
	            <th>Especialidad</th>
	            <th>Dia de atencion</th>
	            <th>Horario de atencion</th>
	            <th>Modificacion</th>
	            <th>Eliminacion</th>
	        </tr>
	    	</thead>
		    <tbody> 
			    <%
					if(lm !=null){
						for(Medicos m : lm){
				%>
					    	<tr> 
							    <form name="seleccionMedico" action="servletABMLMedico" method="post">
									<td><%= m.getDni() %><input type="hidden" name="dniMedico" value=<%= m.getDni() %>></td> 
									<td><%= m.getNombre() %></td>   
									<td><%= m.getApellido() %></td>
									<td><%= m.getSexo() %></td>
									<td><%= m.getNacionalidad() %></td>
									<td><%= m.getProvincia() %></td>
									<td><%= m.getLocalidad() %></td>
									<td><%= m.getFechaNacimiento() %></td>
									<td><%= m.getDireccion() %></td>
									<td><%= m.getMail() %></td>
									<td><%= m.getTelefono() %></td>
									<td><%= m.getEspecialidad().getDescripcion() %></td>
									<td><%= m.getDiasAtencion() %></td>
									<td><%= m.getHorariosAtencion() %></td>
									<td> <input type="submit" name="btnModificar" value="Modificar"></td>
									<td> <input type="submit" name="btnEliminar" value="Eliminar"></td>
								</form> 
							</tr>
				<%
						}
					}
				%>
			</tbody>
		</table>
	</fieldset>
</form>
		    

</body>
</html>