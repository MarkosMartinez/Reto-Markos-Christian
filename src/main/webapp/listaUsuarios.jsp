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
    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/listaEmpleadosClientes.css" />

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
        <li><a href="inicio.html">Inicio</a></li>
        <li><a href="nuestroEquipo.html">Nuestro equipo</a></li>
        <li><a href="">Tratamientos</a></li>
        <li><a href="Login.html">Iniciar sesión/Registrarse</a></li>
        <li><a href="">Pedir Cita</a></li>
        <li><a href="">Contáctanos</a></li>
        <li><a href="verCitas.html">Consultar Citas</a></li>
        <li>
          <a class="activo" href="listaEmpleadosClientes.html"
            >Lista empleados</a
          >
        </li>
      </ul>
    </nav>
  </header>

  <body>
    <main>
      <section>
        <div class="tituloYopcion">
        <c:if test="${director eq true}">
          <h1 class="titulo">Lista de</h1>
          <select class="opcionGestion" name="opcionGestion">
            <option value="empleados">EMPLEADOS</option>
            <option value="clientes">CLIENTES</option>
          </select>
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
                <% //TODO <th>Clínica</th> Solo si son los empleados a los que quieres ver%>
                <th>Editar</th>
                <th>Eliminar</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
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
            </tbody>
          </table>
        </div>
        <c:if test="${director eq true}">
          <a href="#añadir">
          <button class="button" style="vertical-align: middle">
            <span>ㅤAñadirㅤ</span>
          </button>
        </a>
          </c:if>

        <div id="añadir" class="overlay">
          <div class="popup">
            <h2 style="color: rgb(0, 132, 255)">Añadir empleado</h2>
            <a class="close" href="#">&times;</a>
            <div class="content">
              <form action="https://i.redd.it/bn2mhngwvm391.jpg">
                <label for="dni">DNI:</label>
                <input
                  type="text"
                  name="DNI_Emp"
                  required
                  id="dni"
                  pattern="[0-9]{8}[A-Za-z]{1}"
                  title="Debe poner 8 números y una letra"
                  minlength="9"
                  maxlength="9"
                />
                <br /><br />
                <label for="nombre">Nombre:</label>
                <input type="text" name="Nombre" required id="nombre" />
                <br /><br />
                <label for="apellidos">Apellidos:</label>
                <input type="number" name="Apellidos" required id="apellidos" />
                <br /><br />
                <label for="correo">Correo:</label>
                <input type="email" name="Correo" required id="correo" />
                <br /><br />
                <label for="contraseña">Contraseña:</label>
                <input
                  type="password"
                  name="Contraseña"
                  required
                  id="contraseña"
                />
                <br /><br />
                <label for="fecha_nac">Fecha de Nacimiento:</label>
                <input
                  type="date"
                  name="Fecha_Nacimiento"
                  required
                  id="fecha_nac"
                />
                <br /><br />
                <label for="puesto">Puesto:</label>
                <select name="Puesto" id="puesto">
                  <option value="puesto1">Dentista</option>
                  <option value="puesto2">Dentista2</option>
                </select>
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