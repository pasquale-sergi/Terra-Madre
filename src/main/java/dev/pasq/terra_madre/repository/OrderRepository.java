package dev.pasq.terra_madre.repository;

import dev.pasq.terra_madre.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
