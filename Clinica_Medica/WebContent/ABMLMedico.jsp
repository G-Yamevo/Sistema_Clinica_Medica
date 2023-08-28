<%@page import="entidad.Usuarios" %>
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
	ArrayList<Especialidades> le = null;
	if(application.getAttribute("listaEs")!=null){
		le = (ArrayList<Especialidades>)application.getAttribute("listaEs");
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

<%	String mensajeMedicoAgregado = "";
	if(request.getAttribute("medicoAgregado")!= null){
		mensajeMedicoAgregado = (String) request.getAttribute("medicoAgregado");
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

<label><%=admin%> <a href="servletInicioAdmin?Param=CS">Cerrar Sesion</a></label>

	<form action="servletABMLMedico" method="post">
	  <h1>Ingrese los datos del Médico</h1>
	  
	  <h4><%= mensajeMedicoAgregado %></h4>
	  	
	    <fieldset>
			<p>
	        	<label for="dni">DNI:</label>
	        	<input id="dni" type="number" placeholder="Ingrese DNI" required name="txtDni"></input>
	     	 </p>
	     	 
	     	 <p>
	        	<label for="nombre">Nombre:</label>
	        	<input id="nombre" type="text" placeholder="Ingrese nombre" required name="txtNombre"></input>
	     	 </p>
	     	 
	      	<p>
	        	<label for="apellido">Apellido:</label>
	        	<input id="apellido" type="text" placeholder="Ingrese apellido" required name="txtApellido"></input>
	     	 </p>
	     	 
	     	 <p>
	        	<label for="slSexo">Sexo:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<select id="slSexo" name="slSexo">
	        		<option value="M" >Masculino</option>
	        		<option value="F" >Femenino</option>
	        		<option value="O" >Otro</option>
	        	</select>
	     	 </p>
	     	 
	     	 <p>
	        	<label for="nacionalidad">Nacionalidad:</label> <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<input id="nacionalidad" type="text" placeholder="Ingrese nacionalidad" required name="txtNacionalidad"></input>
	     	 </p>
	     	 
	     	 <p>
	        	<label for="provincia">Provincia:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<input id="provincia" type="text" placeholder="Ingrese provincia" required name="txtProvincia"></input>
	     	 </p>   
	     	 
	     	  <p>
	        	<label for="localidad">Localidad:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<input id="localidad" type="text" placeholder="Ingrese localidad" required name="txtLocalidad"></input>
	     	 </p>   	
	     	 
	     	 <p>
				<label for="fechanacimiento">Fecha de Nacimiento:</label>
			 	<input id="fechanacimiento" type="date" name="fechanacimiento" required></input>
			 </p>
	      	
	      	<p>
	        	<label for="direccion">Dirección:</label>
	        	<input id="direccion" type="txt" placeholder="Ingrese dirección" required name="txtDireccion">
	        	
	     	 </p>
	     	 
	     	 <p>
	        	<label for="txtMail">Mail:</label>
	        	<input id="txtMail" type="email" placeholder="Ingrese correo" required name="txtMail"></input>
	    	  </p>
	      
	      	<p>
				<label for="telefono">Teléfono:</label>
				<input id="telefono" type="tel" placeholder="0112345678" name="txtTelefono"></input>
	        </p>
	        
	        <p>
	        	<label for="slEspecialidad">Especialidad:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<select id="slEspecialidad" name="slEspecialidad">
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
	     	 </p> 
	     	 
	     	 <p>
	        	<label for="dia horaI horaF">Dia de atencion:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<select id="slDia" name="slDia">
	        		<option>Lunes</option>
	        		<option>Martes</option>
	        		<option>Miercoles</option>
	        		<option>Jueves</option>
	        		<option>Viernes</option>
	        		<option>Sabado</option>  
	        		<option>Domingo</option>
	        	</select>
	        	
	        </p>
	        
	        <p>
	        	<label>Horario de Atencion:</label>
	        	
	        	<select id="horaI" name="horaI">
	        	
	        	<%for(int i=0;i<=24;i++){
	        		
	        		if(i<10){%>
	        			<option>0<%=i%>:00</option>	
	        		<%}
	        		
	        		else{%>
	        		
	        		<option><%=i%>:00</option>	
	        	
	        		<%} %>
	        	<%} %>
				
	        	</select>
	        	
				<label>-</label>
				
				<select id="horaF" name="horaF">
	        	
	        	<%for(int i=0;i<=24;i++){
	        		
	        		if(i<10){%>
        			<option>0<%=i%>:00</option>	
        		<%}
	        		
        		else{%>
        		
        		<option><%=i%>:00</option>	
        	
        			<%} %>	
	        	<%} %>
				
	        	</select>
	     	 </p> 
	     	 
	     	 <p>
	     	 <label>Contraseña:</label>
	     	 <input type="password" name="txtContrasenia">
	     	 </p>
	     	
	     	 <p>
	        	<input id="btnAgregarMedico" type="submit" value="Agregar Médico" required name="btnAgregarMedico"></input>
	     	 </p>
	    </fieldset>
	
	</form>

</body>
</html>