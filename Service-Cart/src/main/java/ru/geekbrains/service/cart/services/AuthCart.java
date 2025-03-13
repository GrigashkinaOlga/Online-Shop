package ru.geekbrains.service.cart.services;

import lombok.Setter;
import ru.geekbrains.service.cart.domain.CartItemEntity;
import ru.geekbrains.service.cart.dto.CartItem;
import ru.geekbrains.service.cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Корзина авторизированного пользователя.
 */
@Component
@Scope("prototype")
public class AuthCart implements Cart {

    @Autowired
    private CartRepository cartRepository;      // внедряем БД

    @Setter
    private String userId;


    @Override
    public CartItem addItem(String productId, int quantity) {
        CartItemEntity item = cartRepository.findByUserIdAndProductId(userId, productId);
        if (item == null) {
            item = new CartItemEntity(userId, productId, 0);
        }
        item.setQuantity(item.getQuantity() + quantity);
        item = cartRepository.save(item);
        return new CartItem(item.getUserId(), item.getProductId(), item.getQuantity());
    }

    @Override
    public void removeItem(String productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<CartItem> getItems() {
        List<CartItemEntity> items = cartRepository.findByUserId(userId);
        return items.stream().map(item -> new CartItem(item.getUserId(), item.getProductId(), item.getQuantity())).toList();
    }

//    @Override
//    public int getQuantity(String productId) {
//        List<CartItem> cart = getItems();
//        return cart.stream()
//                .filter(cartItem -> cartItem.getProductId().equals(productId))
//                .findFirst()
//                .orElse(new CartItem()).getQuantity();
//    }

    @Override
    public int getQuantity() {
        List<CartItem> cart = getItems();
        return cart.stream().mapToInt(CartItem::getQuantity).sum();
    }

    @Override
    public void clearCart() {
        List<CartItemEntity> items = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(items);
    }

}
