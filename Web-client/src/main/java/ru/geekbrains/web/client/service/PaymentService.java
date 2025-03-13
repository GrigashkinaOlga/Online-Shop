package ru.geekbrains.web.client.service;

import feign.FeignException;
import lombok.AllArgsConstructor;
import ru.geekbrains.web.client.dto.StrategyInfo;
import ru.geekbrains.web.client.links.PaymentServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentServiceClient paymentServiceClient;

    public List<StrategyInfo> getPaymentStrategies() {
        try {
            return paymentServiceClient.getAvailableStrategies().getBody();
        } catch (FeignException e) {
            System.out.println("PaymentService: " + e.getMessage());
            return List.of(new StrategyInfo("", "Оплата временно недоступна"));
        }
    }

}
