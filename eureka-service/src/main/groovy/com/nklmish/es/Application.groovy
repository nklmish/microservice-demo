package com.nklmish.es

import com.nklmish.es.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

import javax.annotation.PostConstruct

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @Bean
    @Profile(["docker", "metric"])
    public HealthReporter createGraphiteReporter() {
        HealthReporter healthReporter = new HealthReporter()
        healthReporter.enableGraphiteReporter()
        return healthReporter
    }

}

