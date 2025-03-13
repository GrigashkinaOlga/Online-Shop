package ru.geekbrains.service.order.controller;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.order.domain.Order;
import ru.geekbrains.service.order.dto.OrderRequest;
import ru.geekbrains.service.order.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    /**
     * Возвращает список всех еще необработанных заказов.
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    /**
     * Запрос на создание заказа.
     * @param order Параметры запроса.
     * @return Http-статус.
     */
    @PostMapping
    public ResponseEntity<UUID> createOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

}
