<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Nuestro Equipo</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
      crossorigin="anonymous"
    />

    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/nuestroEquipo.css" />
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
        <li><a class="activo" href="NuestroEquipo">Nuestro equipo</a></li>
        <li><a href="Tratamientos">Tratamientos</a></li>
        <li><a href="RealizarCita">Pedir Cita</a></li>
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
    <main>
      <h1 class="tituloSuperior">
        Equipo de la Clínica Dental Smiling <br />
        <span>PROFESIONALIDAD, RIGOR, SERIEDAD Y CERCANÍA</span>
      </h1>
      <div class="tarjetas">
        <div class="tarjeta">
          <div class="wrapper">
            <img
              src="img/personal/director.JPG"
              style="object-fit: cover; height: 400px"
              class="cover-image"
            />
          </div>
          <img src="img/personal/nombreDirector.png" class="title" />
          <img src="img/personal/directorHover.png" class="character" />
        </div>
      </div>
      <div class="texto-tarjeta">
        <h1>Director</h1>
        <p>
          El director de Smiling, el Dr. Iván Malagón, se enorgullece de liderar
          un equipo de profesionales comprometidos con brindar atención dental
          de alta calidad y personalizada a cada uno de sus pacientes.
        </p>
      </div>

      <div class="tarjetas salto d-flex justify-content-around">
        <div class="tarjeta">
          <div class="wrapper">
            <img
              src="img/personal/odontologo1.jpg"
              style="height: 430px; object-fit: cover"
              class="cover-image"
            />
          </div>
          <img
            src="img/personal/nombreOdontologo1.png"
            style="width: 280px"
            class="title"
          />
          <img
            src="img/personal/odontologo1Hover.png"
            style="width: 500px"
            class="character"
          />
        </div>

        <div class="tarjeta">
          <div class="wrapper">
            <img
              src="img/personal/odontologo2.jpg"
              style="height: 430px; object-fit: cover"
              class="cover-image"
            />
          </div>
          <img
            src="img/personal/nombreOdontologo2.png"
            style="width: 280px"
            class="title"
          />
          <img
            src="img/personal/odontologo2Hover.png"
            style="width: 200px"
            class="character"
          />
        </div>
      </div>

      <div class="textosOdontologos d-flex justify-content-around">
        <div class="texto-tarjeta">
          <h1>Odontólogo</h1>
          <p>
            Diego Ramirez es un odontólogo comprometido y apasionado que se dedica a proporcionar a sus pacientes una atención dental de alta calidad. Con años de experiencia en el campo, Diego es experto en una amplia gama de procedimientos dentales y utiliza las últimas tecnologías para garantizar los mejores resultados para sus pacientes.</p>
        </div>

        <div class="texto-tarjeta">
          <h1>Odontóloga</h1>
          <p>
            Ana Hernandez es una odontóloga altamente capacitada y
            experimentada, con una gran pasión por su trabajo. Ella se dedica a
            brindar a sus pacientes una atención dental de alta calidad,
            utilizando las últimas tecnologías y técnicas en su práctica.
          </p>
        </div>
      </div>

      <div class="tarjetas salto d-flex justify-content-around">
        <div class="tarjeta">
          <div class="wrapper">
            <img
              src="img/personal/auxiliarDental1.jpg"
              style="height: 430px; object-fit: cover"
              class="cover-image"
            />
          </div>
          <img
            src="img/personal/nombreAuxiliar1.png"
            style="width: 280px"
            class="title"
          />
          <img
            src="img/personal/auxiliarDental1Hover.png"
            style="width: 500px"
            class="character"
          />
        </div>

        <div class="tarjeta">
          <div class="wrapper">
            <img
              src="img/personal/recepcionista.jpg"
              style="height: 430px; object-fit: cover"
              class="cover-image"
            />
          </div>
          <img
            src="img/personal/nombreRecepcionista.png"
            style="width: 280px"
            class="title"
          />
          <img src="img/personal/recepcionistaHover.png" class="character" />
        </div>

        <div class="tarjeta">
          <div class="wrapper">
            <img
              src="img/personal/auxiliarDental2.jpg"
              style="height: 430px; object-fit: cover"
              class="cover-image"
            />
          </div>
          <img
            src="img/personal/nombreAuxiliar2.png"
            style="width: 280px"
            class="title"
          />
          <img
            src="img/personal/auxiliarDental2Hover.png"
            style="width: 300px"
            class="character"
          />
        </div>
      </div>

      <div class="textos d-flex justify-content-around">
        <div class="texto-tarjeta">
          <h1>Auxiliar dental</h1>
          <p>
            Gabriela Castro es una auxiliar dental altamente capacitada y
            dedicada, que trabaja en nuestra clínica dental con profesionalismo
            y pasión. Ella es responsable de asistir al dentista durante los
            procedimientos, gestionar los registros del paciente y asegurarse de
            que todo esté listo para la próxima cita.
          </p>
        </div>

        <div class="texto-tarjeta">
          <h1>Recepcionista</h1>
          <p>
            Nora Goikoetxea, la encantadora recepcionista de nuestra clínica
            dental. Nora es una persona muy amable y siempre tiene una sonrisa
            en su rostro para dar la bienvenida a los pacientes. Su calidez y
            profesionalismo son un reflejo del compromiso que tiene con su
            trabajo.
          </p>
        </div>

        <div class="texto-tarjeta">
          <h1>Auxiliar dental</h1>
          <p>Ahmet Mustafa es un auxiliar dental dedicado y eficiente que trabaja en nuestra clínica dental. Su compromiso con el bienestar de los pacientes es evidente en su habilidad para proporcionar un apoyo inestimable a los odontólogos y su capacidad para gestionar la información de los pacientes de manera efectiva. </p>
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