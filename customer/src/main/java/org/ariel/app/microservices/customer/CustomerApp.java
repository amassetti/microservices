package org.ariel.app.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication (
        scanBasePackages = {
                "org.ariel.app.microservices.customer",
                "org.ariel.app.microservices.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "org.ariel.app.microservices.clients"
)
@PropertySources({
    @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class CustomerApp {
    public static void main(String[] args) {
        // First start db: docker compose up -d
        SpringApplication.run(CustomerApp.class, args);
    }
}
