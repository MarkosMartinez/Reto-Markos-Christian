<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.DTO.Clinica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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

<c:if test = "${aviso == 'dninoregistrado'}">
        <div class='alert alert-danger' role='alert'>El usuario no esta registrado! Prueba a registrarte <a href="">aqui</a>!</div>
</c:if>
<c:if test = "${aviso == 'demasiadascitas'}">
        <div class='alert alert-danger' role='alert'>Hay demasiadas citas ese dia a esa hora, prueba otro dia!</div>
</c:if>

<form class="form-inline" action="RealizarCita" method="POST">   
<div class="mb-3">
    <label for="ID_Clinica" class="form-label">Clinicas Disponibles:</label>
    	<select class="form-control form-control-sm" name="ID_Clinica" required="required">
    <c:forEach var="clinica" items="${clinicas}">
        <option value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
    </c:forEach>
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