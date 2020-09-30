package com.order.demo.repository;

import com.order.demo.model.Cart;
import com.order.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    Order deleteOrderById(Long id);
    Order getOrdersByUserId(Long id);
    List<Order> getOrderById(Long id);
    Order findByCartsId(Long id);

}
