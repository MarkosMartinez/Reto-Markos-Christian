<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Editar Equipamiento</title>
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
        <li><a class="activo" href="VerCitas">Consultar Citas</a></li>
        <c:if test="${tipoLogin eq 'cliente'}">
        <li><a href="EditarPerfil">Editar Perfil</a></li>
        </c:if>
      </ul>
    </nav>
  </header>

  <body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
  <main>
  <div class="form-value">
   <form class="form-inline" action="EditarEquipamiento" method="POST">
   <c:set var="cantidad" value="0"/>
  <table class="table caption-top" style="position:absolute; top:90px">
  <caption>Editar equipamiento en: Nombre de la clinica</caption> <%//TODO Si es el director que lo pueda cambiar? %>
  <thead class="table-light">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nombre</th>
      <th scope="col">Precio</th>
      <th scope="col">Stock</th>
      <th scope="col">Editar</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="equip" items="${equipamiento}">
    <tr>
      <th scope="row">${equip.getId_Equipamiento()}</th>
      <td>${equip.getNombre_Equipamiento()}</td>
      <td>${equip.getPrecio()} €</td>
      <td><input type="number" value="${equip.getStock()}" min="0" name="stock-${cantidad}" required="required"> Uds</td>
      <td><a type="button" href="EditarEquipamiento?c=${cantidad}&v=${equip.getStock()+1}" class="btn btn-success">+1</a> <a type="button" href="EditarEquipamiento?c=${cantidad}&v=${equip.getStock()-1}" class="btn btn-danger">-1</a></td>
      <c:set var="cantidad" value="${cantidad + 1}"/>
    </tr>
    </c:forEach>
  </tbody>
</table>
<input type="text" value="${cantidad}" name="cantidad" readonly="readonly" hidden required="required">
<button type="submit" class="btn btn-warning botonFormulario" style="position:absolute; right: 0; top:300px">Actualizar todos!</button> <% //TODO Arreglar la posicion de este boton %>
</form>
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
        <p><a href="mailto:ikbdb@plaiaundi.net">smiling@hotmail.com</a></p>
      </div>
    </div>
    <div class="footer-derecha col-md-4 col-sm-6">
      <img src="img/logoProv.png" width="100" />
      <p class="name">Smiling &copy; 2023</p>
    </div>
  </footer>
</html>