package dev.pasq.terra_madre.service;

import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.Product;
import dev.pasq.terra_madre.entity.ShoppingCart;
import dev.pasq.terra_madre.repository.CartItemRepository;
import dev.pasq.terra_madre.repository.ProductRepository;
import dev.pasq.terra_madre.repository.ShoppingCartRepository;
import dev.pasq.terra_madre.utilities.CartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public CartItem addItemToCart(Integer userId, Integer productId, Integer quantity, Double price){
        ShoppingCart cart = getOrCreateCart(userId);
        System.out.println("Cart id: "+cart);

        Optional<Product> existingProduct = productRepository.findById(productId);
        if(!existingProduct.isPresent()){
            throw  new RuntimeException("Product not found");
        }

        Product product = existingProduct.get();
        System.out.println("product: "+product);

        int existingQuantity = cartUtilities.getExistingQuantity(cart, product);

        CartItem item = CartItem.builder()
                .shoppingCart(cart)
                .product(product)
                .quantity(existingQuantity + quantity) // Sum quantities
                .price(price)
                .build();

        if (existingQuantity > 0) {
            // Update existing item if it was found
            cartUtilities.updateExistingCartItem(cart, item);
        } else {
            // Add new item to the cart
            cart.getCartItems().add(item);
        }

        System.out.println("item : "+item);
        cart.getCartItems().add(item);
        System.out.println("here 1");
        cartItemRepository.save(item);
        System.out.println("here 2");
        shoppingCartRepository.save(cart);
        System.out.println("here 3");
        return item;
    }
}
