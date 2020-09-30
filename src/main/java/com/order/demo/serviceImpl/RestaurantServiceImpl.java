package com.order.demo.serviceImpl;

import com.order.demo.model.Restaurant;
import com.order.demo.repository.RestaurantRepository;
import com.order.demo.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService{

    RestaurantRepository restaurantRepository;


    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getRestaurantByUserAddress(String address) {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurantByName(String name) {
        return null;
    }

    @Override
    public Restaurant editRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public boolean deleteRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant == null) return false;
        restaurantRepository.delete(restaurant);
        return true;
    }

    @Override
    public boolean deleteAll() {
        log.info("Deleting all restaurants ... ");
        restaurantRepository.deleteAll();
        return true;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        System.out.println(restaurant.getName());
        System.out.println(restaurant.getDescription());
        System.out.println(restaurant.getAddress());
        return restaurantRepository.save(restaurant);
    }


    @Override
    public Restaurant findRestaurantById(Long id) {
        return restaurantRepository.findRestaurantById(id);
    }
}























//   Future improvement on the App to use user location and search nearest restaurant from map
//
//    @Override
//    public List<Restaurant> getRestaurantByUserAddress(String address) {
//        List<Restaurant> restaurants = new ArrayList<>();
//        restaurantRepository.findRestaurantByAddress(address).forEach(restaurants::add);
//        System.out.println(restaurants);
//        return restaurants;
//    }
