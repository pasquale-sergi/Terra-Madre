package dev.pasq.terra_madre.utilities;

import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.Product;
import dev.pasq.terra_madre.entity.ShoppingCart;
import dev.pasq.terra_madre.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartUtilities {
    @Autowired
    private CartItemRepository cartItemRepository;
    public int getExistingQuantity(ShoppingCart cart, Product product) {
        Optional<CartItem> cartItemOptional = cart.getCartItems().stream()
            .filter(item -> item.getProduct().getId().equals(product.getId()))
            .findFirst();
        return cartItemOptional.map(CartItem::getQuantity).orElse(0);
    }

    public void updateExistingCartItem(ShoppingCart cart, CartItem newItem) {
        cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(newItem.getProduct()))
                .findFirst()
                .ifPresent(existingItem -> existingItem.setQuantity(newItem.getQuantity()));
    }

    public void updateQuantityWhenItemRemoved(ShoppingCart cart, Product product) {
        cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .ifPresent(existingItem -> existingItem.setQuantity(existingItem.getQuantity() - 1));
    }
}
