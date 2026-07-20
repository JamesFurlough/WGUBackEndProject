package com.example.demo.dao;

import com.example.demo.entities.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface Cart_ItemRepository extends JpaRepository <Cart_Item, Long> {
}
