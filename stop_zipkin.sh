#!/bin/bash

# Nombre del contenedor
CONTAINER_NAME="zipkin-container"

# Verifica si el contenedor está corriendo
if [ $(docker ps -q -f name=$CONTAINER_NAME) ]; then
    # Detiene el contenedor
    docker stop $CONTAINER_NAME

    # Elimina el contenedor
    docker rm $CONTAINER_NAME

    echo "El contenedor $CONTAINER_NAME se ha detenido y eliminado."
else
    echo "El contenedor $CONTAINER_NAME no está corriendo."
fi
