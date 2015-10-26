package com.nklmish.zuul.demo

import com.nklmish.zuul.demo.metrics.HealthReporter
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
    public HealthReporter enableGraphiteReporter() {
        HealthReporter healthReporter = new HealthReporter()
        healthReporter.enableGraphiteReporter()
        return healthReporter
    }
}
