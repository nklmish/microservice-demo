package com.nklmish.ps.products

import com.codahale.metrics.MetricRegistry
import com.nklmish.ps.products.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.sleuth.Sampler
import org.springframework.cloud.sleuth.sampler.AlwaysSampler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
class Application {

    @Bean
    public Sampler<?> defaultSampler() {
        return new AlwaysSampler();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @Bean
    @Profile(["docker", "metric"])
    public HealthReporter createGraphiteReporter(@Value('${app.graphite.host}') String host,
                                                 @Value('${app.graphite.port}') int port) {
        MetricRegistry metricRegistry = new MetricRegistry()
        HealthReporter healthReporter = new HealthReporter(metricRegistry, host, port)
        healthReporter.enableGraphiteReporter()
        return healthReporter
    }
}
