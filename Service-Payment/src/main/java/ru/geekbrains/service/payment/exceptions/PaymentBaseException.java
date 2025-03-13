package ru.geekbrains.service.payment.exceptions;

public class PaymentBaseException extends RuntimeException {

    public PaymentBaseException() {
        super();
    }

    public PaymentBaseException(String message) {
        super(message);
    }

}
