package com.order.demo.repository;

import com.order.demo.model.Restaurant;
import com.order.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    Iterable<Restaurant> findRestaurantByAddress(User user);

    Restaurant findRestaurantById(Long id);

    Iterable<Restaurant> findRestaurantByAddress(String address);


}