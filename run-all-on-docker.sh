#!/usr/bin/env bash

echo 'building docker images'
./gradlew clean build
./gradlew eureka-service:distDocker &
./gradlew zuul-service:distDocker &
./gradlew product-service:distDocker &
./gradlew price-service:distDocker &
./gradlew comment-service:distDocker &
./gradlew catalog-service:distDocker &
./gradlew hystrix-dashboard-service:distDocker &
./gradlew turbine-service:distDocker

echo 'Deploying services on docker'
docker-compose up -d