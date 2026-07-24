package com.example.demo.services;


import com.example.demo.entities.Cart;
import com.example.demo.entities.Cart_Item;
import com.example.demo.entities.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<Cart_Item> cart_items;
}
