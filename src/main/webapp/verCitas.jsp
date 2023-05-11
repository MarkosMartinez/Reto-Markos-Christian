<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Consultar Citas</title>
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
    <link rel="stylesheet" href="css/verCitas.css" />
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
        <c:if test="${tipoLogin eq 'ninguno'}">
        	<li><a href="LoginYRegistro">Iniciar sesión/Registrarse</a></li>
        </c:if>
        <li><a href="RealizarCita">Pedir Cita</a></li>
        <li><a class="activo" href="VerCitas">Consultar Citas</a></li>
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
        <li><a href="LoginYRegistro">Cerrar Sesion</a></li>
      </ul>
    </nav>
  </header>

  <body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <main>
    
    
      <div class="ag-format-container">
      
      <c:if test="${director eq 'true'}">
		    <div style="max-width: 100%; margin: auto; display: flex; justify-content: center; align-items: center;">
			    <h1 class="listaCitas">Lista de citas de</h1>
			    <form action="VerCitas" method="POST" style="display: inline-block; margin-top: 98px;">
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
          	  <div style="max-width: 100%; margin: auto; display: flex; justify-content: center; align-items: center;">
			    <h1 class="listaCitas">Lista de citas</h1>
			  </div>
            </c:if>
      
       <div id="formularioCita" class="overlay">
          <div class="popup">
            <h2 style="color: rgb(0, 132, 255);">Formulario de cita</h2>
            <a class="close" href="#volver">&times;</a>
            <div class="content">
              <form action="VerCitas" method="POST">
              	<input type="text" value="formcita" name="tipo" readonly="readonly" hidden required="required">
				<input type="text" value="${editardni}" name="editardni" readonly="readonly" hidden required="required">
				<input type="date" required readonly="readonly" name="editarfecha" value="${editarfecha}" hidden>
				<input type="time" required readonly="readonly" name="editarhora" value="${editarhora}" hidden>
                <label>Clínica:</label>
                <input type="text" required value="${editarclinica}" name="editarclinica" readonly="readonly">
                <br><br>
                <label for="cliente">Cliente:</label>
                <input type="text" required id="cliente" value="${editarcliente}" readonly="readonly">
                <br><br>
                <label for="editartelefono">Teléfono:</label>
                <input type="number" required id="editartelefono" name="editartelefono" readonly="readonly" value="${editartelefono}">
                <br><br>
                <label for="editarempleado">Empleado:</label>
                	<select name="editarempleado" required="required">
                	 <c:forEach var="empleado" items="${empleados}">
					 	<option value="${empleado.dni_Emp}">${empleado.nombre} ${empleado.apellidos}</option>
					</c:forEach>
					</select>
                <br><br>
                <label for="informe">Observaciones e informe:</label><br>
                <textarea name="informe" id="informe" cols="80" rows="4"></textarea>
                <br><br>
                <label for="equipamiento">Uso de equipamiento</label>
                <input type="checkbox" id="equipamiento" name="equipamiento">

                <br><br>

                <button type="submit" class="botonFormulario">Guardar</button>
              </form>
            </div>
          </div>
        </div>
      
        <c:if test="${aviso eq 'borradocorrecto'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fas fa-trash fa-shake fa-lg" style="color: #ffffff;"></i> &nbsp; &nbsp;
  <span>Cita cancelada correctamente!</span>
</div>
</div>
        </c:if>
        
        <c:if test="${aviso eq 'citaactualizada'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fa-solid fa-pen fa-bounce fa-lg" style="color: #ffffff;"></i> &nbsp; &nbsp;
  <span>Cita actualizada correctamente!</span>
</div>
</div>
        </c:if>
        
        <c:if test="${aviso eq 'citacreada'}">
        	<div class="alerta">
	  <div class="alert alert-success check alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  <i class="fa-solid fa-calendar-plus fa-bounce fa-lg" style="color: #ffffff;"></i> &nbsp; &nbsp;
  <span>Cita creada correctamente!</span>
</div>
</div>
        </c:if>
        
        <c:if test="${aviso eq 'borradoincorrecto'}">
        <div class="alerta">
        	  <div class="alert alert-warning warning alert-dismissible fade show" role="alert">
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
 <i class="fa-solid fa-triangle-exclamation fa-bounce" style="color: #ffffff;"></i>
  &nbsp; &nbsp;
  <span>Error no se ha podido eliminar la cita!</span>
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

		
 		<c:if test="${citasPosteriores.size() == 0 && tipoLogin ne 'empleado'}">
 		
 		<div class="ag-courses_box">
          <div class="ag-courses_item">
            <div class="ag-courses-item_link">

              <div class="ag-courses-item_title">
                <p style="color: black" >No tienes ninguna cita todavia!</p>
                <p style="font-size: 18px;color: black">Prueba a pedir una cita con el boton de abajo.</p>
              </div>
              <a class="cssbuttons-io-button" href="RealizarCita"> Pedir Cita
			  <div class="icon">
			    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"></path></svg>
			  </div>
			</a>
            </div>
          </div>
        </div>
 		
 		</c:if>
 		
 		<c:if test="${citasPosteriores.size() == 0 && tipoLogin eq 'empleado'}">
 		
 		<div class="ag-courses_box">
          <div class="ag-courses_item">
            <div class="ag-courses-item_link">

              <div class="ag-courses-item_title">
                <p style="color: black" >No existe ninguna cita todavia!</p>
                <p style="font-size: 18px;color: black">Hoy tienes el dia libre!</p>
              </div>
            </div>
          </div>
        </div>
 		
 		</c:if>
        
		

        <div class="ag-courses_box">
        <c:set var="actual" value="0"/>
        <c:if test="${citasPosteriores.size() >= 1}">
               <c:forEach var="cita" items="${citasPosteriores}">
           	 <c:forEach var="clinica" items="${clinicas}">
           		 <c:if test="${clinica.id_clinica eq cita.id_Clinica}">
            		 <c:forEach var="cliente" items="${clientes}">
            		  <c:if test="${cliente.dni eq cita.dni_Cliente}">
          <div class="ag-courses_item">
            <div class="ag-courses-item_link">
              <div class="ag-courses-item_bg"></div>

              <div class="ag-courses-item_title">
                <p>Nombre: ${cliente.nombre} ${cliente.apellidos}</p>
                <p style="font-size: 18px;">Teléfono/s:
                <c:forEach var="telefono" items="${telefonos}">
                  <c:if test="${telefono.dni eq cita.dni_Cliente}">
               		 ${telefono.telefono} 
               	  </c:if>
                </c:forEach>
                </p>
                <p style="font-size: smaller;">Clínica: ${clinica.nombre_clinica}</p>
                <p style="font-size: 16px;">Dirección: ${clinica.direccion}</p>
              </div>

              <div class="ag-courses-item_date-box">
                Fecha:
               <span class="ag-courses-item_date">
                      <fmt:formatDate value="${cita.fecha_Cita}" pattern="dd/MM/yyyy" /> ${horasPosteriores.get(actual)}
                </span>

                <span>
                  <a href="EliminarCita?id_clinica=${cita.id_Clinica}&dni=${cita.dni_Cliente}&fecha=${cita.fecha_Cita}&hora=${horasPosteriores.get(actual)}">
                    <button class="primary-button">
                      <i class="fas fa-trash"></i>
                    </button>
                  </a>
                </span>
                <c:set var="actual" value="${actual + 1}"/>
              </div>
            </div>
          </div>
				   </c:if>
              		</c:forEach>
              	</c:if>
              </c:forEach>
             	</c:forEach>
             </c:if>

        </div>
      
      		<c:if test="${citasPosteriores.size() >= 1}">
            	<h1 style="color: red;text-align: center;">Citas Anteriores</h1>
            </c:if>
            	
<div class="ag-courses_box">
  <c:set var="actual" value="0"/>
 <c:if test="${citasAnteriores.size() >= 1}">
  <c:forEach var="cita" items="${citasAnteriores}">
    <c:forEach var="clinica" items="${clinicas}">
      <c:if test="${clinica.id_clinica eq cita.id_Clinica}">
        <c:forEach var="cliente" items="${clientes}">
          <c:if test="${cliente.dni eq cita.dni_Cliente}">
            <div class="ag-courses_item">
              <div class="ag-courses-item_link">
                <div class="ag-courses-item_bg2"></div>

                <div class="ag-courses-item_title">
                  <p>Nombre: ${cliente.nombre} ${cliente.apellidos}</p>
                  <p style="font-size: smaller;">Clínica: ${clinica.nombre_clinica}</p>
                  <p style="font-size: 18px;">Teléfono/s:
                 <c:forEach var="telefono" items="${telefonos}">
                	 <c:if test="${telefono.dni eq cita.dni_Cliente}">
               			 ${telefono.telefono} 
                	 </c:if>
               	 </c:forEach>
               	 </p>
                  <c:forEach var="historial" items="${historiales}">
                    <c:if test="${historial.DNI eq cita.dni_Cliente && historial.fecha_Revision eq cita.fecha_Cita && historial.hora_Revision == horasAnteriores.get(actual)}">
                    <c:forEach var="empleado" items="${empleados}">
                    <c:if test="${empleado.dni_Emp eq historial.atendido}">
					 	<p style="font-size: 16px;">Atendido por: ${empleado.nombre} ${empleado.apellidos}</p>
					 	</c:if>
					</c:forEach>
                       
                       <p style="font-size: 16px;">Observaciones: ${historial.observaciones}</p>
                     
                    </c:if>
                  </c:forEach>
                  
                </div>

                <div class="ag-courses-item_date-box2">
                  Fecha:
                  <span class="ag-courses-item_date">
                    <fmt:formatDate value="${cita.fecha_Cita}" pattern="dd/MM/yyyy" /> ${horasAnteriores.get(actual)}
                  </span>
                  <c:if test="${tipoLogin eq 'empleado'}">
                    <span>
                    <c:set var="encontrado" value="0"/>
                    <c:forEach var="telefono" items="${telefonos}">
					<c:if test="${telefono.dni eq cliente.dni}">
					<c:if test="${encontrado < 1}">
					  <c:set var="encontrado" value="1"/>
                      <a href="VerCitas?editarid_clinica=${cita.id_Clinica}&editardni=${cliente.dni}&editarclinica=${clinica.nombre_clinica}&editartelefono=${telefono.telefono}&editarfecha=${cita.fecha_Cita}&editarhora=${horasAnteriores.get(actual)}&editarcliente=${cliente.nombre} ${cliente.apellidos}#formularioCita">
                    </c:if>
					</c:if>
				</c:forEach>
                        <button class="primary-button2" type="button">
                          <i class="fa-regular fa-pen-to-square" style="color: #ffffff;"></i>
                        </button>
                      </a>
                    </span>
                  </c:if>
                <c:set var="actual" value="${actual + 1}"/>
              </div>
            </div>
          </div>
				   </c:if>
              		</c:forEach>
              	</c:if>
              </c:forEach>
             	</c:forEach>
             		</c:if>

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
        <p><a href="mailto:ikbdb@plaiaundi.net">smiling@hotmail.com</a></p>
      </div>
    </div>
    <div class="footer-derecha col-md-4 col-sm-6">
      <img src="img/logoProv.png" width="100" />
      <p class="name">Smiling &copy; 2023</p>
    </div>
  </footer>


</html>