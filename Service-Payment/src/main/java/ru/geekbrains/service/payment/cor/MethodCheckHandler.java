package ru.geekbrains.service.payment.cor;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.payment.dto.PaymentContext;
import ru.geekbrains.service.payment.exceptions.PaymentStepException;
import ru.geekbrains.service.payment.services.strategy.PaymentStrategy;

/**
 * Шаг: проверка доступности метода оплаты.
 */
@AllArgsConstructor
public class MethodCheckHandler extends PaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        System.out.println("-- MethodCheckHandler()");
        PaymentStrategy paymentStrategy = context.getPaymentStrategy();
        if (paymentStrategy == null || !paymentStrategy.isPresent()) {
            throw new PaymentStepException("Выбранный способ оплаты недоступен");
        }
    }
}
