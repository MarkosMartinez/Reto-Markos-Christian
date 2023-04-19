<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="modelo.DTO.Cita"%>
<%@ page import="modelo.DTO.Clinica"%>
<%@ page import="modelo.DAO.ModeloClinica"%>
<%@ page import="modelo.DAO.ModeloCita"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Citas</title>
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
    crossorigin="anonymous"
  />
  <link
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
    rel="stylesheet"
  />
  <link rel="stylesheet" href="css/menu.css" />
  <link rel="stylesheet" href="css/verCitas.css">
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
        <li><a href="">Inicio</a></li>
        <li><a href="nuestroEquipo.html">Nuestro equipo</a></li>
        <li><a href="">Tratamientos</a></li>
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
        </c:if>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
        <li><a class="activo" href="VerCitas">Consultar Citas</a></li>
        <c:if test="${tipoLogin eq 'cliente'}">
        <li><a href="">Editar Perfil</a></li>
        </c:if>
      </ul>
    </nav>
  </header>

<body>
    <main style="padding-bottom: 10%;">


        <div class="ag-format-container">
          <h1 style="text-align: center; padding-top: 100px;">Lista de citas</h1>

            <div class="ag-courses_box">
            <c:set var="actual" value="0"/>
            
               <c:forEach var="cita" items="${citas}">
           	 <c:forEach var="clinica" items="${clinicas}">
           		 <c:if test="${clinica.getId_clinica() eq cita.getId_Clinica()}">
            		 <c:forEach var="cliente" items="${clientes}">
            		  <c:if test="${cliente.getDni() eq cita.getDni_Cliente()}">
              <div class="ag-courses_item">
                <a href="#" class="ag-courses-item_link">
                  <div class="ag-courses-item_bg"></div>
          
                  <div class="ag-courses-item_title">
                    <p>Clínica: ${clinica.getNombre_clinica()}</p>
                    <p style="font-size: 16px;">Dirección: ${clinica.getDireccion()}</p>
                    <p>Nombre: ${cliente.getNombre()} ${cliente.getApellidos()}</p>
                    <p style="font-size: 16px;">Teléfono: ${telefonos.get(actual)}</p>
                  </div>
          
                  <div class="ag-courses-item_date-box">
                    Fecha:
                    <span class="ag-courses-item_date">
                      <fmt:formatDate value="${cita.getFecha_Cita()}" pattern="dd/MM/yyyy" /> ${horas.get(actual)}
                      <c:set var="actual" value="${actual + 1}"/>
                    </span>
                  </div>
                </a>
              </div>
              			
              		</c:if>
              		</c:forEach>
              	  </c:if>
              	 	</c:forEach>
             	 </c:forEach>
   
            </div>
          </div>
            

    </main>

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
</body>
</html>