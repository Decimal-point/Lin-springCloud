package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com")
@EnableAutoConfiguration
public class Application implements HealthIndicator{

    @Override
    public Health health() {
        return Health.up().withDetail("status","ok").build();
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }




}
