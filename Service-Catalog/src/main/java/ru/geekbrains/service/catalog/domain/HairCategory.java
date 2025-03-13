package ru.geekbrains.service.catalog.domain;

import org.springframework.stereotype.Component;

/**
 * Категория "Уход за волосами"
 */
@Component
public class HairCategory extends ProductCategory {

    @Override
    public String getName() {
        return "Hair";
    }
}
