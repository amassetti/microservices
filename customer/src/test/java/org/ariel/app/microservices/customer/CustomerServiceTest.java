package org.ariel.app.microservices.customer;

import org.ariel.app.microservices.amqp.RabbitMQMessageProducer;
import org.ariel.app.microservices.clients.albums.AlbumsClient;
import org.ariel.app.microservices.clients.fraud.FraudCheckResponse;
import org.ariel.app.microservices.clients.fraud.FraudClient;
import org.ariel.app.microservices.clients.notification.NotificationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private FraudClient fraudClient;
    @Mock
    private AlbumsClient albumsClient;
    @Mock
    private RabbitMQMessageProducer notificationProducer;

    private NotificationConfig notificationConfig;

    private CustomerService underTest;


    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository, fraudClient, albumsClient, notificationProducer, notificationConfig);
    }

    @Test
    void registerCustomer() {

        CustomerRegistrationReq customerRegistrationReq = new CustomerRegistrationReq("Pepe", "Lui", "plui@gmail.com");
        Customer customer = Customer.builder()
                .firstName(customerRegistrationReq.firstName())
                .lastName(customerRegistrationReq.lastName())
                .email(customerRegistrationReq.email())
                .build();

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to microservices magic!!!", customer.getFirstName())
        );

        underTest.registerCustomer(customerRegistrationReq);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        Mockito.verify(customerRepository).saveAndFlush(customerArgumentCaptor.capture());
        Customer customerCaptured = customerArgumentCaptor.getValue();
        assertThat(customerCaptured).isEqualTo(customer);

        Mockito.verify(fraudClient).isFraudster(customer.getId());
        //Mockito.verify(notificationClient).sendNotification(notificationRequest);
    }

    @Test
    void willThrowExWhenIsFraudster() {

        CustomerRegistrationReq customerRegistrationReq = new CustomerRegistrationReq("Pepe", "Lui", "plui@gmail.com");

        given(fraudClient.isFraudster(Mockito.any())).willReturn(new FraudCheckResponse(true));

        assertThatThrownBy(() -> underTest.registerCustomer(customerRegistrationReq) )
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Customer is fraudster");

        //Mockito.verify(notificationClient, Mockito.never()).sendNotification(Mockito.any());

    }


    @Test
    void getAllCustomers() {
        // when
        underTest.getAllCustomers();

        // then
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById() {
        underTest.getCustomerById(Mockito.anyInt());
        Mockito.verify(customerRepository).findById(Mockito.anyInt());
    }

    @Test
    void getAlbums() {
        underTest.getAlbums();
        Mockito.verify(albumsClient).getAlbums();
    }
}