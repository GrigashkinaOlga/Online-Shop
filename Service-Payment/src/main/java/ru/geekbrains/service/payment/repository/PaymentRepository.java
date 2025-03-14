package ru.geekbrains.service.payment.repository;

import ru.geekbrains.service.payment.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByOrderId(UUID orderId);
    Optional<PaymentEntity> findByUserId(Long userId);
    void deleteByOrderId(UUID orderId);
}
