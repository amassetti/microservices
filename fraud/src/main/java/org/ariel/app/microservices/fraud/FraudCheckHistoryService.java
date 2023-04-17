package org.ariel.app.microservices.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        log.info("Checking customer with id {} is fraudster or not", customerId);
        boolean isFraudster = false;
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(isFraudster)
                .createdAt(LocalDateTime.now())
                .build());
        return isFraudster;
    }

    public List<FraudCheckHistory> getAllChecks() {
        return fraudCheckHistoryRepository.findAll();
    }
}
