<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.DTO.Clinica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pide tu cita</title>

    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
    crossorigin="anonymous"
  />
    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/pedirCita.css">

</head>

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
        <li><a href="nuestroEquipo.html">Nuestro equipo</a></li>
        <li><a href="">Tratamientos</a></li>
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesi�n/Registrarse</a></li>
        </c:if>
        <li><a class="activo" href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <c:if test="${tipoLogin eq 'cliente'}">
        <li><a href="EditarPerfil">Editar Perfil</a></li>
        </c:if>
        <c:if test="${tipoLogin ne 'ninguno'}">
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li> <%//TODO Arreglarlo, porque al a�adir mas campos se sale. %>
        </c:if>
      </ul>
    </nav>
  </header>

<body>
  
    <main>
        <section style="padding-top: 100px;">
            <div class="form-box1">
                <div class="form-value">
                    <form class="form-inline" action="RealizarCita" method="POST">
                        <h2>Pedir cita</h2>
                        <div class="inputbox">
                            <label for="clinica">Cl�nica</label>
						      <c:if test = "${clinica == null}">
						       <select name="ID_Clinica" required="required" style="margin-left: 70px; margin-bottom: 2px; background-color: rgb(255, 255, 255); color: rgb(0, 140, 255);">
							</c:if>
							<c:if test = "${clinica != null}">
						      	 <select name="ID_Clinica" value="${clinica}" required="required" style="margin-left: 70px; margin-bottom: 2px; background-color: rgb(255, 255, 255); color: rgb(0, 140, 255);">
							</c:if>
						    <c:forEach var="clinica" items="${clinicas}">
						        <option value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
						    </c:forEach>
							</select>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-id-card"></i>
                            <c:if test = "${dni == null}">
						       <input type="text" id="dni" name="dni" required="required">
						       <label for="dni">DNI</label>
						       
							</c:if>
							<c:if test = "${dni != null}">
						      	 <input type="text" name="dni" value="${dni}" required="required" disabled="disabled">
						       	<label style="margin-top:-30px">DNI</label> 	 
							</c:if>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-calendar-alt"></i>
                            <c:if test = "${fecha == null}"> <%//TODO A�adir limite a la fecha%>
						       <input type="date" id="fecha" name="fecha" required="required">
							</c:if>
							<c:if test = "${fecha != null}">
						        <input type="date" id="fecha" name="fecha" value="${fecha}" required="required">
							</c:if>
                            <label class="fecha" for="fecha">Fecha</label>
                        </div>

                        <div class="inputbox">
                            <i class="fas fa-clock"></i>
                             <c:if test = "${hora == null}">
						       <input type="time" id="hora" name="hora" required="required">
							</c:if>
							<c:if test = "${hora != null}">
						      	 <input type="time" id="hora" name="hora" value="${hora}" required="required">
							</c:if>
                            <label class="hora" for="hora">Hora</label>
                        </div>
                      
                        <button type="submit">Pedir cita</button>
                    </form>
                </div>
            </div>
        </section>
    </main>
    
</body>

<footer class="footer">
    <div class="footer-izquierda col-md-4 col-sm-6">
      <p class="about">
        <span> Sobre Smiling</span> Ut congue augue non tellus bibendum, in
        varius tellus condimentum. In scelerisque nibh tortor, sed rhoncus odio
        condimentum in. Sed sed est ut sapien ultrices eleifend. Integer tellus
        est, vehicula eu lectus tincidunt, ultricies feugiat leo. Suspendisse
        tellus elit, pharetra in hendrerit ut, aliquam quis augue. Nam ut nibh
        mollis, tristique ante sed, viverra massa.
      </p>

      <div class="iconos">
        <ul>
          <li>
            <a href="#"> <i class="fab fa-facebook-f icon"></i> </a>
          </li>
          <li>
            <a href="#"><i class="fab fa-twitter icon"></i></a>
          </li>
          <li>
            <a href="#"><i class="fab fa-linkedin-in icon"></i></a>
          </li>
          <li>
            <a href="#"><i class="fab fa-instagram icon"></i></a>
          </li>
        </ul>
      </div>
    </div>
    <div class="footer-centro col-md-4 col-sm-6">
      <div>
        <i class="fa fa-map-marker"></i>
        <p><span> Ciudad y calle</span> Puerta del sol, Madrid</p>
      </div>
      <div>
        <i class="fa fa-phone"></i>
        <p>(+34) 688 71 85 21</p>
      </div>
      <div>
        <i class="fa fa-envelope"></i>
        <p><a href="mailto:ikbdb@plaiaundi.net"> smiling@hotmail.com</a></p>
      </div>
    </div>
    <div class="footer-derecha col-md-4 col-sm-6">
      <img src="img/logoProv.png" width="100" />
      <p class="name">Smiling &copy; 2023</p>
    </div>
  </footer>
