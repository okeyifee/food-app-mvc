package com.order.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="items")
public class Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String itemName;
    private String itemInfo;
    private double itemPrice;
    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "restaurantid")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "useritems")
    private User user;

    @ManyToOne
    private Cart cart;

}
