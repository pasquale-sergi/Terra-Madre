package dev.pasq.terra_madre.service;

import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.Product;
import dev.pasq.terra_madre.entity.ShoppingCart;
import dev.pasq.terra_madre.repository.CartItemRepository;
import dev.pasq.terra_madre.repository.ProductRepository;
import dev.pasq.terra_madre.repository.ShoppingCartRepository;
import dev.pasq.terra_madre.utilities.CartUtilities;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartUtilities cartUtilities;

    // Create or retrieve a shopping cart for a user
    public ShoppingCart getOrCreateCart(Integer userId) {
        Optional<ShoppingCart> existingCart = shoppingCartRepository.findByUserId(userId);
        if (existingCart.isPresent()) {
            return existingCart.get();
        } else {
            ShoppingCart cart = new ShoppingCart();
            cart.setUserId(userId);
            return shoppingCartRepository.save(cart);
        }
    }
    @Transactional
    public CartItem addItemToCart(Integer userId, Integer productId, Integer quantity) {
        ShoppingCart cart = getOrCreateCart(userId);

        Optional<Product> existingProduct = productRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Product not found");
        }

        Product product = existingProduct.get();

        int existingQuantity = cartUtilities.getExistingQuantity(cart, product);
        CartItem item = CartItem.builder()
                .shoppingCart(cart)
                .product(product)
                .quantity(existingQuantity + quantity)
                .price(product.getPrice())
                .build();

        if(existingQuantity>0){
            cartUtilities.updateExistingCartItem(cart, item);
        }else{
            cart.addItemToList(item);
            cartItemRepository.save(item);

        }

        shoppingCartRepository.save(cart);
        return item;
    }


}
