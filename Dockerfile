# Multi-stage build para aplicación Java con Maven y Tomcat
FROM maven:3.9-eclipse-temurin-17 AS build

# Establecer variables de entorno para UTF-8
ENV LANG=es_ES.UTF-8
ENV LANGUAGE=es_ES:es
ENV LC_ALL=es_ES.UTF-8

# Instalar locales para soporte de español
RUN apt-get update && \
    apt-get install -y locales && \
    sed -i 's/# es_ES.UTF-8 UTF-8/es_ES.UTF-8 UTF-8/' /etc/locale.gen && \
    locale-gen es_ES.UTF-8 && \
    update-locale LANG=es_ES.UTF-8 && \
    rm -rf /var/lib/apt/lists/*

# Configurar directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Maven primero (para aprovechar cache de Docker)
COPY pom.xml .

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Fase 2: Runtime con Tomcat
FROM tomcat:10.1-jdk17-openjdk-slim

# Establecer variables de entorno para UTF-8 y Docker
ENV LANG=es_ES.UTF-8
ENV LANGUAGE=es_ES:es
ENV LC_ALL=es_ES.UTF-8
ENV DOCKER_ENV=true

# Instalar locales para soporte de español
RUN apt-get update && \
    apt-get install -y locales && \
    sed -i 's/# es_ES.UTF-8 UTF-8/es_ES.UTF-8 UTF-8/' /etc/locale.gen && \
    locale-gen es_ES.UTF-8 && \
    update-locale LANG=es_ES.UTF-8 && \
    rm -rf /var/lib/apt/lists/*

# Configurar Tomcat para UTF-8
RUN echo 'JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Duser.timezone=Europe/Madrid"' >> /usr/local/tomcat/bin/setenv.sh

# Configurar server.xml para UTF-8
RUN sed -i 's/port="8080"/port="8080" URIEncoding="UTF-8"/g' /usr/local/tomcat/conf/server.xml

# Remover aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR generado desde la fase de build
COPY --from=build /app/target/Reto-3EVA-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponer puerto 8080
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]