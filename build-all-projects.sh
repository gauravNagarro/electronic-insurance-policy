#!/bin/sh

cd api-gateway; gradle clean build; cd ..
cd auth-server; gradle clean build; cd ..
cd config-server; gradle clean build; cd ..
cd task-webservice; gradle clean build; cd ..
cd user-webservice; gradle clean build; cd ..
cd web-portal; gradle clean build; cd ..
cd webservice-registry; gradle clean build; cd ..
cd comments-webservice; gradle clean build; cd ..
