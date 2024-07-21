package dev.pasq.terra_madre.repository;

import dev.pasq.terra_madre.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductName(String productName);
}
