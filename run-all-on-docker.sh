#!/usr/bin/env bash

echo 'building docker images'
./gradlew clean build
./gradlew eureka-service:distDocker &
./gradlew zuul-service:distDocker &
./gradlew product-service:distDocker &
./gradlew price-service:distDocker &
./gradlew comment-service:distDocker &
./gradlew catalog-service:distDocker
#
echo 'Deploying services on docker, view logs in app.log'
docker-compose up -d && docker-compose logs &> app.log