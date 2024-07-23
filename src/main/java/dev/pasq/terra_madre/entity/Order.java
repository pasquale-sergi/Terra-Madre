package dev.pasq.terra_madre.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Setter
@Getter
@Builder
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    private Double totalAmount;
    private String status;
    private Date createdAt;

    @PrePersist
    public void onCreate(){
        createdAt = new Date();
    }


    public Order(){}

    public Order(Integer orderId, ShoppingCart cart, Double totalAmount, String status, Date createdAt){
        this.orderId = orderId;
        this.cart = cart;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

}
