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

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
  
  <script type="text/javascript">
    $(document).ready( function () {
        $('#tablaMedicos').DataTable();
    } );
    </script>
    
     <script type="text/javascript">
    $(document).ready( function () {
        $('#tablaTurnosDisp').DataTable();
    } );
    </script>

<script type="text/javascript" src="javascript.js"></script>
</head>
<body>
<%
	ArrayList<Especialidades> le = null;
	if(application.getAttribute("listaEs")!=null){
		le = (ArrayList<Especialidades>)application.getAttribute("listaEs");
	}
	
	ArrayList<Medicos> lm = null;
	if(request.getAttribute("listaMedicos")!=null){
		lm = (ArrayList<Medicos>)request.getAttribute("listaMedicos");
	}
	String cod = "";
	if(request.getAttribute("cod")!=null){
		cod = (String)request.getAttribute("cod");
	}
	
	ArrayList<Turnos> lt = null;
	if(request.getAttribute("listaTurnos")!=null){
		lt = (ArrayList<Turnos>)request.getAttribute("listaTurnos");
	}
	
	String mensajeTurnoAgregado = "";
	if(request.getAttribute("turnoAgregado")!= null){
		mensajeTurnoAgregado = (String) request.getAttribute("turnoAgregado");
	} 
	
	String mensaje = "";
	if(request.getAttribute("mensaje")!= null){
		mensaje = (String) request.getAttribute("mensaje");
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

<form action="servletAsignacionTurnos" method="post">
	<h1>Asignacion de turnos</h1>
	<fieldset>
		<p>
			<h4><%= mensajeTurnoAgregado %></h4>
			<h4><%= mensaje %></h4>
		</p>
		
		<p>
			<label for="especialidad">Seleccione la Especialidad del medico</label> <!-- Luego va a ser llenado con datos de la base de datos -->
        	<select id="especialidad" name="slEspecialidad">
        		<%
					if(le !=null){
						for(Especialidades es : le){
							if(!cod.equals("")){
								if(es.getCod_especialidad().equals(cod)){
									%>
									<option selected='selected' value="<%= es.getCod_especialidad() %>"><%= es.getDescripcion() %></option>
									<%
								}else{
									%>
									<option value="<%= es.getCod_especialidad() %>"><%= es.getDescripcion() %></option>
									<%
								}
							}else{
								%>
								<option value="<%= es.getCod_especialidad() %>"><%= es.getDescripcion() %></option>
								<%
							}
						}
					}
				%>
        	</select>
        	<input type="submit" value="Seleccionar" name="btnSeleccionarEs">
		</p>
		<%
			if(!cod.equals("")){
		%>
		<p>
			<label for="tablaMedicos">Medicos</label>
			<table id="tablaMedicos" class="display" border =2>
			    <thead>
			        <tr>
			            <th>DNI</th>
			            <th>Nombre</th>
			            <th>Apellido</th>
			        </tr>
			    </thead>
			    <tbody>
			    	  
					    
					    <%
							if(lm !=null){
								for(Medicos m : lm){
								%>
								<tr>
									<td><%=m.getDni() %></td> 
									<td><%=m.getNombre() %></td>   
									<td><%=m.getApellido() %></td>
								</tr>
									<%
								}
							}
						%>
						
					
				</tbody>
			</table>
		</p>
		
		<p>
			<label for="paciente">Paciente</label>
			<input id="paciente" type="number" placeholder="ingrese DNI" name="txtDniPaciente">
		</p>
		
		<label for="tablaTurnosDisp">Medicos</label>
			<table id="tablaTurnosDisp" class="display" border =2>
			    <thead>
			        <tr>
			            <th>Medico</th>
			            <th>Hora</th>
			            <th>Disponibilidad</th>
			        </tr>
			    </thead>
			    <tbody>		    	 
					<form name="seleccionMedico" action="servletAsignacionTurnos" method="post">
					    <%if(lm !=null){
							for(Medicos m : lm){
								for(int i= Integer.parseInt(m.getHorariosAtencion().substring(0,2)); i< Integer.parseInt(m.getHorariosAtencion().substring(8,10));i++){%>
									<tr> 
										<%-- <td><%= m.getNombre()%> <%= m.getApellido()%></td>
										<td><%=i %>:00</td>
										<td><input type="submit" value="Disponible"></td> --%>
										<%if(lt !=null){
											 %>
												<td><%= m.getNombre()%> <%= m.getApellido()%></td>
												<td><%=i %>:00</td>
												<td><input type="submit" value="Disponible" name="btnGuardarTurno"></td>
												<td><input type="hidden" name="Dni" value="<%=m.getDni() %>"></td>
												<td><input type="hidden" name="hora" value="<%=i%>:00"></td>
												<%
											}%> 
									</tr> 
									<%
									}
								}
							}
						%>
						</form> 
					
				</tbody>
			</table>
		
		
		
		<%
			}
		%>
	</fieldset>
</form>


</body>
</html>