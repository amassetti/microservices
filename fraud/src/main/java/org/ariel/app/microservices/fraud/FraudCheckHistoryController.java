package org.ariel.app.microservices.fraud;

import lombok.AllArgsConstructor;
import org.ariel.app.microservices.clients.fraud.FraudCheckResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

    @GetMapping
    public List<FraudCheckHistory> getAllChecks(){
        return fraudCheckHistoryService.getAllChecks();
    }
}
