<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lista de empleados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/listaEmpleadosClientes.css" />
  </head>

<header> <% //TODO Arreglar el header y añadir lo que falte. Por ejemplo GestionarUsuarios %>
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
        <c:if test="${tipoLogin eq 'empleado'}">
        <li><a href="EditarEquipamiento">Editar Equipamiento</a></li>
        </c:if>
        <c:if test="${tipoLogin != 'ninguno'}">
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
        </c:if>
      </ul>
    </nav>
  </header>

  <body>
    <main>
    
	     <c:if test="${aviso eq 'usucreado'}">
	        	<div class="alerta">
					  <div class="alert alert-success check alert-dismissible fade show" role="alert">
				 		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				  		<i class="fa-solid fa-circle-check fa-bounce fa-lg"></i> &nbsp; &nbsp;
				 		<span>Empleado creado correctamente!</span>
					  </div>
				</div>
        </c:if>
        
        <c:if test="${aviso eq 'error'}">
        	<div class="alerta">
        	  <div class="alert alert-warning warning alert-dismissible fade show" role="alert" style="background-color: red; box-shadow: none;">
  				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
 				<i class="fa-solid fa-triangle-exclamation fa-spin fa-lg" style="color: #ffffff;"></i> <% //TODO Cambiar el icono! %>
  				&nbsp; &nbsp;
  				<span>Error, esto no deberia de haber ocurrido!</span>
			  </div>
        	</div>
        </c:if>
    
      <section>
        <div class="tituloYopcion">
        <c:if test="${director eq true}">
          <h1 class="titulo">Lista de</h1>
		  <form action="GestionarUsuarios" method="POST" >
		   <input type="text" value="cambiomodo" name="tipo" readonly="readonly" hidden required="required">
          	<select class="opcionGestion" name="opcionGestion" onchange="this.form.submit()">
          		<c:if test="${visualizar eq 'emp'}">
            	<option selected="selected" value="empleados">EMPLEADOS</option>
            	<option value="clientes">CLIENTES</option>
            	</c:if>
            	<c:if test="${visualizar ne 'emp'}">
            	<option value="empleados">EMPLEADOS</option>
            	<option selected="selected" value="clientes">CLIENTES</option>
            	</c:if>
          	</select>
		</form>
          </c:if>
          <c:if test="${director eq false}">
          <h1 class="titulo">Lista de Clientes</h1>
          </c:if>
        </div>

        <div class="tbl-header">
          <table cellpadding="0" cellspacing="0" border="0">
            <thead>
              <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <c:if test="${visualizar eq 'emp'}">
            	<th>Clínica</th>
            	</c:if>
                <th>Editar</th>
                <th>Eliminar</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <c:if test="${visualizar eq 'emp'}">
            	<c:forEach var="empleado" items="${empleados}">
		              <tr>
		                <td>${empleado.dni_Emp}</td>
		                <td>${empleado.nombre} ${empleado.apellidos}</td>
		                <c:forEach var="clinica" items="${clinicas}">
		                	<c:if test="${clinica.id_clinica eq empleado.id_Clinica}">
		                 	  <td>${clinica.nombre_clinica}</td>
		                 	</c:if>
		                  </c:forEach>
		                <td>
		                  <a href="EditarPerfil?dni=${empleado.dni_Emp}" class="btn btn-success"> <%//TODO Hacer que funcione! %>
		                  <i class="fa-solid fa-pen-to-square" style="color: #ffffff"></i>
		                  </a>
		                </td>
		                <td>
		                  <a href="EliminiarUsuario?dni=${empleado.dni_Emp}" class="btn btn-danger"> <%//TODO Hacer que funcione! %>
		                  <i class="fa-solid fa-user-slash" style="color: #ffffff"></i>
		                  </a>
		                </td>
		              </tr>
	              </c:forEach>
            	</c:if>
            <c:if test="${visualizar ne 'emp'}">
            	<c:forEach var="cliente" items="${clientes}">
              <tr>
                <td>${cliente.dni}</td>
                <td>${cliente.nombre} ${cliente.apellidos}</td>
                <td>
                  <a href="EditarPerfil?dni=${cliente.dni}" class="btn btn-success">
                  <i class="fa-solid fa-pen-to-square" style="color: #ffffff"></i>
                  </a>
                </td>
                <td>
                  <a href="EliminiarUsuario?dni=${cliente.dni}" class="btn btn-danger">
                  <i class="fa-solid fa-user-slash" style="color: #ffffff"></i>
                  </a>
                </td>
              </tr>
              </c:forEach>
            </c:if>
            
            </tbody>
          </table>
        </div>
        <c:if test="${director eq true and visualizar eq 'emp'}">
          <a href="#añadir">
          <button class="button" style="vertical-align: middle">
            <span>ㅤAñadirㅤ</span>
          </button>
        </a>

        <div id="añadir" class="overlay">
          <div class="popup">
          <h2 style="color: rgb(0, 132, 255)">Añadir empleado</h2>
            <a class="close" href="#">&times;</a>
            <div class="content">
              <form action="GestionarUsuarios" method="POST">
		  	 	<input type="text" value="insertEmpleado" name="tipo" readonly="readonly" hidden required="required">
                <label for="dni">DNI:</label>
                <input type="text" name="DNI_Emp" required id="dni" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra" minlength="9" maxlength="9"/>
                <br /><br />
                <label for="nombre">Nombre:</label>
                <input type="text" name="Nombre" required id="nombre" />
                <br /><br />
                <label for="apellidos">Apellidos:</label>
                <input type="text" name="Apellidos" required id="apellidos" />
                <br /><br />
                <label for="correo">Correo:</label>
                <input type="email" name="Correo" required id="correo" />
                <br /><br />
                <label for="contraseña">Contraseña:</label> <% //TODO Confirmar contraseña? %>
                <input type="password" name="pass" required id="contraseña" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="La contraseña debe de tener una longitud minima de 6 caracteres, con mayusculas, minusculas y numeros"/>
                <br /><br />
                <label for="fecha_nac">Fecha de Nacimiento:</label>
                <input type="date" name="Fecha_Nacimiento" required id="fecha_nac"/>
                <br /><br />
                <label for="puesto">Puesto:</label>
                <select name="Puesto" id="puesto" required="required">
                <c:forEach var="puesto" items="${puestos}">
                  <option value="${puesto.id_Puesto}">${puesto.nombre_Puesto}</option>
                  </c:forEach>
                </select>
                <br /><br />
                <label for="clinica">Clinica:</label>
                <select name="Clinica" id="clinica" required="required">
                 <c:forEach var="clinica" items="${clinicas}">
                  <option value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
                  </c:forEach>
                </select>
                <br /><br />
                <button type="submit" class="botonFormulario">Enviar</button>
              </form>
            </div>
          </div>
        </div>
        </c:if>
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