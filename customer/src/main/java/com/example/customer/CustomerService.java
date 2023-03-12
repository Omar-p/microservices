package com.example.customer;

import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notification.NotificationClient;
import com.example.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;

  private final NotificationClient notificationClient;


  public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
    var customer = Customer.builder()
        .firstName(customerRegistrationRequest.firstName())
        .lastName(customerRegistrationRequest.lastName())
        .email(customerRegistrationRequest.email())
        .build();

    // TODO: check if email is valid
    // TODO: check if email is already registered
    customer = customerRepository.saveAndFlush(customer);
    // TODO: check if fraudster
    final FraudCheckResponse fraudCheckResponse = fraudClient
        .fraudCheckResponse(customer.getId());

    if (fraudCheckResponse.isFraudster()) {
      log.warn("Fraudster detected: {}", customer);
      throw new RuntimeException("Fraudster detected: " + customer);
    }
    // TODO: send async notification
    notificationClient.sendNotification(
        new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, welcome to our services!", customer.getFirstName())
        )
    );
  }
}
