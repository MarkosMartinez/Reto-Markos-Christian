<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Editar Perfil</title>
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
    <link rel="stylesheet" href="css/editarPerfil.css" />
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
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <li><a class="activo" href="EditarPerfil">Editar Perfil</a></li>
        <c:if test="${tipoLogin eq 'empleado'}">
        <li><a href="EditarEquipamiento">Editar Equipamiento</a></li>
        </c:if>
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
      </ul>
    </nav>
  </header>

  <body>
    <main>
    
     <c:if test="${aviso eq 'actualizado'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fa-solid fa-circle-check fa-bounce fa-lg"></i> &nbsp; &nbsp;
  <span>Perfil actualizado correctamente!</span>
</div>
</div>
        </c:if>
        
        <c:if test="${aviso eq 'error'}"> <% //TODO Cambiar el error que no de vueltas %>
        <div class="alerta">
        	  <div class="alert alert-warning warning alert-dismissible fade show" role="alert" style="background-color: red; box-shadow: none;">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
 <i class="fa-solid fa-triangle-exclamation fa-spin fa-lg" style="color: #ffffff;"></i> <% //TODO Cambiar el icono! %>
  &nbsp; &nbsp;
  <span>Error, esto no deberia de haber ocurrido!</span>
</div>
        </div>
        </c:if>
    
      <div class="container">
        <div class="row gutters">
          <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
              <div class="card-body">
                <div class="editarPerfil">
                  <div class="foto">
                    <div class="logo">
                      <img src="img/logoProv.png" />
                    </div>
                    <h5 class="titulo">Editar perfil de Smiling</h5>
                  </div>
                  <div class="texto">
                    <h5>Dudas</h5>
                    <p>
                      Si tienes alguna duda o problema no dudes en contactarnos
                      a través de nuestro email smiling@hotmail.com o de nuestro
                      teléfono 688 718 521 disponible de lunes a viernes de 9:00
                      a 17:00
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
            <form class="form-inline" action="EditarPerfil" method="POST">
     		<input type="text" class="form-control" value="${cliente.dni}" required hidden readonly="readonly" name="dni">
              <div class="card-body">
                <div class="row gutters">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="mb-2 text-primary">Datos personales</h6>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="nombre">Nombre</label>
                      <input type="text" class="form-control" id="nombre" value="${cliente.nombre}" name="nombre" required/>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="apellidos">Apellidos</label>
                      <input type="text" class="form-control" id="apellidos" value="${cliente.apellidos}" name="apellidos" required/>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="correo">Correo</label>
                      <input type="email" class="form-control" id="correo" value="${cliente.correo}" name="correo" required/>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="telefono">Añadir teléfono (opcional)</label>
                      <input type="number" class="form-control" id="telefono" name="telefono" min="600000000" max="999999999"/>
                    </div>
                  </div>
                </div>

                <div class="row gutters mt-1">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <label for="telefonos">Teléfono/s:</label>
                    <select name="telefonos" id="telefonos" size="2">
                     <c:forEach var="telefono" items="${telefonos}">
                      <option value="${telefono.telefono}">${telefono.telefono}</option>
                     </c:forEach>
                    </select>
                    <p>* Selecciona uno y pulsar en "Actualizar" para eliminarlo</p>
                  </div>
                </div>
                <div class="row gutters">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="mt-3 mb-2 text-primary">Cambiar contraseña</h6>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="contrasena">Introduce la contraseña actual</label>
                      <c:if test="${tipoLogin eq 'empleado'}">
                      	<input type="password" class="form-control" id="contrasena" readonly="readonly" disabled="disabled" name="contrasena"/>
                      </c:if>	
                      <c:if test="${tipoLogin eq 'cliente'}">
                      	<input type="password" class="form-control" id="contrasena" name="contrasena"/>
                      </c:if>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="nuevaCon">Nueva contraseña</label>
                      <input type="password" class="form-control" id="nuevaCon" name="nuevaCon" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="La contraseña debe de tener una longitud minima de 6 caracteres, con mayusculas, minusculas y numeros"/>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="confNuevaCon">Confirmar nueva contraseña</label>
                      <input type="password" class="form-control" id="confNuevaCon" name="confNuevaCon" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="La contraseña debe de tener una longitud minima de 6 caracteres, con mayusculas, minusculas y numeros"/>
                    </div>
                  </div>
                </div>
                <div class="row gutters">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <button type="submit" id="submit" class="btn btn-primary" style="margin-top: 30px">Actualizar</button>
                    <a href="EliminarCliente?dni=${cliente.dni}" class="btn btn-danger btnEliminar">Eliminar Usuario</a>
                  </div>
                  
                </div>
              </div>
             </form>
            </div>
          </div>
        </div>
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