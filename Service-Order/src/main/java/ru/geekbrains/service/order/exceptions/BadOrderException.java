package ru.geekbrains.service.order.exceptions;

public class BadOrderException  extends OrderException {

    @Override
    public String getMessage() {
        return "Неверный UUID заказа";
    }
}
