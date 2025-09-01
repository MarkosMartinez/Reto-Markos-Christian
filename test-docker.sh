#!/bin/bash

# Script para probar el Docker build localmente
# Este script maneja los casos comunes de problemas con certificados SSL

echo "🐳 Iniciando build de Docker para Reto Markos Christian..."

# Verificar que Docker está disponible
if ! command -v docker &> /dev/null; then
    echo "❌ Docker no está instalado o no está en el PATH"
    exit 1
fi

# Verificar que docker-compose está disponible  
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose no está instalado o no está en el PATH"
    exit 1
fi

echo "✅ Docker y Docker Compose disponibles"

# Intentar build de la imagen
echo "🔨 Construyendo imagen Docker..."
if docker build -t reto-app .; then
    echo "✅ Imagen Docker construida exitosamente"
else
    echo "❌ Error en build de Docker"
    echo "💡 Si hay errores de certificados SSL, puede ser necesario:"
    echo "   1. Configurar proxy corporativo si aplica"
    echo "   2. Usar una imagen base diferente"
    echo "   3. Ejecutar en un entorno con acceso a internet completo"
    exit 1
fi

# Test básico de docker-compose
echo "🧪 Validando configuración de docker-compose..."
if docker-compose config > /dev/null; then
    echo "✅ Configuración de docker-compose válida"
else
    echo "❌ Error en configuración de docker-compose"
    exit 1
fi

echo "🎉 Build de Docker completado exitosamente!"
echo ""
echo "Para ejecutar la aplicación:"
echo "  docker-compose up -d"
echo ""
echo "Para acceder:"
echo "  Aplicación: http://localhost:8080"
echo "  MySQL: localhost:3306"