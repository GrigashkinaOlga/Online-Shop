package ru.geekbrains.service.payment.services;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.payment.dto.PaymentRequest;
import ru.geekbrains.service.payment.dto.PaymentContext;
import ru.geekbrains.service.payment.services.strategy.PaymentStrategyRegistry;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PaymentService {

    PaymentStrategyRegistry paymentStrategyRegistry;

    PaymentManager paymentManager;

    /**
     * Списание средств со счета пользователя в пользу магазина.
     */
    public Boolean transfer(PaymentRequest request) {
        System.out.println("-- transfer method called");
        // создаем контекст данных
        PaymentContext context = new PaymentContext(
                request.getOrderId(),
                request.getUserId(),
                request.getAmount(),
                paymentStrategyRegistry.getStrategy(request.getPaymentMethod())
        );
        System.out.println("  -- transfer context: " + context);
        paymentManager.excecute(context);
        return true;
    }


}
