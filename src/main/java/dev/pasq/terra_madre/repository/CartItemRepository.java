package dev.pasq.terra_madre.repository;

import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
