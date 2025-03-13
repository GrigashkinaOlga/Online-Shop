package ru.geekbrains.service.order.saga;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.order.domain.Order;
import ru.geekbrains.service.order.domain.OrderItem;
import ru.geekbrains.service.order.domain.OrderStatus;
import ru.geekbrains.service.order.exceptions.SagaStepException;
import ru.geekbrains.service.order.repository.OrderRepository;
import ru.geekbrains.service.order.services.InformationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Шаг: создание заказа.
 */
@AllArgsConstructor
public class CreateOrderStep implements SagaStep<SagaContext> {

    private final OrderRepository orderRepository;
    private final InformationService informationService;

    @Override
    public void execute(SagaContext context) throws SagaStepException {
        Order order = Order.builder()
                .userId(context.getUserId())
                .totalPrice(context.getTotalAmount())
                .paymentMethod(context.getPaymentMethod())
                .status(OrderStatus.Pending)
                .build();

        Order finalOrder = order;
        List<OrderItem> orderItems = context.getItems().stream()
                .map(e -> OrderItem.builder()
                        .order(finalOrder) // Передаем ссылку на созданный заказ
                        .productId(e.getProductId())
                        .productName(e.getProductName())
                        .quantity(e.getQuantity())
                        .price(e.getPrice())
                        .build()
                ).toList();

        order.setItems(new ArrayList<>(orderItems));    // иначе получим неизменяемый список items'ов. Спасибо lombok, за прекрасные пару часов отладки! А надо то было, всего лишь документацию почитать на @Builder :)
        order = orderRepository.save(order);
        context.setOrderId(order.getOrderId());
        if (order.getOrderId() == null) {
            throw new SagaStepException("Ошибка создания заказа. Проблемы с БД");      // ошибка создания заказа, возможно БД недоступна
        }
    }

    @Override
    public void rollback(SagaContext context) {
        if (context.getOrderId() != null) {
            Order order = orderRepository.findById(context.getOrderId()).orElse(null);
            if (order != null) {
                // меняем статус заказа на "отменен"
                order.setStatus(OrderStatus.Cancelled);
                order = orderRepository.save(order);
            }
            context.setOrderId(null);
        }
        informationService.sendMessage("Заказ отменен. Попробуйте попозже.");
    }
}
