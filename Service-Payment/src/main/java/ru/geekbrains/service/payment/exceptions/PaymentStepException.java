package ru.geekbrains.service.payment.exceptions;

public class PaymentStepException extends PaymentBaseException {

    public PaymentStepException() {
        super();
    }

    public PaymentStepException(String message) {
        super(message);
    }
}
