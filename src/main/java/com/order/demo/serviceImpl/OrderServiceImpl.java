package com.order.demo.serviceImpl;

import com.order.demo.model.Order;
import com.order.demo.repository.OrderRepository;
import com.order.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }

//    @Override
//    public Optional<Order> getOrderByOrderId(Long id) {
//        return orderRepository.findById(id);
//    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public boolean clearOrders() {
        orderRepository.deleteAll();
        return true;
    }

    @Override
    public Order deleteOrderById(Long id) {
        return null;
    }

    @Override
    public Order getOrdersByUserId(Long id) {
        return (Order) orderRepository.findAllById(Collections.singleton(id));
    }

    @Override
    public List<Order> OrdersInDatabase() {
        return orderRepository.findAll();
    }

    @Override
    public boolean findOrder(Long id) {
        orderRepository.findById(id);
        return true;
    }

    @Override
    public Order findOrderByOrderId(Long id) {
        return null;
    }

    @Override
    public Order findOrderByCartid(Long cartId) {
        return orderRepository.findByCartsId(cartId);
    }
}
