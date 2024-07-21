package dev.pasq.terra_madre.service;

import dev.pasq.terra_madre.entity.Product;
import dev.pasq.terra_madre.exceptionHandler.ProductNotFoundException;
import dev.pasq.terra_madre.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(String name){
        try {
            return productRepository.findByProductName(name);
        }catch (RuntimeException e) {
            log.error("Failed to fetch product by name: {}", name, e);
            throw new ProductNotFoundException("Product with name " + name + " not found", e);
        }
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(String productName, String description, Double price, Integer quantity, String category) {
        try {
            Product product = Product.builder()
                    .productName(productName)
                    .description(description)
                    .price(price)
                    .quantity(quantity)
                    .category(category)
                    .build();
            return productRepository.save(product);
        }catch (RuntimeException e){
            log.error("Failed to add the new product");
            throw new RuntimeException();
        }
    }
}
