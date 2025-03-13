package ru.geekbrains.service.order.links;

import ru.geekbrains.service.order.config.FeignClientConfig;
import ru.geekbrains.service.order.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "PAYMENT-SERVICE", configuration = FeignClientConfig.class)
public interface PaymentServiceClient {

    @PostMapping("/api/payment/transfer")
    ResponseEntity<Void> transfer(@RequestBody PaymentRequest request);

    @PostMapping("/api/payment/rollback")
    ResponseEntity<Void> rollback(@RequestBody UUID order);

}
