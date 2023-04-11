package org.ariel.app.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApp {
    public static void main(String[] args) {
        // First start db: docker compose up -d
        SpringApplication.run(CustomerApp.class, args);
    }
}
