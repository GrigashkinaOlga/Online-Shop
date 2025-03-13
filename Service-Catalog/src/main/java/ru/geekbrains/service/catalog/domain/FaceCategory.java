package ru.geekbrains.service.catalog.domain;

import org.springframework.stereotype.Component;

/**
 * Категория "Уход за лицом"
 */
@Component
public class FaceCategory extends ProductCategory {

    @Override
    public String getName() {
        return "Face";
    }
}
