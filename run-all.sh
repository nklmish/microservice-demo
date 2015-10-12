#!/usr/bin/env bash

./gradlew clean build
./gradlew eureka-service:dockerBuildImage &
./gradlew zuul-service:dockerBuildImage &
./gradlew product-service:dockerBuildImage &
./gradlew price-service:dockerBuildImage &
./gradlew comment-service:dockerBuildImage &
./gradlew catalog-service:dockerBuildImage &
./gradlew hystrix-dashboard-service:dockerBuildImage &
./gradlew turbine-service:dockerBuildImage
