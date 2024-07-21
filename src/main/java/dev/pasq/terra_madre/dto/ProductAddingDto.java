package dev.pasq.terra_madre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddingDto {

    private String productName;
    private String description;
    private Double price;
    private String category;
    private Integer quantity;
}
