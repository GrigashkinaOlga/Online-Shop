package ru.geekbrains.service.catalog.controllers;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.catalog.dto.ProductReservationRequest;
import ru.geekbrains.service.catalog.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/catalog")
@AllArgsConstructor
public class ApiController {

    ProductService productService;

    /**
     * Резервирование товара.
     * @param request Параметры запроса (код товара, кто резервирует, сколько)
     */
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveCatalog(@RequestBody ProductReservationRequest request) {
        System.out.println("-- reserve request: " + request);
        productService.reserve(request);
        return ResponseEntity.ok("Товар зарезервирован.");
    }

    /**
     * Отмена резерва.
     */
    @PostMapping("/cancel")
    public ResponseEntity<Void> unblock(@RequestBody UUID orderId) {
        System.out.println("-- unblock request: " + orderId);
        productService.cancelReserved(orderId);
        return ResponseEntity.ok().build();
    }

    /**
     * Завершение заказа, списываем его из резерва.
     */
    @PostMapping("/approved")
    public ResponseEntity<Void> approve(@RequestBody UUID orderId) {
        productService.approvedReservation(orderId);
        return ResponseEntity.ok().build();
    }

}
