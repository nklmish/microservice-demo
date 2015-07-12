package com.nklmish.cs.catalogs

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }
}
