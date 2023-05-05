<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Editar Perfil Empleado</title>

    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/editarPerfil.css" />

    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
    crossorigin="anonymous"
  />
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
        <li><a class="activo" href="">Inicio</a></li>
        <li><a href="nuestroEquipo.html">Nuestro equipo</a></li>
        <li><a href="">Tratamientos</a></li>
        <li><a href="Login.html">Iniciar sesión/Registrarse</a></li>
        <li><a href="pedirCita.html">Pide tu Cita</a></li>
        <li><a href="">Contáctanos</a></li>
        <li><a href="verCitas.html">Consultar Citas</a></li>
      </ul>
    </nav>
  </header>

  <body>
    <main>
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
                    <h5 class="titulo">Editar perfil de Empleado Smiling</h5>
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
              <div class="card-body">
                <div class="row gutters">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="mb-2 text-primary">Datos personales</h6>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="nombre">Nombre</label>
                      <input
                        type="text"
                        class="form-control"
                        id="nombre"
                        required
                      />
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="apellidos">Apellidos</label>
                      <input
                        type="text"
                        class="form-control"
                        id="apellidos"
                        required
                      />
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="correo">Correo</label>
                      <input
                        type="email"
                        class="form-control"
                        id="correo"
                        required
                      />
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                        <label for="puesto">Puesto</label>
                        <select name="puesto" id="puesto" class="form-control" required>
                            <option value="puesto1">Dentista</option>
                            <option value="puesto2">Recepcionista</option>
                            <option value="puesto3">Director</option>
                        </select>
                      </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                        <label for="clinica">Clinica</label>
                        <select name="clinica" id="clinica" class="form-control" required>
                            <option value="clinica1">Clinica1</option>
                            <option value="clinica2">Clinica2</option>
                        </select>
                      </div>
                  </div>
                </div>
                <div class="row gutters">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="mt-3 mb-2 text-primary">Cambiar contraseña</h6>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="contrasena"
                        >Introduce la contraseña actual</label
                      >
                      <input
                        type="password"
                        class="form-control"
                        id="contrasena"
                      />
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="nuevaCon">Nueva contraseña</label>
                      <input
                        type="password"
                        class="form-control"
                        id="nuevaCon"
                      />
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <div class="form-group">
                      <label for="confNuevaCon"
                        >Confirmar nueva contraseña</label
                      >
                      <input
                        type="password"
                        class="form-control"
                        id="confNuevaCon"
                      />
                    </div>
                  </div>
                </div>
                <div class="row gutters">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <button
                      type="button"
                      id="submit"
                      name="submit"
                      class="btn btn-primary"
                      style="margin-top: 30px"
                    >
                      Actualizar
                    </button>
                  </div>
                </div>
              </div>
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