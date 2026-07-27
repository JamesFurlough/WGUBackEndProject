package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
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

    public void add(Cart_Item cart_item) {
        // sets relationship with new cart_item
        if (cart_item != null) {
            if (cart_items == null) {
                cart_items = new HashSet<>();
            }
            cart_items.add(cart_item);
            cart_item.setCart(this);
        }
    }

    @Column(name = "package_price")
    private float package_price;

    @Column(name = "party_size")
    private int party_size;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        pending,
        ordered,
        canceled
    }

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
