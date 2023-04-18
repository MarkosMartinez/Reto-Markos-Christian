<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
 
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
 
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
 
  <link rel="stylesheet" href="Estilos/LoginYRegistro.css">
 
  <title>Login y Registro</title>
</head>
<body>
   <div class="container">
     <div class="login-container">
       <div class="register">
         <h2>Registrarse</h2>
         <form action="LoginYRegistro" method="POST">
         <input type="text" value="registro" name="tipo" hidden>
          <c:if test = "${dni == null}">
         	<input type="text" placeholder="DNI" name="dni" required="required">
	      </c:if>
	      <c:if test = "${dni != null}">
         	 <input type="text" placeholder="DNI" name="dni" value="${dni}" required="required">
	      </c:if>
           <c:if test = "${nombre != null}">
         	 <input type="text" placeholder="Nombre" name="nombre" value="${nombre}" required="required">
	      </c:if>
	      <c:if test = "${nombre == null}">
         	 <input type="text" placeholder="Nombre" name="nombre" required="required">
	      </c:if>
	      <c:if test = "${apellido == null}">
         	<input type="text" placeholder="Apellidos" name="apellido" required="required">
	      </c:if>
	      <c:if test = "${apellido != null}">
         	<input type="text" placeholder="Apellidos" name="apellido" value="${apellido}" required="required">
	      </c:if>
	      <c:if test = "${correo == null}">
         	<input type="email" placeholder="Correo" name="correo" required="required">
	      </c:if>
	      <c:if test = "${correo != null}">
         	 <input type="email" placeholder="Correo" name="correo" value="${correo}" required="required">
	      </c:if>
	      <c:if test = "${fechanacimiento == null}">
         	<input type="date" placeholder="Fecha de Nacimiento" name="fechanacimiento" required="required">
	      </c:if>
	      <c:if test = "${fechanacimiento != null}">
         	 <input type="date" placeholder="Fecha de Nacimiento" name="fechanacimiento" value="${fechanacimiento}" required="required">
	      </c:if>
	      <c:if test = "${telefono == -1}">
         	<input type="number" min="600000000" max="999999999" placeholder="Telefono (Principal)" name="telefono" required="required">
	      </c:if>
	      <c:if test = "${telefono != -1}">
         	 <input type="tel" placeholder="Telefono (Principal)" name="telefono" value="${telefono}" required="required">
	      </c:if>
           <input type="password" placeholder="Contraseña" name="pass" required="required">
           <input type="password" placeholder="Confirma la contraseña" name="confpass" required="required">
           <input type="submit" class="submit" value="Registrarse">
         </form>
       </div>
       <div class="login">
         <h2>Iniciar Sesión</h2>
         <div class="login-items">
           <form action="LoginYRegistro" method="POST">
           <input type="text" value="login" name="tipo" hidden>
           <input type="text" placeholder="DNI" name="dni" required="required">
           <input type="password" placeholder="Contraseña" name="pass" required="required">
           <input type="submit" class="submit" value="Iniciar Sesion">
         </form>
         </div>
       </div>
     </div>
   </div>
</body>
</html>