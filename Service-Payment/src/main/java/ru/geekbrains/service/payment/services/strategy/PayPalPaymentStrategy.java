package ru.geekbrains.service.payment.services.strategy;

import ru.geekbrains.service.payment.exceptions.PaymentStepException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(Long userId, BigDecimal amount)  throws PaymentStepException {
        System.out.println("Using PayPalPaymentStrategy");
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}
