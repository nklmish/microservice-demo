package com.nklmish.cs.catalogs

import com.nklmish.cs.catalogs.metrics.HealthReporter
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

import javax.annotation.PostConstruct

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
class Application {

    @Value('${app.rabbitmq.host}')
    String host

    @Autowired
    HealthReporter healthReporter

    @Bean
    public ConnectionFactory createRabbitMqConnectionFactory() {
       return new CachingConnectionFactory(host)
    }

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @PostConstruct
    public void enableGraphiteReporter() {
        healthReporter.enableGraphiteReporter()
    }

}
