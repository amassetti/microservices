package org.ariel.app.microservices.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationReq customerReq) {
        log.info("new customer registration {}", customerReq);
        customerService.registerCustomer(customerReq);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping ("{customerId}")
    public Customer getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }
}
