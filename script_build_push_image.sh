#!/usr/bin/env bash

#Ejecución para construir la imagen del subsistema.
#Este comando ejecutará el Dockerfile que debe estar al mismo nivel que este script en el repositorio
docker build -t censo .

#NECESARIO SETEAR USUARIO/PASS DE DOCKER HUB COMO VARIABLES DE ENTORNO 
#EN EL PROYECTO DE TRAVIS RELACIONADO CON EL REPOSITORIO
#VER EL SIGUIENTE ENLACE PARA ELLO: 
# https://docs.travis-ci.com/user/environment-variables/#Defining-Variables-in-Repository-Settings

export DOCKER_ID_USER=manusg

#Login para la subida a Docker Hub
docker login --username=manusg --password=manuel123

docker tag censo $DOCKER_ID_USER/censo

#Subida de la imagen a Docker Hub
docker push $DOCKER_ID_USER/censo
