package com.nklmish.cs.catalogs

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
class Application {

    @Value('${app.rabbitmq.host}')
    String host

    @Bean
    public ConnectionFactory createRabbitMqConnectionFactory() {
       return new CachingConnectionFactory(host)
    }

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
