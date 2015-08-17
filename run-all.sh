#!/usr/bin/env bash

cd eureka-service; ./gradlew --daemon clean build distDocker; cd -
cd zuul-service; ./gradlew --daemon clean build distDocker; cd -
cd product-service; ./gradlew --daemon clean build distDocker; cd -
cd price-service; ./gradlew --daemon clean build distDocker; cd -
cd comment-service; ./gradlew --daemon clean build distDocker; cd -
cd catalog-service; ./gradlew --daemon clean build distDocker; cd -

