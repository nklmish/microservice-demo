#!/usr/bin/env bash

./gradlew clean build
./gradlew eureka-service:bootRun &
./gradlew zuul-service:bootRun &
./gradlew product-service:bootRun &
./gradlew price-service:bootRun &
./gradlew comment-service:bootRun &
./gradlew catalog-service:bootRun &
./gradlew hystrix-dashboard-service:bootRun &
./gradlew turbine-service:bootRun
