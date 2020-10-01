package com.order.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order.demo.util.Type;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private String email;
    private String password;
    private String address;
    private Type App_User_Type;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Restaurant> restaurants = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    private Cart cart;

}
