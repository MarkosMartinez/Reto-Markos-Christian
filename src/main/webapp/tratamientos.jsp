<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tratamientos</title>

    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/tratamientos.css" />
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
        <li><a class="activo" href="Tratamientos">Tratamientos</a></li>
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
      <div class="brackets">
        <div class="titulos">
          <h1>BRACKETS</h1>
          <button class="descripcion">
            <span class="hover-underline-animation"> Leer más </span>
            <svg
              viewBox="0 0 46 16"
              height="10"
              width="30"
              xmlns="http://www.w3.org/2000/svg"
              id="arrow-horizontal"
            >
              <path
                transform="translate(30)"
                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                data-name="Path 10"
                id="Path_10"
              ></path>
            </svg>
          </button>
          <p>
            Los brackets dentales son un tipo de dispositivo ortodóntico
            utilizado para corregir la posición de los dientes y mejorar la
            mordida. Están compuestos de pequeñas piezas metálicas o de cerámica
            que se adhieren a los dientes con un adhesivo especial y se conectan
            mediante arcos y ligaduras. Los brackets trabajan aplicando una
            presión constante y gradual sobre los dientes para moverlos
            lentamente a su posición correcta. Este proceso puede tomar varios
            meses o incluso años, dependiendo de la gravedad de la malposición
            dental. Los brackets dentales son una herramienta importante para
            mejorar la salud y estética dental, permitiendo a las personas tener
            una sonrisa más saludable y atractiva.
          </p>
        </div>
      </div>

      <div class="endodoncia">
        <div class="titulos">
          <h1 style="color: black">ENDODONCIA</h1>
          <button class="descripcion">
            <span
              class="hover-underline-animation"
              style="color: rgb(4, 104, 255)"
            >
              Leer más
            </span>
            <svg
              viewBox="0 0 46 16"
              height="10"
              width="30"
              xmlns="http://www.w3.org/2000/svg"
              id="arrow-horizontal"
            >
              <path
                transform="translate(30)"
                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                data-name="Path 10"
                id="Path_10"
              ></path>
            </svg>
          </button>
          <p>
            La endodoncia es un procedimiento dental que se utiliza para tratar
            problemas en el interior de un diente, como una infección o
            inflamación del tejido dental conocido como pulpa. Esta técnica
            consiste en remover la pulpa dañada y limpiar el espacio interior
            del diente, para luego sellarlo y protegerlo con una corona o una
            obturación dental. La endodoncia es una de las opciones de
            tratamiento más comunes para salvar un diente que de otra manera
            podría necesitar ser extraído. Al preservar el diente natural, la
            endodoncia puede ayudar a prevenir la necesidad de prótesis o
            implantes dentales costosos y prolongar la vida útil de los dientes
            naturales. Además, los avances en la tecnología dental han hecho que
            este procedimiento sea más rápido, más preciso y más cómodo para los
            pacientes que lo necesitan.
          </p>
        </div>
      </div>

      <div class="protesis">
        <div class="titulos">
          <h1 style="color: rgb(255, 255, 255)">PRÓTESIS</h1>
          <button class="descripcion">
            <span class="hover-underline-animation" style="color: rgb(0, 0, 0)">
              Leer más
            </span>
            <svg
              viewBox="0 0 46 16"
              height="10"
              width="30"
              xmlns="http://www.w3.org/2000/svg"
              id="arrow-horizontal"
            >
              <path
                transform="translate(30)"
                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                data-name="Path 10"
                id="Path_10"
              ></path>
            </svg>
          </button>
          <p>
            Una prótesis dental es un dispositivo dental personalizado que se
            utiliza para reemplazar los dientes perdidos o dañados. Esta
            herramienta puede ser removible o fija, y está diseñada para imitar
            la apariencia y la función de los dientes naturales. <br /><br />
            Hay varios tipos de prótesis dentales disponibles, que varían desde
            dentaduras completas para reemplazar todos los dientes hasta puentes
            fijos para reemplazar uno o varios dientes faltantes. Las prótesis
            dentales pueden estar hechas de materiales como resina, cerámica o
            metal, y se adaptan específicamente a la boca del paciente para
            garantizar un ajuste cómodo y seguro.
          </p>
        </div>
      </div>

      <div class="odonPreventiva">
        <div class="titulos">
          <h1>ODONTOLOGÍA PREVENTIVA</h1>
          <button class="descripcion">
            <span
              class="hover-underline-animation"
              style="color: rgb(0, 0, 0)"
            >
              Leer más
            </span>
            <svg
              viewBox="0 0 46 16"
              height="10"
              width="30"
              xmlns="http://www.w3.org/2000/svg"
              id="arrow-horizontal"
            >
              <path
                transform="translate(30)"
                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                data-name="Path 10"
                id="Path_10"
              ></path>
            </svg>
          </button>
          <p>
            La odontología preventiva es una rama de la odontología que se
            centra en la prevención de problemas dentales antes de que ocurran.
            En lugar de tratar problemas existentes, la odontología preventiva
            se enfoca en mantener la salud bucal y prevenir problemas futuros. <br><br>
            Los dentistas y sus equipos pueden ayudar a los pacientes a prevenir
            la caries dental, la enfermedad de las encías y otros problemas
            dentales mediante la educación en hábitos de higiene oral efectivos,
            como el cepillado y el uso de hilo dental, y la identificación
            temprana de problemas dentales potenciales a través de exámenes y
            evaluaciones regulares.
          </p>
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