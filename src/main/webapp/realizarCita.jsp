<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="modelo.DTO.Clinica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
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
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      rel="stylesheet"
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
        <li><a href="NuestroEquipo">Nuestro equipo</a></li>
        <li><a href="Tratamientos">Tratamientos</a></li>
        <li><a class="activo" href="RealizarCita">Pedir Cita</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <c:if test="${tipoLogin eq 'empleado'}">
        <li><a href="EditarEquipamiento">Editar Equipamiento</a></li>
        </c:if>
        <c:if test="${director eq true}">
        <li><a href="GestionarClinicas">Gestionar Clinicas</a></li>
        </c:if>
        <c:if test="${tipoLogin eq 'empleado'}">
        <li><a href="GestionarUsuarios">Gestionar Usuarios</a></li>
        </c:if>
        <c:if test="${tipoLogin eq 'cliente'}">
        <li><a href="EditarPerfil">Editar Perfil</a></li>
        </c:if>
        <c:if test="${tipoLogin eq 'empleado'}">
        <li><a href="EditarEmpleado">Editar Perfil</a></li>
        </c:if>
        <c:if test="${tipoLogin ne 'ninguno'}">
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
        </c:if>
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
        </c:if>
      </ul>
    </nav>
  </header>

<body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <main>
    
    <c:if test="${aviso eq 'demasiadascitas'}">
      	<div class="alerta">
			     <div class="alert alert-danger danger alert-dismissible fade show" role="alert">
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			 <i class="fa-solid fa-triangle-exclamation fa-bounce" style="color: #ffffff;"></i>
			  &nbsp; &nbsp;
			  <span>Hay demasiadas</span>
			</div>
		 </div>
        </c:if>
        
        
        <c:if test="${aviso eq 'dninoregistrado'}">
      	<div class="alerta">
			     <div style="width:250px;height:90px" class="alert alert-danger danger alert-dismissible fade show" role="alert">
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			 <i class="fa-solid fa-triangle-exclamation fa-bounce" style="color: #ffffff;"></i>
			  &nbsp; &nbsp;
			  <span>Su dni no se encuentra en nuestra base de datos, pruebe registrandose <a style="color:yellow;" href="LoginYRegistro.jsp">aqui</a></span>
			</div>
		 </div>
        </c:if>
        
        <fmt:formatDate value="${hoy}" pattern="dd/MM/yyyy" />
        <%Date hoy = new Date();%>
		<c:set var="hoy" value="<%= hoy %>" />
        
        
        <section style="padding-top: 100px;">
            <div class="form-box1">
                <div class="form-value">
                    <form class="form-inline" action="RealizarCita" method="POST">
                        <h2>Pedir cita</h2>
                        <div class="inputbox">
                            <label for="clinica">Clínica</label>
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
						       <input type="text" id="dni" name="dni" placeholder="xxxxxxxA" required="required" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9">
						       <label style="margin-top:-30px" for="dni">DNI</label>
						       
							</c:if>
							<c:if test = "${dni != null}">
						      	 <input type="text" name="dni" value="${dni}" required="required" readonly pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9">
						       	<label style="margin-top:-30px">DNI</label>
							</c:if>
                        </div>
                        <div class="inputbox">
                            <i class="fas fa-calendar-alt"></i>
                            <c:if test = "${fecha == null}"> <%//TODO Añadir limite maximo a la fecha%>
						       <input type="date" id="fecha" name="fecha" min="<fmt:formatDate value="${hoy}" pattern='yyyy-MM-dd' />" required="required">
							</c:if>
							<c:if test = "${fecha != null}">
						        <input type="date" id="fecha" name="fecha" min="<fmt:formatDate value="${hoy}" pattern='yyyy-MM-dd' />" value="${fecha}" required="required">
							</c:if>
                            <label class="fecha" for="fecha">Fecha</label>
                        </div>

                        <div class="inputbox">
                            <i class="fas fa-clock"></i>
                             <c:if test = "${hora == null}">
						       <input type="time" id="hora" name="hora" min="09:00" max="17:00" required="required">
							</c:if>
							<c:if test = "${hora != null}">
						      	 <input type="time" id="hora" name="hora" value="${hora}" min="09:00" max="17:00" required="required">
							</c:if>
                            <label class="hora" for="hora">Hora</label>
                        </div>
                      
                        <button class="pedirC" type="submit">Pedir cita</button>
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
