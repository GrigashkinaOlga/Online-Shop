package ru.geekbrains.service.payment.cor;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.payment.domain.PaymentEntity;
import ru.geekbrains.service.payment.domain.PaymentStatus;
import ru.geekbrains.service.payment.dto.PaymentContext;
import ru.geekbrains.service.payment.exceptions.PaymentStepException;
import ru.geekbrains.service.payment.repository.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Шаг: открытие трансфера.
 */
@AllArgsConstructor
public class CreateTransferHandler extends PaymentHandler {

    private PaymentRepository paymentRepository;


    @Override
    @Transactional
    protected void handle(PaymentContext context) {
        System.out.println("-- CreateTransferHandler()");
        // создаем в БД запись о транзакции
        try {
            PaymentEntity entity = new PaymentEntity(
                    context.getOrderId(),
                    context.getUserId(),
                    context.getAmount(),
                    context.getPaymentStrategy().getName(),
                    PaymentStatus.Pending);
            paymentRepository.save(entity);
        } catch (RuntimeException e) {
            throw new PaymentStepException("Ошибка БД: " + e.getMessage());
        }
    }
}
