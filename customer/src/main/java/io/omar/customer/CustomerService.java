package io.omar.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .build();

    // TODO: check if email valid
    customerRepository.saveAndFlush(customer);
    // TODO: check if email not taken
    // TODO: check if fraudster
    final FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
        "http://localhost:8081/api/v1/fraud-check/{customerId}",
        FraudCheckResponse.class,
        customer.getId()
    );

    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }
    // TODO: send notification
  }
}
