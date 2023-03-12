package com.example.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FraudCheckService {

  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

  public boolean isFraudulentCustomer(Integer customerId) {
    fraudCheckHistoryRepository.save(
        FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .build()
    );
    return false;
  }
}
