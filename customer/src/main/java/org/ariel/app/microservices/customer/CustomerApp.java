package org.ariel.app.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApp {
    public static void main(String[] args) {
        // First start db: docker compose up -d
        SpringApplication.run(CustomerApp.class, args);
    }
}
