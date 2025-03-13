package ru.geekbrains.service.catalog.domain;

import org.springframework.stereotype.Component;

@Component
public class OtherCategory extends ProductCategory {

    @Override
    public String getName() {
        return "Other";
    }
}
