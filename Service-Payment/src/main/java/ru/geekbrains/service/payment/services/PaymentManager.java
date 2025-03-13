package ru.geekbrains.service.payment.services;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.payment.cor.PaymentHandler;
import ru.geekbrains.service.payment.cor.RollbackTransfer;
import ru.geekbrains.service.payment.dto.PaymentContext;
import ru.geekbrains.service.payment.exceptions.PaymentStepException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PaymentManager {

    private PaymentHandler paymentHandler;
    private RollbackTransfer rollbackTransfer;

    public void excecute(PaymentContext context) throws PaymentStepException {
        try {
            paymentHandler.run(context);

        } catch (PaymentStepException e) {
            System.out.println("-- Error: " + e.getMessage());
            rollbackTransfer.rollback(context);
            throw e;
        }
    }

}
