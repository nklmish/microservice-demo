package com.nklmish.es

import com.nklmish.es.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

import javax.annotation.PostConstruct

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
class Application {

    @Autowired
    HealthReporter healthReporter

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @PostConstruct
    public void enableGraphiteReporter() {
        healthReporter.enableGraphiteReporter()
    }

}

