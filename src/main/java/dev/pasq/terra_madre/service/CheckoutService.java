package dev.pasq.terra_madre.service;

import dev.pasq.terra_madre.entity.Order;
import dev.pasq.terra_madre.entity.ShoppingCart;
import dev.pasq.terra_madre.repository.OrderRepository;
import dev.pasq.terra_madre.repository.ShoppingCartRepository;
import dev.pasq.terra_madre.utilities.PaymentDetails;
import dev.pasq.terra_madre.utilities.ShippingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;


    public Order completeCheckout(Integer cartId, PaymentDetails paymentDetails, ShippingDetails shippingDetails){
        //validate the cart
        ShoppingCart cart = getValidCart(cartId);
        validPayment(paymentDetails);
        validShipping(shippingDetails);

        //create order
        Order order = createOrderFromCart(cart);
        //process payment
        processPayment(paymentDetails, order);
        //update order status
        order.setStatus("COMPLETED");
        orderRepository.save(order);

        //handle shipping
        handleShipping(shippingDetails, order);

        //send confirmation order
        sendConfirmation(order);
        //return
        return order;

    }

    private ShoppingCart getValidCart(Integer cartId){
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(()->new RuntimeException("Cart not found"));
    }

    private void validPayment(PaymentDetails paymentDetails){

    }

    private void validShipping(ShippingDetails shippingDetails){

    }

    private Order createOrderFromCart(ShoppingCart cart){
        return orderService.createOrder(cart.getCartId());
    }

    private void processPayment(PaymentDetails paymentDetails, Order order){

    }

    private void handleShipping(ShippingDetails shippingDetails, Order order){

    }

    private void sendConfirmation(Order order){

    }

}
