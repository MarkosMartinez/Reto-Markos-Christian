<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="modelo.DTO.Clinica"%>

    <!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lista de habitaciones</title>
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
    <link rel="stylesheet" href="css/listaHabitacionesClinicas.css" />
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
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <li><a href="EditarEquipamiento">Editar Equipamiento</a></li>
        <li><a class="activo" href="GestionarClinicas">Gestionar Clinicas</a></li>
        <li><a href="GestionarUsuarios">Gestionar Usuarios</a></li>
        <li><a href="EditarEmpleado">Editar Perfil</a></li>
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
      </ul>
    </nav>
  </header>

  <body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    
    <main>
    
    <c:if test="${aviso eq 'habitacioncreada'}">
		<div class="alerta">
			<div class="alert alert-success check alert-dismissible fade show" role="alert">
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				<i class="fa-solid fa-tooth fa-bounce fa-xl"></i> &nbsp; &nbsp;
				<span>Habitacion creada correctamente!</span>
			</div>
		</div>
    </c:if>
    
    <c:if test="${aviso eq 'clinicacreada'}">
		<div class="alerta">
			<div class="alert alert-success check checkclinica alert-dismissible fade show" role="alert">
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				<i class="fa-solid fa-building-circle-check fa-beat fa-lg"></i> &nbsp; &nbsp;
				<span>Clinica creada correctamente!</span>
			</div>
		</div>
    </c:if>
    
    <c:if test="${aviso eq 'clinicaeliminada'}">
		<div class="alerta">
			<div class="alert alert-success check checkclinica alert-dismissible fade show" role="alert">
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				<i class="fas fa-trash fa-shake fa-lg" style="color: #ffffff;"></i> &nbsp; &nbsp;
				<span>Clinica eliminada correctamente!</span>
			</div>
		</div>
    </c:if>
    
    <c:if test="${aviso eq 'habitacioneliminada'}">
		<div class="alerta">
			<div class="alert alert-success check alert-dismissible fade show" role="alert">
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				<i class="fas fa-trash fa-shake fa-lg" style="color: #ffffff;"></i> &nbsp; &nbsp;
				<span>Habitacion eliminada correctamente!</span>
			</div>
		</div>
    </c:if>
        
    <c:if test="${aviso eq 'error'}">
    	<div class="alerta">
    		<div class="alert alert-warning warning alert-dismissible fade show" role="alert" style="background-color: red; box-shadow: none;">
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				<i class="fa-solid fa-triangle-exclamation fa-bounce fa-lg"></i>
				&nbsp; &nbsp;
				<span>Error, esto no deberia de haber ocurrido!</span>
			</div>
    	</div>
   </c:if>
    
      <section>
        <div class="tituloYopcion" style="max-width: 100%; margin: auto; display: flex; justify-content: center; align-items: center;">
          <h1 class="titulo">Lista de habitaciones de</h1>
          <form action="GestionarClinicas" method="POST" style="display: inline-block; margin-top: -61px;">
          <input type="text" value="modclinica" name="tipo" readonly="readonly" hidden required="required">
	          <select class="opcionGestion" name="clinica" onchange="this.form.submit()">
		          <c:forEach var="clinica" items="${clinicas}">
		          	<c:if test="${clinica.id_clinica eq empleadoLogueado.getId_Clinica()}">
					   <option selected="selected" value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
					</c:if>
					<c:if test="${clinica.id_clinica ne empleadoLogueado.getId_Clinica()}">
					   <option value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
					</c:if>
		          </c:forEach>
	          </select>
          </form>
        </div>

        <div class="botonesAñaEli">
            <div class="dropdown" style="margin-right: 10px; position: relative; z-index: 1;">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                  Añadir Clínica <i class="fa-solid fa-building-circle-check"></i>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <li>
                    <form action="GestionarClinicas" method="POST" style="padding: 10px;">
                   	 <input type="text" value="addClinica" name="tipo" readonly="readonly" hidden required="required">
                      <label for="nombre">Nombre:</label>
                      <input type="text" id="nombre" name="nombre" required="required" >
                      <br><br>
                      <label for="direccion">Direccion:</label>
                      <input type="text" id="direccion" name="direccion" required="required" >
                      <br><br>
                      <label for="telefono">Teléfono:</label>
                      <input type="number" id="telefono" name="telefono" min="600000000" required="required" >
                      <br><br>
                      <button type="submit" class="btn btn-primary">Añadir</button>
                    </form>
                  </li>
                </ul>
              </div>
    
              <div>
                <a href="EliminarClinica?id=${empleadoLogueado.getId_Clinica()}" class="btn btn-danger">Eliminar Clínica <i class="fa-solid fa-building-circle-xmark"></i></a>
              </div>
              
        </div>

        <div class="tbl-header">
          <table cellpadding="0" cellspacing="0" border="0">
            <thead>
              <tr>
              	<c:if test="${orden ne 'DESC'}">
                <th><a href="GestionarClinicas?o=DESC" class="linkorden">Nº Habitación ⬇</a></th>
                </c:if>
                <c:if test="${orden eq 'DESC'}">
                <th><a href="GestionarClinicas?o=ASC" class="linkorden">Nº Habitación ⬆</a></th>
                </c:if>
                <th>Especialidad</th>
                <th>Eliminar</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
              <c:forEach var="habitacion" items="${habitaciones}">
	              <tr>
	                <td>${habitacion.num_Habitacion}</td>
	                <td>${habitacion.especialidad }</td>
	                <td>
	                  <a href="EliminarHabitacion?id=${habitacion.id_Habitacion}" class="btn btn-danger"
	                    ><i class="fa-solid fa-trash-can" style="color: #ffffff"></i
	                  ></a>
	                </td>
	              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

        <a href="#añadir">
          <button class="button" style="vertical-align: middle">
            <span>ㅤAñadirㅤ</span>
          </button>
        </a>

        <div id="añadir" class="overlay">
          <div class="popup">
            <h2 style="color: rgb(0, 132, 255)">Añadir habitación</h2>
            <a class="close" href="#">&times;</a>
            <div class="content">
              <form action="GestionarClinicas" method="POST">
              <input type="text" value="addHabitacion" name="tipo" readonly="readonly" hidden required="required">
                <label for="numHabitacion">Número de habitacion:</label>
                <input type="number" name="numHabitacion" required id="numHabitacion" min="0"/>
                <br /><br />
                <label for="especialidad">Especialidad:</label>
                <input type="text" name="especialidad" required id="especialidad" />
                <br /><br />
                <label for="clinica">Clinica:</label>
                <select name="clinica" id="clinica" required="required">
                  <c:forEach var="clinica" items="${clinicas}">
		          	<c:if test="${clinica.id_clinica eq empleadoLogueado.getId_Clinica()}">
					   <option selected="selected" value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
					</c:if>
					<c:if test="${clinica.id_clinica ne empleadoLogueado.getId_Clinica()}">
					   <option value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
					</c:if>
		          </c:forEach>
                </select>
                <br /><br />
                <button type="submit" class="botonFormulario">Enviar</button>
              </form>
            </div>
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
</html>