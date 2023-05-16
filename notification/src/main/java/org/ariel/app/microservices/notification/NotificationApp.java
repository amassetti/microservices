package org.ariel.app.microservices.notification;

import org.ariel.app.microservices.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "org.ariel.app.microservices.notification",
                "org.ariel.app.microservices.rabbitmq",
                "org.ariel.app.microservices.amqp"
        }
)
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApp {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApp.class, args);
    }

    /*
    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            NotificationConfig notificationConfig
    ) {
        return args -> {
            producer.publish(
                    new Person("Pepe Luis", 40),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey()
            );
        };
    }

    record Person(String name, int age) {}
*/

}
