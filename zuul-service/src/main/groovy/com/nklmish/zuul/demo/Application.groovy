package com.nklmish.zuul.demo

import com.nklmish.zuul.demo.metrics.HealthReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.stereotype.Controller

import javax.annotation.PostConstruct

@SpringBootApplication
@EnableZuulProxy
@Controller
class Application {

    @Autowired
    HealthReporter healthReporter

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application).web(true).run(args)
    }

    @PostConstruct
    public void enableGraphiteReporter() {
        healthReporter.enableGraphiteReporter()
    }
}
