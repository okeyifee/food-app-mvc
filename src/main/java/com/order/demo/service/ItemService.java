package com.order.demo.service;

import com.order.demo.model.Item;
import com.order.demo.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService{

    Item createItem(Item item);

    Item findItemById(Long id);

    Iterable<Restaurant> getItemByName(String name);

    Restaurant editItem(Item item);

    List<Item> getItems();

    boolean deleteItemById(Long id);

    Item save(Item item);

}
