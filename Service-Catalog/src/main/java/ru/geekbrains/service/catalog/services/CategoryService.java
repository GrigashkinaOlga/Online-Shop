package ru.geekbrains.service.catalog.services;

import lombok.AllArgsConstructor;
import ru.geekbrains.service.catalog.domain.CategoryFactory;
import ru.geekbrains.service.catalog.domain.ProductCategory;
import ru.geekbrains.service.catalog.domain.ProductCategoryEntity;
import ru.geekbrains.service.catalog.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryFactory categoryFactory;


    /**
     * Добавляет в БД новую категорию.
     */
    public void createCategory(String name) {
        if (categoryRepository.findByName(name) == null) {
            categoryRepository.save(new ProductCategoryEntity(name));
        }
    }

    /**
     * Возвращает список классов всех доступных категорий.
     */
    public List<ProductCategory> getAllCategories() {
        List<ProductCategoryEntity> entities = categoryRepository.findAll();
        return entities.stream().map(e -> categoryFactory.getCategory(e.getName())).toList();
    }

    /**
     * Возвращает объект категории по её имени.
     */
    public ProductCategory getCategory(String name) {
        return categoryFactory.getCategory(name);
    }

}