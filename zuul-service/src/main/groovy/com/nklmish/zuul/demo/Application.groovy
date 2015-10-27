package com.nklmish.zuul.demo

import com.codahale.metrics.MetricRegistry
import com.nklmish.zuul.demo.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.sleuth.Sampler
import org.springframework.cloud.sleuth.sampler.AlwaysSampler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile

@SpringBootApplication
@EnableZuulProxy
@EnableAspectJAutoProxy(proxyTargetClass = true)
class Application {

    @Bean
    public Sampler<?> defaultSampler() {
        return new AlwaysSampler()
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application).web(true).run(args)
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
