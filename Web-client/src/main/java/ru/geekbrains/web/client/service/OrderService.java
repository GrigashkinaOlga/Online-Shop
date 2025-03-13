package ru.geekbrains.web.client.service;

import feign.FeignException;
import lombok.AllArgsConstructor;
import ru.geekbrains.web.client.dto.OrderRequest;
import ru.geekbrains.web.client.links.CartServiceClient;
import ru.geekbrains.web.client.links.OrderServiceClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    OrderServiceClient orderServiceClient;
    CartServiceClient cartServiceClient;

    public void createOrder(OrderRequest orderRequest) throws FeignException {
        orderServiceClient.createOrder(orderRequest);
        cartServiceClient.clearCart();
    }
}
