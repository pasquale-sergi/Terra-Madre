package dev.pasq.terra_madre.repository;

import dev.pasq.terra_madre.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
