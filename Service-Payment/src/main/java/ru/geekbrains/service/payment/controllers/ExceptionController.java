package ru.geekbrains.service.payment.controllers;

import ru.geekbrains.service.payment.exceptions.PaymentBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PaymentBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String catalogException(PaymentBaseException e) {
        return "Ошибка: " + LocalDateTime.now() + ": " + e.getMessage();
    }

}
