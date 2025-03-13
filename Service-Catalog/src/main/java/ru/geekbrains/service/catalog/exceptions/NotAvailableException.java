package ru.geekbrains.service.catalog.exceptions;

/**
 * Исключение на случай отсутствия товара в наличии.
 */
public class NotAvailableException extends CatalogException {

    @Override
    public String getMessage() {
        return "Товара нет в наличии.";
    }
}
