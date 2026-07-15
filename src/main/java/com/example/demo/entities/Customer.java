package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
}
