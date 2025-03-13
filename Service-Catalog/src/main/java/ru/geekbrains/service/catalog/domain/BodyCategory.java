package ru.geekbrains.service.catalog.domain;

import org.springframework.stereotype.Component;

/**
 * Категория "Уход за телом"
 */
@Component
public class BodyCategory extends ProductCategory {

    @Override
    public String getName() {
        return "Body";
    }
}
