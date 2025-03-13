package ru.geekbrains.service.order.saga;

import ru.geekbrains.service.order.exceptions.SagaStepException;

/**
 * Один шаг операции, или её отката.
 * @param <T> Обобщенный тип.
 */
public interface SagaStep<T> {
    void execute(T context) throws SagaStepException;   // Выполнение операции
    void rollback(T context);                           // Откат операции
}
