package dev.pasq.terra_madre.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;
    @Column(nullable = false, name = "product_name")

    private String productName;
    private String description;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String category;

}
