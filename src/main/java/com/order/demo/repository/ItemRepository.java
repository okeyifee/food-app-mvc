package com.order.demo.repository;

import com.order.demo.model.Item;
import com.order.demo.model.Restaurant;
import com.order.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

    Iterable<Item> findItemById(Long id);

    Iterable<Restaurant> findItemsByItemName(String itemName);

    Iterable<Restaurant> findItemByItemPrice(String itemPrice);

}
