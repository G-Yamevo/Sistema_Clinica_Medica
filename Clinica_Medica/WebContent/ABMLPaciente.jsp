<%@page import="entidad.Usuarios" %>
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
	Usuarios u = null;
	String admin = "";
	
	if((Usuarios)session.getAttribute("Admin")!= null){
		u = (Usuarios)session.getAttribute("Admin");
		admin = u.getNombre() + " " + u.getApellido();
	} 
%>

<%	String mensajePacienteAgrregado = "";
	if(request.getAttribute("pacienteAgregado")!= null){
		mensajePacienteAgrregado = (String) request.getAttribute("pacienteAgregado");
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


	<form action="servletABMLPaciente" method="post">
	  <h1>Ingrese los datos del paciente</h1>
	
		<h4><%=  mensajePacienteAgrregado %> </h4>
		
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
	        	<label for="slSexo">Sexo:</label>
	        	<select id="slSexo" name="slSexo">
	        		<option value="M">Masculino</option>
	        		<option value="F">Femenino</option>
	        		<option value="O">Otro</option>
	        	</select>
	     	 </p>
	     	 
	     	 <p>
	        	<label for="txtNacionalidad">Nacionalidad:</label> <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<input id="nacionalidad" type="text" placeholder="Ingrese nacionalidad" required name="txtNacionalidad"></input>
	     	 </p>
	     	 
	     	 <p>
	        	<label for="txtProvincia">Provincia:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<input id="provincia" type="text" placeholder="Ingrese provincia" required name="txtProvincia"></input>

	     	 </p>   
	     	 
	     	  <p>
	        	<label for="txtLocalidad">Localidad:</label>  <!-- Luego va a ser llenado con datos de la base de datos -->
	        	<input id="localidad" type="text" placeholder="Ingrese localidad" required name="txtLocalidad"></input>

	     	 </p>   	
	     	 
	     	 <p>
				<label for="fechanacimiento">Fecha de Nacimiento:</label>
				<input type="date" name="fechanacimiento" id="fechanacimiento" required></input>
			 </p>
	      	
	      	<p>
	        	<label for="txtDireccion">Dirección:</label>
	        	<input id="direccion" type="text" placeholder="Ingrese dirección" required name="txtDireccion">
	    
	     	 </p>
	     	 
	     	 <p>
	        	<label for="txtMail">Mail:</label>
	        	<input id="txtMail" type="email" placeholder="Ingrese correo" required name="txtMail"></input>
	    	  </p>
	      
	      	<p>
				<label for="telefono">Teléfono:</label>
				<input id="telefono" type="tel" placeholder="0112345678" name="txtTelefono" required></input>
	        </p>
	        
	         <p>
				<label for="txtPassword">Contraseña:</label>
				<input id="password" type="password" name="txtPassword" required></input>
	        </p>
	     	
	     	 <p>
	        	<input id="btnAgregarPaciente" type="submit" value="Agregar Paciente" required name="btnAgregarPaciente"></input>
	     	 </p>
	    </fieldset>
	
	</form>

</body>
</html>