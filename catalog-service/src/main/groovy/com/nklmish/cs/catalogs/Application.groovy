package com.nklmish.cs.catalogs

import com.codahale.metrics.MetricRegistry
import com.nklmish.cs.catalogs.metrics.HealthReporter
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.sleuth.Sampler
import org.springframework.cloud.sleuth.sampler.AlwaysSampler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
class Application {

    @Bean
    public ConnectionFactory createRabbitMqConnectionFactory(@Value('${app.rabbitmq.host}') String host) {
       return new CachingConnectionFactory(host)
    }

    @Bean
    public Sampler<?> defaultSampler() {
        return new AlwaysSampler()
    }

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
