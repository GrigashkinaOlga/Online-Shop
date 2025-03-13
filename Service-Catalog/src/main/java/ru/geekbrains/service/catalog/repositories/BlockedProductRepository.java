package ru.geekbrains.service.catalog.repositories;

import ru.geekbrains.service.catalog.domain.BlockedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Резервирование товаров.
 */
@Repository
public interface BlockedProductRepository extends JpaRepository<BlockedProduct, UUID> {

}
