package com.nklmish.ps.prices

import com.nklmish.ps.prices.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.sleuth.Sampler
import org.springframework.cloud.sleuth.sampler.AlwaysSampler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile

import javax.annotation.PostConstruct

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
    public HealthReporter createGraphiteReporter() {
        HealthReporter healthReporter = new HealthReporter()
        healthReporter.enableGraphiteReporter()
        return healthReporter
    }
}
