package dev.pasq.terra_madre.controller;

import dev.pasq.terra_madre.dto.CartItemAddingDto;
import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.ShoppingCart;
import dev.pasq.terra_madre.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/new")
    public ResponseEntity<ShoppingCart> createCart(@RequestParam Integer userId){
        ShoppingCart cart = shoppingCartService.getOrCreateCart(userId);
        return ResponseEntity.ok(cart);

    }
    @PostMapping("/{cart_id}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable("cart_id") Integer cartId,
                                                  @RequestBody CartItemAddingDto request){
        CartItem item = shoppingCartService.addItemToCart(cartId, request.getProductId(), request.getQuantity(), request.getPrice());
        return ResponseEntity.ok(item);
    }
}
