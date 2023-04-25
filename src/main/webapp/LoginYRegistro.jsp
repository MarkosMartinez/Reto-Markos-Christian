<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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

<header>
    <nav>
      <input type="checkbox" id="check" />
      <label for="check" class="botonmenu">
        <i class="fas fa-bars"></i>
      </label>
      <a href="#" class="enlaceLogo">
        <img src="img/logoProv.png" class="logo" />
      </a>
      <ul class="opciones">
        <li><a href="Principal">Inicio</a></li>
        <li><a href="nuestroEquipo.html">Nuestro equipo</a></li>
        <li><a href="">Tratamientos</a></li>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
      </ul>
    </nav>
  </header>

<body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

    <main>    
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
        
        <section style="padding-top: 100px;">
            <div class="form-box1">
                <div class="form-value">
                     <form action="LoginYRegistro" method="POST">
                     <input type="text" value="login" name="tipo" hidden>
                        <h2>Iniciar sesión</h2>
                        <div class="inputbox">
                            <i class="fas fa-id-card"></i>
                            <c:if test = "${dnilogin == null}">
					         	<input type="text" name="dni" id="dnilogin"required="required">
						      </c:if>
						      <c:if test = "${dnilogin != null}">
					         	 <input type="text" name="dni" id=dnilogin" value="${dnilogin}" required="required">
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
            <div class="form-box2">
                <div class="form-value">
                    <form action="LoginYRegistro" method="POST">
         				<input type="text" value="registro" name="tipo" hidden>
                        <h2>Registrarse</h2>
                        
                        <div class="inputbox">
                            <i class="fas fa-id-card"></i>
                            <c:if test = "${dni == null}">
					         	<input type="text" name="dni" id="dni" required="required">
						      </c:if>
						      <c:if test = "${dni != null}">
					         	 <input type="text" name="dni" id="dni" value="${dni}" required="required">
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
                            <c:if test = "${fechanacimiento == null}">
				         	<input type="date" name="fechanacimiento" id="fechanacimiento" min='1899-01-01' max='2023-01-01' required="required"> <%//TODO Cambiar la fecha min/max %>
					      </c:if>
					      <c:if test = "${fechanacimiento != null}">
				         	 <input type="date" name="fechanacimiento" id="fechanacimiento" value="${fechanacimiento}" required="required">
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
                            <input type="password" name="pass" id="passregister" required="required">
                            <label for="passregister">Contraseña</label>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="confpass" id="confpass" required="required">
                            <label for="confpass">Confirmar Contraseña</label>
                        </div>
                        <button class="boton" type="submit">Registrarse</button>
                    </form>
                </div>
            </div>
        </section>  

        
    </main>
  

</body>
</html>