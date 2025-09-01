# Single-stage build - using pre-built WAR
FROM tomcat:9.0-jdk17-openjdk-slim

# Establecer variables de entorno para UTF-8 y Docker
ENV LANG=es_ES.UTF-8
ENV LANGUAGE=es_ES:es
ENV LC_ALL=es_ES.UTF-8
ENV DOCKER_ENV=true

# Configurar Tomcat para UTF-8
RUN echo 'JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Duser.timezone=Europe/Madrid"' >> /usr/local/tomcat/bin/setenv.sh

# Configurar server.xml para UTF-8
RUN sed -i 's/port="8080"/port="8080" URIEncoding="UTF-8"/g' /usr/local/tomcat/conf/server.xml

# Remover aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR generado localmente
COPY target/Reto-3EVA-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponer puerto 8080
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]