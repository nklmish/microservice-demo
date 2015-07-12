#!/usr/bin/env bash
 
cd eureka-service; ./gradlew clean build bootRun & cd -
cd zuul-service; ./gradlew clean build bootRun & cd -
cd product-service; ./gradlew clean build bootRun & cd -
cd price-service; ./gradlew clean build bootRun & cd - 
cd comment-service; ./gradlew clean build booRun & cd - 
cd catalog-service; ./gradlew clean build bootRun && fg

