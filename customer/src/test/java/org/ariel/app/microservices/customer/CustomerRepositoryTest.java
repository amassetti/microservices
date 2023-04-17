package org.ariel.app.microservices.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void clean() {
        underTest.deleteAll();
    }

    @Test
    void customerShouldExistAfterSave() {

        // Given
        String email = "a@gmail.com";

        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName("Pepe");
        customer.setLastName("Lui");

        underTest.save(customer);

        // when
        Customer existingCUstomer = underTest.findByEmail(email);

        // then
        assertThat(existingCUstomer).isNotNull();

    }

    @Test
    void customerShouldNotExistIfNotSaved() {

        // Given
        String email = "a@gmail.com";

             // when
        Customer existingCUstomer = underTest.findByEmail(email);

        // then
        assertThat(existingCUstomer).isNull();

    }

}
