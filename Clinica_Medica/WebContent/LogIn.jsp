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
	String dniIncorrecto = "";
	if(request.getAttribute("dniIncorrecto")!= null){
		dniIncorrecto = (String) request.getAttribute("dniIncorrecto");
	} 
	String contIncorrecta = "";
	if(request.getAttribute("contIncorrecta")!= null){
		contIncorrecta = (String) request.getAttribute("contIncorrecta");
	} 
%>
	<form action="servletLogIn" method="post">
		<h1>Iniciar Sesion</h1>
		<fieldset>
			<p>
				<label for="dni">DNI:</label>
				<input id="dni" type="number" placeholder="Ingrese su DNI" required name="txtDNI">&emsp;<label class="rojo"><%=dniIncorrecto %></label>
		    </p>
		    <p>
				<label for="contraseña">Contraseña:</label>
				<input id="contraseña" type="password" placeholder="Ingrese su Contraseña" required name="txtContraseña">&emsp;<label class="rojo"><%=contIncorrecta %></label>
		    </p>
		    <p>
		    	<input name="btnIniciarSesion" value="Iniciar Sesion" requiered type="submit">
		    	
		    	&emsp;
		    	
		    	<a href="ElegirTipo.jsp"> Crear Usuario </a>
		    
		    </p>
		    
	    </fieldset>
	</form>
</body>
</html>