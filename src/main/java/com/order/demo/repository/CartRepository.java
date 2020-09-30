package com.order.demo.repository;


import com.order.demo.model.Cart;
import com.order.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    Cart deleteAllById(Long id);
    Cart findByItemid(Long id);
}
