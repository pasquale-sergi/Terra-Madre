package dev.pasq.terra_madre.controller;

import dev.pasq.terra_madre.entity.Order;
import dev.pasq.terra_madre.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestParam Integer cartId){
        Order order = orderService.createOrder(cartId);
        return ResponseEntity.ok(order);
    }
}
