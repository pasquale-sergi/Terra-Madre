package dev.pasq.terra_madre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemAddingDto {
    private Integer productId;
    private Integer quantity;
    private Double price;
}
