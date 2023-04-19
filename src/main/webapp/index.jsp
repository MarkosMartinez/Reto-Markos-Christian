<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Inicio</title>
    <link rel="stylesheet" href="css/inicio.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
      crossorigin="anonymous"
    />
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/menu.css" />
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
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesi�n/Registrarse</a></li>
        </c:if>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <c:if test="${tipoLogin eq 'cliente'}">
        <li><a href="">Editar Perfil</a></li>
        </c:if>
      </ul>
    </nav>
  </header>

  <body>
    <div>
      <section class="imgPrincipal">
        <br />
        <h1 class="slogan">
          Frescura como la brisa,
          <span class="sloganAzul">
            en tu sonrisa. <br />
            Ven a Smiling porque tu sonrisa,</span
          >
          nos importa.
        </h1>
      </section>
      <svg
        class="waves"
        xmlns="http://www.w3.org/2000/svg"
        xmlns:xlink="http://www.w3.org/1999/xlink"
        viewBox="0 24 150 28"
        preserveAspectRatio="none"
        shape-rendering="auto"
      >
        <defs>
          <path
            id="gentle-wave"
            d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"
          />
        </defs>
        <g class="parallax">
          <use
            xlink:href="#gentle-wave"
            x="48"
            y="0"
            fill="rgba(255,255,255,0.7)"
          />
          <use
            xlink:href="#gentle-wave"
            x="48"
            y="3"
            fill="rgba(255,255,255,0.5)"
          />
          <use
            xlink:href="#gentle-wave"
            x="48"
            y="5"
            fill="rgba(255,255,255,0.3)"
          />
          <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" />
        </g>
      </svg>
    </div>

    <main>
      <h1 style="color: rgb(0, 102, 255)">
        Clínica Dental Smiling en Torremolinos (pendiente de bombardear)
      </h1>
      <br />

      <div class="textoMain">
        <p>
          Una bonita sonrisa es sinónimo de salud y bienestar. Nuestra
          experiencia y especialización permite personalizar cada caso para
          conseguir un resultado perfecto gracias a tratamientos de última
          tecnología.
        </p>

        <p>
          Manteniendo la filosofía de calidad y atención que la inspiró desde el
          primer día, Clínica Dental Smiling se basa hoy en tres pilares
          fundamentales: calidad en el servicio, equipos de última tecnología y
          formación en las últimas técnicas y en tratamientos dentales más
          innovadores, siempre con especial atención al paciente personalizando
          los tratamientos y ofreciendo un trato cercano. Descubre cuales son
          los tratamientos más indicados para mejorar y fortalecer tu sonrisa.
        </p>
      </div>

      <div class="contenedorTextoImg">
        <div
          class="columns image"
          style="background-image: url('img/primeraImg.jpg')"
        >
          &nbsp;
        </div>

        <div class="columns content">
          <div class="content-container">
            <h5 style="color: rgba(40, 106, 255, 1)">
              En Smiling tu sonrisa está en manos de expertos
            </h5>
            <p>
              En Smiling aseguramos la salud dental y el bienestar de nuestros
              pacientes. Aplicamos tratamientos dentales avanzados junto con el
              mejor equipo de dentistas expertos en Ortodoncia, Periodoncia,
              Implantología, Estética Dental, Prótesis dentales, y más. Nuestro
              objetivo es que siempre tengas una clínica cerca de ti para
              ayudarte a acceder al tratamiento que necesitas con unas cómodas
              condiciones de pago y financiación.
            </p>
          </div>
        </div>
      </div>

      <div class="contenedorTextoImg">
        <div class="columns content">
          <div class="content-container">
            <h5 style="color: rgba(40, 106, 255, 1)">
              Tus dientes te lo agradecerán
            </h5>
            <p>
              En nuestra clínica odontológica, no solo nos enfocamos en tratar
              problemas dentales existentes, sino también en prevenirlos.
              Creemos que la prevención es la clave para una sonrisa saludable y
              hermosa a largo plazo. Al visitar nuestra clínica, nuestros
              pacientes obtienen acceso a servicios de odontología preventiva y
              cosmética, como limpiezas dentales regulares, blanqueamiento
              dental y tratamientos de ortodoncia. Además, nuestro equipo de
              dentistas altamente capacitados trabaja con cada paciente para
              desarrollar un plan de tratamiento personalizado y efectivo que
              satisfaga sus necesidades y objetivos. Al visitarnos, nuestros
              pacientes pueden disfrutar de una sonrisa más brillante, saludable
              y hermosa que durará toda la vida.
            </p>
          </div>
        </div>

        <div
          class="columns image"
          style="background-image: url('img/segundaImg.JPG')"
        >
          &nbsp;
        </div>
      </div>

      <div class="contenedorTextoImg">
        <div
          class="columns image"
          style="background-image: url('img/terceraImg.jpg')"
        >
          &nbsp;
        </div>

        <div class="columns content">
          <div class="content-container">
            <h5 style="color: rgba(40, 106, 255, 1)">
              Te sentirás como en casa
            </h5>
            <p>
              En nuestra clínica odontológica, nos enorgullece ofrecer un
              ambiente cálido y acogedor para todos nuestros pacientes. Sabemos
              que visitar al dentista puede ser estresante y, por eso, nos
              esforzamos por crear un ambiente relajante y agradable para
              nuestros pacientes. Nuestro personal es amable, atento y siempre
              está dispuesto a responder cualquier pregunta o inquietud que
              nuestros pacientes puedan tener. Además, nos esforzamos por ser
              accesibles y convenientes para nuestros pacientes, ofreciendo
              horarios flexibles y opciones de financiamiento para ayudar a
              nuestros pacientes a recibir el cuidado dental que necesitan y
              merecen.Visítanos y experimenta nuestra atención dental
              personalizada y de alta calidad en un ambiente cómodo y relajado."
            </p>
          </div>
        </div>
      </div>
    
     
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

    </main>
  </body>
</html>
