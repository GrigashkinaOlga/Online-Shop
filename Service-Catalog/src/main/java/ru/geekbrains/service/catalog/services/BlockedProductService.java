package ru.geekbrains.service.catalog.services;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.catalog.domain.BlockedProduct;
import ru.geekbrains.service.catalog.repositories.BlockedProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BlockedProductService {

    private BlockedProductRepository blockedRepository;

    public void reserve(UUID orderId, long productId, int quantity) {
        System.out.println("-- Reserving blocked product " + productId + " for order " + orderId + " with quantity " + quantity);
        BlockedProduct res = blockedRepository.save(new BlockedProduct(orderId, productId, quantity));
        System.out.println("  -- result: " + res);
    }

    public BlockedProduct unblock(UUID orderId) {
        BlockedProduct blockedProduct = blockedRepository.findById(orderId).orElse(null);
        if (blockedProduct != null) {
            blockedRepository.deleteById(orderId);
        }
        return blockedProduct;
    }
}
