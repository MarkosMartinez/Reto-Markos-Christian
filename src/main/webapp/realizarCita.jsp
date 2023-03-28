<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.DTO.Clinica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Realizar Cita</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>

<%
ArrayList<Clinica> clinicas = (ArrayList<Clinica>) request.getAttribute("clinicas");
%>

<form class="form-inline" action="RealizarCita" method="POST">   
<div class="mb-3">
    <label for="ID_Clinica" class="form-label">Clinicas Disponibles:</label>
    	<select class="form-control form-control-sm" name="ID_Clinica" required="required">
    	<%
    	for(Clinica clinica:clinicas){
    		out.print("<option value='" + clinica.getId_clinica() + "'>" + clinica.getNombre_clinica() + "</option>");
    	}
    	%>
		</select>
  </div>
  <div class="mb-3">
    <label for="dni" class="form-label">DNI:</label>
    <input type="text" class="form-control" id="dni" name="dni" required="required">
  </div>
  <div class="mb-3">
    <label for="fecha" class="form-label">Fecha:</label>
    <input type="date" class="form-control" id="fecha" name="fecha" required="required">
  </div>
  <div class="mb-3">
    <label for="hora" class="form-label">Hora:</label>
    <input type="time" class="form-control" id="hora" name="hora" required="required">
  </div>
  <button type="submit" class="btn btn-primary">Realizar la Cita</button>
</form>

</body>
</html>