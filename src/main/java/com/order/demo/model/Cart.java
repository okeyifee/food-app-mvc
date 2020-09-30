package com.order.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "carts")
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemname;
    protected Long itemid;
    private Integer quantity;
    private  double itemPrice;
    private int count;

    @OneToOne
    private User users;

    @OneToOne
    private Order order;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();
}

