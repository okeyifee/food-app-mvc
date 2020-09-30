package com.order.demo.model;

import com.order.demo.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String orderStatus;

    @Column(name = "OrderDate", nullable = false)
    private LocalDate timeStamp;

    @Column(name = "OrderTime", nullable = false)
    private LocalTime time;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "Total", nullable = false)
    private double Total;

    @OneToOne(mappedBy = "order")
    private Cart carts;

    @ManyToOne
    private User user;


//    public Double getTotalOrderPrice() {
//        double sum = 0D;
//        List<OrderItem> orderItems = getOrderItems();
//        for (OrderItem op : orderItems) {
//            sum += op.getTotalPrice();
//        }
//        return sum;
//    }
}
