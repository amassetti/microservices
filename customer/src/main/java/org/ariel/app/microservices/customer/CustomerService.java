package org.ariel.app.microservices.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ariel.app.microservices.clients.FraudCheckResponse;
import org.ariel.app.microservices.clients.FraudClient;
import org.ariel.app.microservices.clients.albums.Albums;
import org.ariel.app.microservices.clients.albums.AlbumsClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final AlbumsClient albumsClient;

    public void registerCustomer(CustomerRegistrationReq customerRegistrationReq) {
        log.info("CustomerService - registering customer {}", customerRegistrationReq);
        Customer customer = Customer.builder()
                .firstName(customerRegistrationReq.firstName())
                .lastName(customerRegistrationReq.lastName())
                .email(customerRegistrationReq.email())
                .build();

        // todo: check email valid and not taken

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if (response.isFraudster()) {
            throw new IllegalStateException("Customer is fraudster");
        }


        // todo: send notification
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElse(new Customer());
    }

    public List<Albums> getAlbums() {
        return albumsClient.getAlbums();
    }
}
