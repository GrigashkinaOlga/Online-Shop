package ru.geekbrains.service.catalog.repositories;

import ru.geekbrains.service.catalog.domain.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    ProductCategoryEntity findByName(String name);
}
