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

        // get cart_items
        Set<Cart_Item> cartItems = purchase.getCartItems();
        if (cartItems != null)
            cartItems.forEach(item -> cart.add(item));

        if ((cart == null) || (cart.getCart_items() == null) || (cart.getCart_items().isEmpty()))
        {
            return new PurchaseResponse("ERROR! Cart cannot be Null");
        }


        // populate customer with cart
        customer.add(cart);

        // save to the Database
        cartRepository.save(cart);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
