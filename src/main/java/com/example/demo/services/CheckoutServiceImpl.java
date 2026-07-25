package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Cart_Item;
import com.example.demo.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // retrieve the cart info from dto
        Cart cart = purchase.getCart();
        Customer customer = purchase.getCustomer();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // set cart status
        cart.setStatus(Cart.Status.ordered);

        // populate cart with cart_items
        Set<Cart_Item> cart_items = purchase.getCart_items();
        cart_items.forEach((item -> cart.add(item)));

        // populate customer with cart
        customer.add(cart);

        // save to the Database
        cartRepository.save(cart);

        // return a response
        if (cart != null) {
            if (cart.getCart_items() != null) {
                if (!cart.getCart_items().isEmpty()) {
                    return new PurchaseResponse(orderTrackingNumber);
                }
            }
        }
        return new PurchaseResponse("ERROR! Cart Cannot be empty");
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
