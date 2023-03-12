package com.example.fraud;

import com.example.clients.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

  private final FraudCheckService fraudCheckService;

  @GetMapping("/{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
    log.info("Checking fraud for customer {}", customerId);
    final boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
