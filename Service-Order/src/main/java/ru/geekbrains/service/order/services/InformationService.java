package ru.geekbrains.service.order.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * Рассылка сообщений всем пользователям
 */
@Service
@AllArgsConstructor
public class InformationService {

    private AmqpTemplate amqpTemplate;


    public void sendMessage(String message) {
        amqpTemplate.convertAndSend("springCloudBusPK8000", "someRoutingKey", message);
    }
}
