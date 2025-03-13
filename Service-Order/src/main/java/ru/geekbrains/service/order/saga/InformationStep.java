package ru.geekbrains.service.order.saga;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.order.exceptions.SagaStepException;
import ru.geekbrains.service.order.services.InformationService;

/**
 * Шаг: Информирование пользователя о статусе сделки.
 */
@AllArgsConstructor
public class InformationStep implements SagaStep<SagaContext> {

    private InformationService informationService;

    @Override
    public void execute(SagaContext context) throws SagaStepException {
        informationService.sendMessage("Покупка совершена. Ваш заказ в ближайшее время перейдет в службу доставки.");
    }

    @Override
    public void rollback(SagaContext context) {

    }
}
