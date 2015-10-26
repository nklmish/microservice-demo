package com.nklmish.ts

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.turbine.EnableTurbine

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application).run(args)
    }
}

