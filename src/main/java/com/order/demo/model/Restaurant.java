package com.order.demo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String address;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Item> items;

}
