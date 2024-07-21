package dev.pasq.terra_madre.utilities;

import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.Product;
import dev.pasq.terra_madre.entity.ShoppingCart;

public class CartUtilities {
    public int getExistingQuantity(ShoppingCart cart, Product product) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public void updateExistingCartItem(ShoppingCart cart, CartItem newItem) {
        cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(newItem.getProduct()))
                .findFirst()
                .ifPresent(existingItem -> existingItem.setQuantity(newItem.getQuantity()));
    }
}
