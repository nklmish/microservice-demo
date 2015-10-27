package com.nklmish.es

import com.codahale.metrics.MetricRegistry
import com.nklmish.es.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @Bean
    @Profile(["docker", "metric"])
    public HealthReporter createGraphiteReporter(@Value('${app.graphite.host}') String graphiteHost,
                                                 @Value('${app.graphite.port}') int graphitePort) {
        MetricRegistry metricRegistry = new MetricRegistry()
        HealthReporter healthReporter = new HealthReporter(metricRegistry, graphiteHost, graphitePort)
        healthReporter.enableGraphiteReporter()
        return healthReporter
    }

}

