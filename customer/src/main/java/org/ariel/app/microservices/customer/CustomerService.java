package org.ariel.app.microservices.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationReq customerRegistrationReq) {
        log.info("CustomerService - registering customer {}", customerRegistrationReq);
        Customer customer = Customer.builder()
                .firstName(customerRegistrationReq.firstName())
                .lastName(customerRegistrationReq.lastName())
                .email(customerRegistrationReq.email())
                .build();

        customerRepository.save(customer);
    }
}
