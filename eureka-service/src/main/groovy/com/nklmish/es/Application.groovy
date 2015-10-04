package com.nklmish.es

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

}

