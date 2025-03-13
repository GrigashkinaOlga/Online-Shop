package ru.geekbrains.service.order.saga;

import feign.FeignException;
import lombok.AllArgsConstructor;
import ru.geekbrains.service.order.dto.PaymentRequest;
import ru.geekbrains.service.order.exceptions.SagaStepException;
import ru.geekbrains.service.order.links.PaymentServiceClient;

/**
 * Шаг: Оплата заказа.
 */
@AllArgsConstructor
public class PaymentTransferStep implements SagaStep<SagaContext> {

    private final PaymentServiceClient paymentServiceClient;


    @Override
    public void execute(SagaContext context) throws SagaStepException {
        try {
            PaymentRequest paymentRequest = new PaymentRequest(
                    context.getOrderId(),
                    context.getUserId(),
                    context.getTotalAmount(),
                    context.getPaymentMethod(),
                    "BUY");
            paymentServiceClient.transfer(paymentRequest);
        } catch (FeignException e) {
            throw new SagaStepException(e.contentUTF8());
        }
    }

    @Override
    public void rollback(SagaContext context) {
        try {
            paymentServiceClient.rollback(context.getOrderId());
        } catch (FeignException ignored) {}
    }

}
