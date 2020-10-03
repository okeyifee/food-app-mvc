package com.order.demo.service;

import com.order.demo.model.Cart;
import com.order.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService{

    void removeOrder(Order order);

    void removeOrder(Long id);

    //  Optional<Order> getOrderByOrderId(Long id);
    Order addOrder(Order order);

    boolean clearOrders();

    Order deleteOrderById(Long id);

    Order getOrdersByUserId(Long id);

    List<Order> OrdersInDatabase();

    boolean findOrder(Long id);

    Order findOrderByOrderId(Long id);

    Order findOrderByCartid(Long cartId);
}



