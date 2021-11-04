#!/bin/bash
echo "SHELL SCRIPT START"
docker-compose down
echo "DOCKER DOWN"
docker rmi $(docker images -q)
echo "DOCKER BUILD"
docker-compose build --no-cache
echo "DOCKER RUN"
docker-compose up -d
echo "FINISH"