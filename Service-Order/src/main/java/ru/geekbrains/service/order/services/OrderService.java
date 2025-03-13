package ru.geekbrains.service.order.services;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.order.domain.Order;
import ru.geekbrains.service.order.dto.CartItem;
import ru.geekbrains.service.order.dto.OrderRequest;
import ru.geekbrains.service.order.links.CatalogServiceClient;
import ru.geekbrains.service.order.links.PaymentServiceClient;
import ru.geekbrains.service.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import ru.geekbrains.service.order.saga.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private CatalogServiceClient catalogServiceClient;
    private PaymentServiceClient paymentServiceClient;

    private InformationService informationService;

    /**
     * Создает новый заказ и проводит его через все этапы.
     */
    public UUID createOrder(OrderRequest request) {
        // Создаём контекст данных заказа.
        SagaContext context = new SagaContext();
        context.setUserId(request.getUserId());
        context.setPaymentMethod(request.getPaymentMethod());
        BigDecimal total = request.getCartItems().stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        context.setTotalAmount(total);
        List<SagaContextItems> items = request.getCartItems().stream().map(e -> new SagaContextItems(e.getProductId(), e.getProductName(), e.getQuantity(), e.getPrice())).toList();
        context.setItems(items);

        // Задаем последовательность действий.
        SagaOrchestrator<SagaContext> orchestrator = new SagaOrchestrator<>();
        orchestrator.addStep(new CreateOrderStep(orderRepository, informationService));                 // открываем заказ
        orchestrator.addStep(new ProductReservationStep(catalogServiceClient));     // резервируем товар
        orchestrator.addStep(new PaymentTransferStep(paymentServiceClient));        // Списываем средства в пользу магазина
        orchestrator.addStep(new ApprovedStep(orderRepository, catalogServiceClient)); // завершение сделки
        orchestrator.addStep(new InformationStep(informationService));                                // оповещаем пользователя

        // Запускаем выполнение и возвращаем результат.
        orchestrator.execute(context);
        return context.getOrderId();
    }


    /**
     * Возвращает список всех находящихся в обработке заказов.
     */
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
