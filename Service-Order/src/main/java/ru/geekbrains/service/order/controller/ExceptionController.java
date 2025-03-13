package ru.geekbrains.service.order.controller;

import ru.geekbrains.service.order.exceptions.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String catalogException(OrderException e) {
        if (e.getMessage().startsWith("Ошибка: ")) {
            return e.getMessage();
        }
        return "Ошибка: " + e.getMessage();
    }

}
