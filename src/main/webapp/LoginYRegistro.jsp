<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
 
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
 
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      rel="stylesheet"
    />	
    
      <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
      crossorigin="anonymous"
    />
    
     
  <link rel="stylesheet" href="Estilos/LoginYRegistro.css">
  <link rel="stylesheet" href="css/menu.css">
  <link rel="stylesheet" href="css/Login.css">
 
  <title>Iniciar sesión / Registrarse</title>
</head>

  <!--Inicio del menu de navegación-->

<header>
    <nav>
      <input type="checkbox" id="check" />
      <label for="check" class="botonmenu">
        <i class="fas fa-bars"></i>
      </label>
      <a href="Principal" class="enlaceLogo">
        <img src="img/logoProv.png" class="logo" />
      </a>
      <ul class="opciones">
        <li><a href="Principal">Inicio</a></li>
        <li><a href="NuestroEquipo">Nuestro equipo</a></li>
        <li><a href="Tratamientos">Tratamientos</a></li>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
       	<li><a class="activo" href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
      </ul>
    </nav>
  </header>
  <!--Fin del menu de navegación-->

<body>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <main>    
    
    	<fmt:formatDate value="${hoy}" pattern="dd/MM/yyyy" />
        <%Date hoy = new Date();%>
		<c:set var="hoy" value="<%= hoy %>" />
    
    <!-- Avisos que aparecerán depende de las acciones del usuario -->
			    <c:if test="${aviso eq 'error'}">
				<div class="alerta">
			     <div class="alert alert-danger danger alert-dismissible fade show" role="alert">
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			 <i class="fa-solid fa-triangle-exclamation fa-bounce" style="color: #ffffff;"></i>
			  &nbsp; &nbsp;
			  <span>Error al iniciar sesion!</span>
			</div>
		 </div>
			 </c:if>
		
			<c:if test="${aviso eq 'contrasenanovalida'}">
				<div class="alerta">
			     <div style="width: 240px" class="alert alert-danger danger alert-dismissible fade show" role="alert">
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			 <i class="fa-solid fa-key fa-flip fa-lg"></i>
			  &nbsp; &nbsp;
			  <span>Contraseñas no validas!</span>
			</div>
		 </div>
			 </c:if>
			 
			 
			 <c:if test="${aviso eq 'dniexistente'}">
				<div class="alerta">
			     <div style="width: 205px" class="alert alert-danger danger alert-dismissible fade show" role="alert">
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			 <i class="fa-solid fa-id-card fa-beat fa-lg" style="color: #ffffff;"></i>
			  &nbsp; &nbsp;
			  <span>DNI ya registrado!</span>
			</div>
		 </div>
			 </c:if>	 
          <!--Fin de avisos-->

        <section style="padding-top: 100px;">
          <!-- Primer contenedor para el formulario de inicar sesion -->
            <div class="form-box1">
                <div class="form-value">
                     <form action="LoginYRegistro" method="POST">
                     <input type="text" value="login" name="tipo" hidden>
                        <h2>Iniciar sesión</h2>
                        <div class="inputbox">
                            <i class="fas fa-id-card"></i>
                            <c:if test = "${dnilogin == null}">
					         	<input type="text" name="dni" id="dnilogin"required="required" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9">
						      </c:if>
						      <c:if test = "${dnilogin != null}">
					         	 <input type="text" name="dni" id="dnilogin" value="${dnilogin}" required="required" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9">
						     </c:if>
                            <label for="dnilogin">DNI</label>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="pass" id="pass" required="required">
                            <label for="pass">Contraseña</label>
                        </div>
                        <button class="boton" type="submit">Login</button>
                    </form>
                </div>
            </div>
            <!-- Fin primer contenedor y formulario -->

            <!-- Contenedor y formulario para registrarse -->
            <div class="form-box2">
                <div class="form-value">
                    <form action="LoginYRegistro" method="POST">
         				<input type="text" value="registro" name="tipo" hidden>
                        <h2>Registrarse</h2>
                        
                        <div class="inputbox">
                            <i class="fas fa-id-card"></i>
                            <c:if test = "${dni == null}">
					         	<input type="text" name="dni" id="dni" required="required" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9">
						      </c:if>
						      <c:if test = "${dni != null}">
					         	 <input type="text" name="dni" id="dni" value="${dni}" required="required" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9">
						     </c:if>
                            <label for="dni">DNI</label>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-signature"></i>
                            <c:if test = "${nombre != null}">
					         	 <input type="text" name="nombre" id="nombre" value="${nombre}" required="required">
						      </c:if>
						      <c:if test = "${nombre == null}">
					         	 <input type="text" name="nombre" id="nombre" required="required">
						    </c:if>
                            <label for="nombre">Nombre</label>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-signature"></i>
                            <c:if test = "${apellido == null}">
					         	<input type="text" name="apellido" id="apellido" required="required">
						      </c:if>
						      <c:if test = "${apellido != null}">
					         	<input type="text" name="apellido" id="apellido" value="${apellido}" required="required">
						      </c:if>
                            <label for="apellido">Apellidos</label>
                        </div>
                        <div class="inputbox">
                            <i class="fa-solid fa-envelope" style="color: #ffffff;"></i>
                            <c:if test = "${correo == null}">
				         	<input type="email" name="correo" id="correo" required="required">
					      </c:if>
					      <c:if test = "${correo != null}">
				         	 <input type="email" name="correo" id="correo" value="${correo}" required="required">
					      </c:if>
                            <label for="correo">Email</label>
                        </div>
                        <div class="inputbox">
                                                    <i class="fas fa-calendar-alt"></i>
                        
                            <c:if test = "${fechanacimiento == null}">
				         	<input type="date" name="fechanacimiento" id="fechanacimiento" min='1899-01-01' max="<fmt:formatDate value="${hoy}" pattern='yyyy-MM-dd' />" value="${fecha}" required="required">
					      </c:if>
					      <c:if test = "${fechanacimiento != null}">
				         	 <input type="date" name="fechanacimiento" id="fechanacimiento" value="${fechanacimiento}" min='1899-01-01' max="<fmt:formatDate value="${hoy}" pattern='yyyy-MM-dd' />" value="${fecha}" required="required">
					      </c:if>
                            <label class="fecha" for="fechanacimiento">Fecha de Nacimiento</label>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-phone-volume"></i>
                            <c:if test = "${telefono == -1}">
				         	<input type="number" min="600000000" id="telefono" max="999999999" name="telefono" required="required">
					      </c:if>
					      <c:if test = "${telefono != -1}">
				         	 <input type="tel" name="telefono" id= "telefono" value="${telefono}" required="required">
					      </c:if>
                            <label for="telefono">Teléfono</label>
                        </div>                 
                        <div class="inputbox">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="pass" id="passregister" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="La contraseña debe de tener una longitud minima de 6 caracteres, con mayusculas, minusculas y numeros">
                            <label for="passregister">Contraseña</label>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="confpass" id="confpass" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="La contraseña debe de tener una longitud minima de 6 caracteres, con mayusculas, minusculas y numeros">
                            <label for="confpass">Confirmar Contraseña</label>
                        </div>
                        <button class="boton" type="submit">Registrarse</button>
                    </form>
                </div>
            </div>
            <!-- Fin contenedor y formulario para registrarse -->
        </section>  

        
    </main>
  

</body>
</html>