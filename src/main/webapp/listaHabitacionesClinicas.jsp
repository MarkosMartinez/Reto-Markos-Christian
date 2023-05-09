<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lista de habitaciones</title>
    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/listaHabitacionesClinicas.css" />

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
      <a href="Principal" class="enlaceLogo">
        <img src="img/logoProv.png" class="logo" />
      </a>
      <ul class="opciones">
        <li><a href="Principal">Inicio</a></li>
        <li><a href="NuestroEquipo">Nuestro equipo</a></li>
        <li><a href="Tratamientos">Tratamientos</a></li>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <li><a class="activo" href="EditarEquipamiento">Editar Equipamiento</a></li>
        <li><a href="GestionarClinicas">Gestionar Clinicas</a></li>
        <li><a href="GestionarUsuarios">Gestionar Usuarios</a></li>
        <li><a href="EditarEmpleado">Editar Perfil</a></li>
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
      </ul>
    </nav>
  </header>

  <body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    
    <main>
      <section>
        <div class="tituloYopcion">
          <h1 class="titulo">Lista de habitaciones de</h1>
          <select class="opcionGestion" name="opcionGestion">
            <option value="clinica1">CLINICA1</option>
            <option value="clinica2">CLINICA2</option>
          </select>
        </div>

        <div class="botonesAñaEli">
            <div class="dropdown" style="margin-right: 10px; position: relative; z-index: 1;">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                  Añadir Clínica <i class="fa-solid fa-building-circle-check"></i>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <li>
                    <form style="padding: 10px;">
                      <label for="nombre">Nombre:</label>
                      <input type="text" id="nombre" name="nombre">
                      <br><br>
                      <label for="direccion">Direccion:</label>
                      <input type="text" id="direccion" name="direccion">
                      <br><br>
                      <label for="telefono">Teléfono:</label>
                      <input type="number" id="telefono" name="telefono" min="600000000">
                      <br><br>
                      <button type="submit" class="btn btn-primary">Añadir</button>
                    </form>
                  </li>
                </ul>
              </div>
    
              <div>
                <a href="" class="btn btn-danger">
                    Eliminar Clínica <i class="fa-solid fa-building-circle-xmark"></i>
                  </a>
              </div>
              
        </div>
     
          

        <div class="tbl-header">
          <table cellpadding="0" cellspacing="0" border="0">
            <thead>
              <tr>
                <th>Nº Habitación</th>
                <th>Especialidad</th>
                <th>Eliminar</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
              <tr>
                <td>33</td>
                <td>Radiología</td>
                <td>
                  <a href="" class="btn btn-danger"
                    ><i class="fa-solid fa-trash-can" style="color: #ffffff"></i
                  ></a>
                </td>
              </tr>
              <tr>
                <td>33</td>
                <td>Radiología</td>
                <td>
                  <a href="" class="btn btn-danger"
                    ><i class="fa-solid fa-trash-can" style="color: #ffffff"></i
                  ></a>
                </td>
              </tr>
              <tr>
                <td>33</td>
                <td>Radiología</td>
                <td>
                  <a href="" class="btn btn-danger"
                    ><i class="fa-solid fa-trash-can" style="color: #ffffff"></i
                  ></a>
                </td>
              </tr>
              <tr>
                <td>33</td>
                <td>Radiología</td>
                <td>
                  <a href="" class="btn btn-danger"
                    ><i class="fa-solid fa-trash-can" style="color: #ffffff"></i
                  ></a>
                </td>
              </tr>
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
              <form action="https://i.redd.it/bn2mhngwvm391.jpg">
                <label for="numHabitacion">Número de habitacion:</label>
                <input
                  type="number"
                  name="numHabitacion"
                  required
                  id="numHabitacion" min="0"
                />
                <br /><br />
                <label for="especialidad">Especialidad:</label>
                <input type="text" name="especialidad" required id="especialidad" />
                <br /><br />
                <label for="clinica">Clinica:</label>
                <select name="Clinica" id="clinica">
                  <option value="clinica1">Clinica1</option>
                  <option value="clinica2">Clinica2</option>
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