# Reto Marcos Christian - Aplicación Clínica Odontológica

Esta es una aplicación web Java para gestión de clínicas odontológicas desarrollada con Maven, JSP/Servlets y MySQL.

## 🐳 Despliegue con Docker

### Prerrequisitos
- Docker
- Docker Compose

### Instrucciones de Despliegue

1. **Clonar el repositorio:**
   ```bash
   git clone <repository-url>
   cd Reto-Markos-Christian
   ```

2. **Construir y ejecutar con Docker Compose:**
   ```bash
   docker-compose up --build
   ```

3. **Acceder a la aplicación:**
   - Aplicación web: http://localhost:8080
   - Base de datos MySQL: localhost:3306

### Servicios Docker

#### 🌐 Aplicación Web (webapp)
- **Puerto:** 8080
- **Tecnologías:** Java 17, Tomcat 10, Maven
- **Encoding:** UTF-8 (soporte completo para caracteres españoles como ñ)
- **Healthcheck:** Verificación automática de disponibilidad

#### 🗄️ Base de Datos (mysql)
- **Puerto:** 3306
- **Versión:** MySQL 8.0
- **Base de datos:** clinica_odontologica
- **Usuario:** clinica_user
- **Contraseña:** clinica_pass
- **Charset:** utf8mb4 (soporte completo para caracteres españoles)
- **Healthcheck:** Verificación automática de conexión

### Configuración de Caracteres Españoles (ñ)

La aplicación está configurada para manejar correctamente caracteres españoles:

- **Aplicación Java:** UTF-8 encoding en JVM y Tomcat
- **Base de datos:** Charset utf8mb4 con collation utf8mb4_unicode_ci
- **Tomcat:** URIEncoding="UTF-8" en server.xml
- **Sistema:** Locales es_ES.UTF-8

### Comandos Útiles

```bash
# Iniciar servicios
docker-compose up -d

# Ver logs
docker-compose logs -f

# Reconstruir aplicación
docker-compose up --build webapp

# Parar servicios
docker-compose down

# Parar y eliminar volúmenes (CUIDADO: elimina datos de BD)
docker-compose down -v

# Acceder a contenedor de aplicación
docker exec -it reto-webapp bash

# Acceder a contenedor de MySQL
docker exec -it reto-mysql mysql -u clinica_user -p
```

### Desarrollo

Para desarrollo local sin Docker:

1. **Compilar con Maven:**
   ```bash
   mvn clean package
   ```

2. **Desplegar WAR en Tomcat local**

### Estructura del Proyecto

```
├── src/main/java/          # Código Java (Servlets, DAO, DTO)
├── src/main/webapp/        # JSP, CSS, imágenes
├── BBDD/                   # Scripts SQL
├── Dockerfile              # Configuración Docker
├── docker-compose.yml     # Orquestación Docker
├── pom.xml                # Configuración Maven
└── README.md              # Este archivo
```

### Solución de Problemas

**La aplicación no arranca:**
- Verificar que MySQL esté saludable: `docker-compose ps`
- Ver logs: `docker-compose logs webapp`

**Problemas con caracteres españoles:**
- Los contenedores están configurados con UTF-8 y soporte para español
- Verificar que el navegador use encoding UTF-8

**Error de conexión a base de datos:**
- Verificar que el contenedor MySQL esté funcionando
- Las variables de entorno se configuran automáticamente