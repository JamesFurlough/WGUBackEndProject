package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="divisions")
@Getter
@Setter
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private long id;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL)
    private Set<Customer> customerSet;

    @Column(name = "division")
    private String division_name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, insertable = false, updatable = false)
    private Country country;

    @Column(name = "country_id")
    private long country_id;
    public void setCountry(Country country) {
        setCountry_id(country.getId());
        this.country = country;
    }

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "last_update")
    private Date last_update;
}
