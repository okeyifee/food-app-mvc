package com.order.demo.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "contactus")
public class ContactUs{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private String email;
    private String country;
    private String address;
    private String message;
}
