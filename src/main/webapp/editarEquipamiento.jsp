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
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/equipamiento.css">
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
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
        </c:if>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a href="">Contactanos</a></li>
        <li><a href="VerCitas">Consultar Citas</a></li>
        <li><a class="activo" href="EditarEquipamiento">Editar Equipamiento</a></li>
      </ul>
    </nav>
  </header>

<body>
    <main>
        <section>
            <h1>Lista de equipamiento</h1>
            <div class="form-value">
            <form class="form-inline" action="EditarEquipamiento" method="POST">
            <input type="text" value="actualizar" name="tipo" readonly="readonly" hidden required="required">
  			 <c:set var="cantidad" value="0"/>
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
            <div class="tbl-content">
              <table cellpadding="0" cellspacing="0" border="0">
                <tbody>
				  <c:forEach var="equip" items="${equipamiento}">
				    <tr>
				      <th scope="row">${equip.getId_Equipamiento()}</th>
				      <td>${equip.getNombre_Equipamiento()}</td>
				      <td>${equip.getPrecio()} €</td>
				      <td><input type="number" value="${equip.getStock()}" min="0" name="stock-${cantidad}" required="required" class="inputs"> Uds</td>
				      <td><a type="button" href="EditarEquipamiento?c=${cantidad}&v=${equip.getStock()-1}" class="btn btn-danger">-1</a></td>
				      <c:set var="cantidad" value="${cantidad + 1}"/>
				    </tr>
				    </c:forEach>
				  </tbody>
              </table>
             </div>
				<input type="text" value="${cantidad}" name="cantidad" readonly="readonly" hidden required="required">
				<a href="#formularioEquip" type="button" class="btn btn-success btnInsertar">Insertar Equipamiento</a>
				<button type="submit" class="btn btn-warning botonFormulario">Actualizar todos!</button> <% //TODO Arreglar la posicion de este boton %>
				
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
				
				       <div id="formularioEquip" class="overlay">
          <div class="popup">
            <h2 style="color: rgb(0, 132, 255);">Insertar Equipamiento</h2>
            <a class="close" href="#volver">&times;</a>
            <div class="content">
              <form action="EditarEquipamiento" method="POST">
				<input type="text" value="insert" name="tipo" readonly="readonly" hidden required="required">
                <label for="nombre" >Nombre:</label>
                <input type="text" required id="nombre" name="nombre" readonly="readonly">
                <br><br>
                <label for="precio">Precio:</label>
                <input type="number" required id="precio" min="0" readonly="readonly">
                <br><br>
                <label for="stock">Stock:</label>
                <input type="number" required id="stock"  min="0" readonly="readonly">
                <br><br>
                <button type="submit" class="botonInsertar">Insertar</button>
              </form>
            </div>
          </div>
        </div>
        
			</form>
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