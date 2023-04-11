package org.ariel.app.microservices.customer;

public record CustomerRegistrationReq(String firstName, String lastName, String email) {
}
