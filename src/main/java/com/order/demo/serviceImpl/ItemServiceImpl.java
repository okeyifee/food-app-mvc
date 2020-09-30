package com.order.demo.serviceImpl;


import com.order.demo.model.Item;
import com.order.demo.model.Restaurant;
import com.order.demo.repository.ItemRepository;
import com.order.demo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;


    @Override
    public Item createItem(Item item) {
        System.out.println(item.getItemName());
        System.out.println(item.getItemInfo());
        System.out.println(item.getItemPrice());
        return itemRepository.save(item);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean deleteItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) return false;
        itemRepository.delete(item);
        return true;
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }


    @Override
    public Iterable<Restaurant> getItemByName(String name) {
        return itemRepository.findItemsByItemName(name);
    }

    @Override
    public Restaurant editItem(Item item) {
        return null;
    }

}
