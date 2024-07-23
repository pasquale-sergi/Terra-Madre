package dev.pasq.terra_madre.service;

import dev.pasq.terra_madre.entity.CartItem;
import dev.pasq.terra_madre.entity.Order;
import dev.pasq.terra_madre.entity.ShoppingCart;
import dev.pasq.terra_madre.repository.OrderRepository;
import dev.pasq.terra_madre.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public Order createOrder(Integer cartId){
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
        if(cart.isEmpty()) throw new RuntimeException("Cart not found");

        Order order = new Order();
        order.setCart(cart.get());
        order.setTotalAmount(cart.get().calculateTotal());
        order.setStatus("PENDING");
        orderRepository.save(order);
        return order;
    }
}
