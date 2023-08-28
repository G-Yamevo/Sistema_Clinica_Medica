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
<style type="text/css">
	.rojo{
		color: red;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<%
	String mensaje = "";
	if(request.getAttribute("mensaje")!= null){
		mensaje = (String) request.getAttribute("mensaje");
	}
%>
<%
	ArrayList<Especialidades> le = null;
	if(application.getAttribute("listaEs")!=null){
		le = (ArrayList<Especialidades>)application.getAttribute("listaEs");
	}
	
	Medicos m = null;
	if(request.getAttribute("MedicoMod")!=null){
		m = (Medicos) request.getAttribute("MedicoMod");
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

<label><%=admin %> <a href="servletInicioAdmin?Param=CS">Cerrar Sesion</a></label>

<form action="servletABMLMedico" method="post">
	<h1>Modificar Medico</h1>
	<fieldset>
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
	        </tr>
	    	</thead>
		    <tbody> 
		    	<tr> 
	    			<td><%=m.getDni() %></td> 
					<td><%=m.getNombre() %></td>   
					<td><%=m.getApellido() %></td>
					<td><%=m.getSexo() %></td>
					<td><%=m.getNacionalidad() %></td>
					<td><%=m.getProvincia() %></td>
					<td><%=m.getLocalidad() %></td>
					<td><%=m.getFechaNacimiento() %></td>
					<td><%=m.getDireccion() %></td>
					<td><%=m.getMail() %></td>
					<td><%=m.getTelefono() %></td>
					<td><%=m.getEspecialidad().getDescripcion() %></td>
					<td><%=m.getDiasAtencion() %></td>
					<td><%=m.getHorariosAtencion() %></td>
				</tr>
				<tr> 
			    	<td><%= m.getDni() %><input type="hidden" name="txtDni" value=<%= m.getDni() %>></td>
					<td><input id="nombre" type="text" placeholder="Ingrese nombre" required name="txtNombre" value="<%=m.getNombre() %>"></input></td> 
					<td><input id="apellido" type="text" placeholder="Ingrese apellido" required name="txtApellido" value="<%=m.getApellido() %>"></input></td>   
					<td>
						<select id="sexo" name="slSexo">
			        		<%
							if(m.getSexo().equals("M")){
							%>
							<option selected='selected'>M</option>
							<option>F</option>
							<option>Otro</option>
							<%
							}else{
								if(m.getSexo().equals("F")){
								%>
								<option>M</option>
								<option selected='selected'>F</option>
								<option>Otro</option>
								<%
								}else{
									if(m.getSexo().equals("Otro")){
										%>
										<option>M</option>
										<option >F</option>
										<option selected='selected'>Otro</option>
										<%
									}else{
										%>
										<option>M</option>
										<option >F</option>
										<option>Otro</option>
										<%
									}
								}
							
							}
						%>
			        	</select>
		        	</td>
					<td><input id="nacionalidad" type="text" placeholder="Ingrese nacionalidad" required name="txtNacionalidad" value="<%=m.getNacionalidad() %>"></input></td>
					<td><input id="provincia" type="text" placeholder="Ingrese provincia" required name="txtProvincia" value="<%=m.getProvincia() %>"></input></td>
					<td><input id="localidad" type="text" placeholder="Ingrese localidad" required name="txtLocalidad" value="<%=m.getLocalidad() %>"></input></td>
					<td><input id="fecha" type="date" required name="txtFecha" value="<%=m.getFechaNacimiento() %>"></td>
					<td><input id="direccion" type="txt" placeholder="Ingrese dirección" required name="txtDireccion" value="<%=m.getDireccion() %>"></td>
					<td><input id="mail" type="email" placeholder="Ingrese correo" required name="txtMail" value="<%=m.getMail() %>"></input></td>
					<td><input id="telefono" type="tel" placeholder="0112345678" name="txtTelefono" value="<%=m.getTelefono() %>"></input></td>
					<td>
						<select id="especialidad" name="slEspecialidad">
		        		<%
						if(le !=null){
							for(Especialidades es : le){
								if(es.getCod_especialidad().equals(m.getEspecialidad().getCod_especialidad())){
								%>
								<option value="<%= es.getCod_especialidad()%>" selected='selected'><%= es.getDescripcion() %></option>
								<%
								}else{
									%>
									<option value="<%= es.getCod_especialidad()%>"><%= es.getDescripcion() %></option>
									<%
								}
							}
						}
						%>
		        		</select>
		        	</td>
					<td>
					<select id="dia" name="slDia">
		        		<option>Lunes</option>
		        		<option>Martes</option>
		        		<option>Miercoles</option>
		        		<option>Jueves</option>
		        		<option>Viernes</option>
		        		<option>Sabado</option>  
		        		<option>Domingo</option>
		        	</select>
        			</td>
        			<%
        				String hora[] = m.getHorariosAtencion().split(" - ");
        				String horaI = hora[0];
        				String horaF = hora[1];
        			%>
					<td><input id="horaI" type="time" required name="horaI" value="<%=horaI%>"></input> <label>-</label> <input id="horaF" type="time" required name="horaF" value="<%=horaF %>"></input></td>
				</tr>
			</tbody>
		</table>
		<input type="submit" name="rModificacion" value="Realizar Modificacion">
		<input type="submit" name="Cancelar" value="Cancelar">
		<label class="rojo"><%=mensaje%></label>
	</fieldset>
</form>
</body>
</html>