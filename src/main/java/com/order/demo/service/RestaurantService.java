package com.order.demo.service;

import com.order.demo.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface RestaurantService {

    Restaurant createRestaurant (Restaurant restaurant);
    Restaurant findRestaurantById(Long id);
    List<Restaurant> getRestaurantByUserAddress (String address);
    List<Restaurant> getRestaurantByName (String name);
    Restaurant editRestaurant (Restaurant restaurant);
    List<Restaurant> getRestaurants ();
    boolean deleteRestaurantById (Long id);
    boolean deleteAll();
    Restaurant save(Restaurant restaurant);
}