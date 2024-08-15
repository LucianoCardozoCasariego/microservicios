#!/bin/bash

# Nombre del contenedor
CONTAINER_NAME="zipkin-container"

# Verifica si el contenedor ya está corriendo
if [ $(docker ps -q -f name=$CONTAINER_NAME) ]; then
    echo "El contenedor $CONTAINER_NAME ya está corriendo en el puerto 9411."
else
    # Ejecuta el contenedor en segundo plano
    docker run -d --name $CONTAINER_NAME -p 9411:9411 openzipkin/zipkin

    # Verifica si el contenedor se inició correctamente
    if [ $? -eq 0 ]; then
        echo "El contenedor $CONTAINER_NAME se ha iniciado exitosamente en el puerto 9411."
    else
        echo "Hubo un error al iniciar el contenedor $CONTAINER_NAME."
    fi
fi
