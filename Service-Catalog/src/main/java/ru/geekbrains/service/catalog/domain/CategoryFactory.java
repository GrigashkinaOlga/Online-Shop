package ru.geekbrains.service.catalog.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Фабрика категорий.
 */
@Component
@AllArgsConstructor
public class CategoryFactory {

    private final FaceCategory faceCategory;
    private final BodyCategory bodyCategory;
    private final HairCategory hairCategory;
    private final OtherCategory otherCategory;

    public ProductCategory getCategory(String type) {
        return switch (type.toLowerCase()) {
            case "face" -> faceCategory;
            case "body" -> bodyCategory;
            case "hair" -> hairCategory;
            case "other" -> otherCategory;
            default -> throw new IllegalArgumentException("Неизвестная категория: " + type);
        };
    }
}