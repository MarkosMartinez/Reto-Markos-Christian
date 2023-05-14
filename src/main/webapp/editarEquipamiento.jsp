<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipamiento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/equipamiento.css">
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
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
        </c:if>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <li><a class="activo" href="EditarEquipamiento">Editar Equipamiento</a></li>
        <c:if test="${director eq true}">
        <li><a href="GestionarClinicas">Gestionar Clinicas</a></li>
        </c:if>
        <li><a href="GestionarUsuarios">Gestionar Usuarios</a></li>
        <li><a href="EditarEmpleado">Editar Perfil</a></li>
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
      </ul>
    </nav>
  </header>
<!--Fin del menu de navegación-->

<body>
    <main>
    
      <!--Avisos que saltarán dependiendo de las acciones que realice el usuario-->
    <c:if test="${aviso eq 'insertado'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fa-solid fa-circle-check fa-bounce fa-lg"></i> &nbsp; &nbsp;
  <span>Equipamiento insertado correctamente!</span>
</div>
</div>
        </c:if>
        
        <c:if test="${aviso eq 'actualizado'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fa-solid fa-circle-check fa-bounce fa-lg"></i> &nbsp; &nbsp;
  <span>Equipamiento actualizado correctamente!</span>
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
      <!--Fin de avisos-->  

      <!--Titulo superior, depende del rol con el que se entre a la pagina web aparecerán unas opciones u
      otras, en caso del director será capaz de cambiar de clinica para ver el quipamiento de otra-->
    <section>
      <c:if test="${director eq 'true'}">
		    <div style="max-width: 100%; margin: auto; display: flex; justify-content: center; align-items: center;">
		        <h1>Lista de equipamiento de </h1>
		        <form action="EditarEquipamiento" method="POST">
		            <select class="clinica" name="clinica" required="required" onchange="this.form.submit()">
		                <c:forEach var="clinica" items="${clinicas}">
		                    <c:if test="${clinica.id_clinica eq empleadoLogueado.getId_Clinica()}">
		                        <option selected="selected" value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
		                    </c:if>
		                    <c:if test="${clinica.id_clinica ne empleadoLogueado.getId_Clinica()}">
		                        <option value="${clinica.id_clinica}">${clinica.nombre_clinica}</option>
		                    </c:if>
		                </c:forEach>
		            </select>
		            <input type="text" value="modclinica" name="tipo" readonly="readonly" hidden required="required">
		            <input type="text" value="${empleadoLogueado.dni_Emp}" name="dnidirector" readonly="readonly" hidden required="required">
		        </form>
		    </div>
		  </c:if>

      <c:if test="${director eq 'false'}">
         <h1>Lista de equipamiento</h1>
       </c:if>
           <div class="form-value">
           <form class="form-inline" action="EditarEquipamiento" method="POST">
            <input type="text" value="actualizar" name="tipo" readonly="readonly" hidden required="required">
  			 <c:set var="cantidad" value="0"/>
            
            <!--Tabla en la que se mostrarán los campos del equipamiento-->
            <div class="tbl-header">
              <table cellpadding="0" cellspacing="0" border="0">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Editar</th>
                  </tr>
                </thead>
              </table>
            </div>
            
            <!--Tabla en la que se mostrará el equipamiento-->
            <div class="tbl-content">
              <table cellpadding="0" cellspacing="0" border="0">
                <tbody>
				  <c:forEach var="equip" items="${equipamiento}">
				    <tr>
				      <th scope="row">${equip.id_Equipamiento}</th>
				      <td>${equip.nombre_Equipamiento}</td>
				      <td>${equip.precio} €</td>
				      <td><input type="number" value="${equip.stock}" min="0" name="stock-${cantidad}" required="required" class="inputs"> Uds</td>
				      <c:if test="${director eq 'true' && equip.stock > 0}">
				      <td><a type="button" style="margin-right: 6px;" href="EditarEquipamiento?c=${cantidad}&v=${equip.stock-1}" class="btn btn-danger">-1</a><a type="button" href="EditarEquipamiento?d=${equip.id_Equipamiento}" class="btn btn-danger eliminar"><i class="fa-solid fa-trash" style="color: #ffffff;"></i></a></td>
				      </c:if>
				      <c:if test="${director eq 'true' && equip.stock <= 0}">
				      <td><a type="button" style="margin-right: 6px;" href="" class="btn btn-danger disabled">-1</a><a type="button" href="EditarEquipamiento?d=${equip.id_Equipamiento}" class="btn btn-danger eliminar"><i class="fa-solid fa-trash" style="color: #ffffff;"></i></a></td>
				      </c:if>
				      <c:if test="${director eq 'false' && equip.stock > 0}">
				      <td><a type="button" href="EditarEquipamiento?c=${cantidad}&v=${equip.stock-1}" class="btn btn-danger">-1</a></td>
				      </c:if>
				       <c:if test="${director eq 'false' && equip.stock <= 0}">
				      <td><a type="button" href="" class="btn btn-danger disabled">-1</a></td>
				      </c:if>
				      <c:set var="cantidad" value="${cantidad + 1}"/>
				    </tr>
				    </c:forEach>
				  </tbody>
              </table>
             </div>
             
             <!--Botones para insertar equipamiento o para actualizar los cambios-->
					<input type="text" value="${cantidad}" name="cantidad" readonly="readonly" hidden required="required">
				  <a href="#formularioEquip" type="button" class="btn btn-success btnInsertar">Insertar Equipamiento</a>
				  <button type="submit" class="btn btn-warning botonFormulario">Actualizar todos!</button>
				
        <!--Link de javascript de bootrap-->
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        
			</form>
		</div>
    
    <!--Formulario popup que aparecerá al darle al boton de insertar Equipamiento-->
		<div id="formularioEquip" class="overlay">
          <div class="popup">
            <h2 style="color: rgb(0, 132, 255);">Insertar Equipamiento</h2>
            <a class="close" href="#volver">&times;</a>
            <div class="content">
              <form action="EditarEquipamiento" method="POST">
				<input type="text" value="insert" name="tipo" readonly="readonly" hidden required="required">
				<input type="number" value="${empleadoLogueado.id_Clinica}" name="idClinica" readonly="readonly" hidden required="required">
                <label for="nombre" >Nombre:</label>
                <input type="text" required id="nombre" name="nombre">
                <br><br>
                <label for="precio">Precio:</label>
                <input type="number" step="any" required id="precio" name="precio" min="0">
                <br><br>
                <label for="stock">Stock:</label>
                <input type="number" required id="stock" name="stock" min="0">
                <br><br>
                <button type="submit" class="botonInsertar">Insertar</button>
              </form>
            </div>
          </div>
        </div>
            
          </section>
    </main>
</body>

<!--Footer-->
<footer class="footer">
    <div class="footer-izquierda col-md-4 col-sm-6">
      <p class="about">
        <span> Sobre Smiling</span> 
        En nuestra clínica odontológica, nos dedicamos a brindar la mejor atención dental para cada uno de nuestros pacientes. Contamos con un equipo altamente capacitado y comprometido en ofrecer servicios de calidad y personalizados para satisfacer las necesidades de cada uno de nuestros pacientes. Nuestra misión es ayudar a nuestros pacientes a mantener una buena salud bucal y una sonrisa radiante. ¡Estamos encantados de servirle!
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