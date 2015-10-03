package com.nklmish.ps.prices

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
