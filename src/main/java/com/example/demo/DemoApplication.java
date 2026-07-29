package com.example.demo;
/*
* Author: James Furlough
* Date: 7/29/2026
* Description: Minimum Viable Product of New Backend System
* */
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    DemoApplication (CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @PostConstruct
    private void addCustomers() {
        if (customerRepository.count() <= 1) {
            Division division;
            division = divisionRepository.getReferenceById(2L);
            for (int n = 1; n <= 5; n++) {
                Customer customer = new Customer("test", "customer" + n, "123 test drive", "12345", "(123) 123-1234", division);
                customerRepository.save(customer);
            }
        }
    }
}
