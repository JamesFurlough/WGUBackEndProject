package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;

    @OneToMany(mappedBy = "cart")
    private Set<Cart_Item> cart_items;

    @Column(name = "package_price")
    private float package_price;

    @Column(name = "party_size")
    private int party_size;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "status")
    private Status status;

    private enum Status {
        pending,
        ordered,
        canceled
    }

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
