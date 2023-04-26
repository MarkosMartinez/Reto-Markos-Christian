<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="modelo.DTO.Cita"%>
<%@ page import="modelo.DTO.Clinica"%>
<%@ page import="modelo.DAO.ModeloClinica"%>
<%@ page import="modelo.DAO.ModeloCita"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Consultar Citas</title>
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
    <link rel="stylesheet" href="css/verCitas.css" />
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
        	<li><a href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
        </c:if>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
        <li><a class="activo" href="VerCitas">Consultar Citas</a></li>
        <c:if test="${tipoLogin eq 'cliente'}">
        <li><a href="EditarPerfil">Editar Perfil</a></li>
        </c:if>
        <c:if test="${tipoLogin != 'ninguno'}">
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
        </c:if>
      </ul>
    </nav>
  </header>

  <body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <main>
    
    
      <div class="ag-format-container">
      
      <h1 class="listaCitas">Lista de citas</h1>
        <c:if test="${aviso eq 'borradocorrecto'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fas fa-trash fa-shake fa-lg" style="color: #ffffff;"></i> &nbsp; &nbsp;
  <span>Cita cancelada correctamente!</span>
</div>
</div>
        </c:if>
        
        <c:if test="${aviso eq 'borradoincorrecto'}">
        <div class="alerta">
        	  <div class="alert alert-warning warning alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
 <i class="fa-solid fa-triangle-exclamation fa-bounce" style="color: #ffffff;"></i>
  &nbsp; &nbsp;
  <span>Error no se ha podido eliminar la cita!</span>
</div>
        </div>
        </c:if>
        
        <c:if test="${aviso eq 'error'}">
        <div class="alerta">
        	  <div class="alert alert-warning warning alert-dismissible fade show" role="alert" style="background-color: red; box-shadow: none;"> <% //TODO Arreglar sombra %>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
 <i class="fa-solid fa-triangle-exclamation fa-spin fa-lg" style="color: #ffffff;"></i> <% //TODO Cambiar el icono! %>
  &nbsp; &nbsp;
  <span>Error, esto no deberia de haber ocurrido!</span>
</div>
        </div>
        </c:if>

		
 		<c:if test="${citasPosteriores.size() == 0}">
 		<div class="ag-courses_box">
 		<%//TODO Aqui estara la card o lo que tenga que mostrarse cuando no haya ninguna cita posterior! Las anteriores se veran? %>
 		</div>
 		</c:if>
        
		

        <div class="ag-courses_box">
        <c:set var="actual" value="0"/>
               <c:forEach var="cita" items="${citasPosteriores}">
           	 <c:forEach var="clinica" items="${clinicas}">
           		 <c:if test="${clinica.getId_clinica() eq cita.getId_Clinica()}">
            		 <c:forEach var="cliente" items="${clientes}">
            		  <c:if test="${cliente.getDni() eq cita.getDni_Cliente()}">
          <div class="ag-courses_item">
            <div class="ag-courses-item_link">
              <div class="ag-courses-item_bg"></div>

              <div class="ag-courses-item_title">
                <p>Clínica: ${clinica.getNombre_clinica()}</p>
                <p style="font-size: 16px;">Dirección: ${clinica.getDireccion()}</p>
                <p>Nombre: ${cliente.getNombre()} ${cliente.getApellidos()}</p>
                <p style="font-size: 16px;">Teléfono: ${telefonosPosteriores.get(actual)}</p>
              </div>

              <div class="ag-courses-item_date-box">
                Fecha:
               <span class="ag-courses-item_date">
                      <fmt:formatDate value="${cita.getFecha_Cita()}" pattern="dd/MM/yyyy" /> ${horasPosteriores.get(actual)}
                </span>

                <span>
                  <a href="EliminarCita?id_clinica=${cita.getId_Clinica()}&dni=${cita.getDni_Cliente()}&fecha=${cita.getFecha_Cita()}&hora=${horasPosteriores.get(actual)}">
                    <button class="primary-button">
                      <i class="fas fa-trash"></i>
                    </button>
                  </a>
                </span>
                <c:set var="actual" value="${actual + 1}"/>
              </div>
            </div>
          </div>
				   </c:if>
              		</c:forEach>
              	</c:if>
              </c:forEach>
             	</c:forEach>

        </div>
      </div>
      
      <div class="ag-courses_box">
        <c:set var="actual" value="0"/>
            
               <c:forEach var="cita" items="${citasAnteriores}">
           	 <c:forEach var="clinica" items="${clinicas}">
           		 <c:if test="${clinica.getId_clinica() eq cita.getId_Clinica()}">
            		 <c:forEach var="cliente" items="${clientes}">
            		  <c:if test="${cliente.getDni() eq cita.getDni_Cliente()}">
          <div class="ag-courses_item">
            <div class="ag-courses-item_link">
              <div class="ag-courses-item_bg2"></div>

              <div class="ag-courses-item_title">
                <p>Clínica: ${clinica.getNombre_clinica()}</p>
                <p style="font-size: 16px;">Dirección: ${clinica.getDireccion()}</p>
                <p>Nombre: ${cliente.getNombre()} ${cliente.getApellidos()}</p>
                <p style="font-size: 16px;">Teléfono: ${telefonosAnteriores.get(actual)}</p>
              </div>

              <div class="ag-courses-item_date-box2">
                Fecha:
               <span class="ag-courses-item_date">
                      <fmt:formatDate value="${cita.getFecha_Cita()}" pattern="dd/MM/yyyy" /> ${horasAnteriores.get(actual)}
                </span>
                <c:if test="${tipoLogin eq 'empleado'}">
                 <span>
                  <a href="ConfirmarCita?id_clinica=${cita.getId_Clinica()}&dni=${cita.getDni_Cliente()}&fecha=${cita.getFecha_Cita()}&hora=${horasPosteriores.get(actual)}">
                    <button class="primary-button"> <% //TODO En verde? %>
                      <i class="fa-regular fa-circle-check"></i>
                    </button>
                  </a>
                </span>
                </c:if>
                <c:set var="actual" value="${actual + 1}"/>
              </div>
            </div>
          </div>
				   </c:if>
              		</c:forEach>
              	</c:if>
              </c:forEach>
             	</c:forEach>

        </div>
      
      
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


</html>