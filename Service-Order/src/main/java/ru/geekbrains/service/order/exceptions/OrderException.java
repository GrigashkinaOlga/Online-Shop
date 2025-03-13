package ru.geekbrains.service.order.exceptions;

public abstract class OrderException extends RuntimeException{

    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }
}
