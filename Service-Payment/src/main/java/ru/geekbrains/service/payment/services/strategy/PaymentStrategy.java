package ru.geekbrains.service.payment.services.strategy;

import ru.geekbrains.service.payment.exceptions.PaymentStepException;

import java.math.BigDecimal;

/**
 * Списание средств пользователя.
 */
public interface PaymentStrategy {
    void pay(Long userId, BigDecimal amount)  throws PaymentStepException;
    boolean isPresent();
    String getName();
}

