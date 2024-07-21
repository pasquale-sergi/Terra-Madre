package dev.pasq.terra_madre.repository;

import dev.pasq.terra_madre.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findById(Integer cartId);

    Optional<ShoppingCart> findByUserId(Integer userId);
}
