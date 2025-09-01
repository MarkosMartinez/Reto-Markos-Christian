#!/bin/bash

# Script para construir y ejecutar la aplicación con Docker
# Build and run script for the application with Docker

echo "🔨 Compilando la aplicación con Maven..."
echo "🔨 Building application with Maven..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar la aplicación"
    echo "❌ Error building the application"
    exit 1
fi

echo "✅ Aplicación compilada exitosamente"
echo "✅ Application built successfully"

echo "🐳 Iniciando contenedores Docker..."
echo "🐳 Starting Docker containers..."
docker compose up --build

echo "🌐 Aplicación disponible en http://localhost:8080"
echo "🌐 Application available at http://localhost:8080"