package ru.geekbrains.service.order.saga;

import feign.FeignException;
import lombok.AllArgsConstructor;
import ru.geekbrains.service.order.dto.ProductReservationRequest;
import ru.geekbrains.service.order.exceptions.SagaStepException;
import ru.geekbrains.service.order.links.CatalogServiceClient;

/**
 * Шаг: резервирование товара на складе.
 */
@AllArgsConstructor
public class ProductReservationStep implements SagaStep<SagaContext> {

    CatalogServiceClient catalogServiceClient;

    @Override
    public void execute(SagaContext context) throws SagaStepException {
        try {
            context.getItems().forEach(product -> {
                ProductReservationRequest request = ProductReservationRequest.builder()
                        .orderId(context.getOrderId())
                        .userId(context.getUserId())
                        .productId(product.getProductId())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .build();
                catalogServiceClient.reserve(request);
            });

        } catch (FeignException e) {
            throw new SagaStepException(e.contentUTF8());
        }
    }

    @Override
    public void rollback(SagaContext context) {
        System.out.println("-- ProductReservationStep().rollback()");
        try {
            catalogServiceClient.unblock(context.getOrderId());
        } catch (FeignException ignored) {}
    }


}
