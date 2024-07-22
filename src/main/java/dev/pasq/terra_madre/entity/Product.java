package dev.pasq.terra_madre.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@NoArgsConstructor
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

    public Integer getId() {
        return id;
    }

    public Product(Integer id, String productName, String description, Integer quantity, Double price, String category) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
