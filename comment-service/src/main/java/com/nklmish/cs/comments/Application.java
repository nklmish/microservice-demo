package com.nklmish.cs.comments;

import com.nklmish.cs.comments.metrics.HealthReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
class Application {

    @Autowired
    HealthReporter healthReporter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void enableGraphiteReporter() {
        healthReporter.enableGraphiteReporter();
    }
}
