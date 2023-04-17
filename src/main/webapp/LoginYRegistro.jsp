<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
         <input type="text" value="registro" hidden>
           <input type="text" placeholder="DNI" name="DNI">
           <input type="text" placeholder="Nombre y Apellido" name="nombreyapellido">
           <input type="text" placeholder="Nombre de Usuario" name="username">
           <input type="email" placeholder="Correo" name="correo">
           <input type="date" placeholder="Fecha de Nacimiento" name="fechanacimiento">
           <input type="password" placeholder="Contraseña" name="pass">
           <input type="password" placeholder="Confirma la contraseña" class="confpass">
           <input type="submit" class="submit" value="Registrarse">
         </form>
       </div>
       <div class="login">
         <h2>Iniciar Sesión</h2>
         <div class="login-items">
           <form action="LoginYRegistro" method="POST">
           <input type="text" value="login" hidden>
           <input type="text" placeholder="DNI" name="DNI">
           <input type="password" placeholder="Contraseña" name="pass">
           <input type="submit" class="submit" value="Iniciar Sesion">
         </form>
         </div>
       </div>
     </div>
   </div>
</body>
</html>