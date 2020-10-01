package com.order.demo.controller;

import com.order.demo.model.Restaurant;
import com.order.demo.model.User;
import com.order.demo.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//@RestController
@Controller
@RequestMapping("/restaurant")
public class RestaurantController{
    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String getRestaurants(Model model, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        model.addAttribute("user", (User) userObj);
        model.addAttribute("restaurants", restaurantService.getRestaurants());
        model.addAttribute("newrestaurant", new Restaurant());
        return "restaurant";
    }

    @PostMapping("/new")
    public String newRestaurant(HttpSession session, @Valid Restaurant restaurant) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        restaurantService.createRestaurant(restaurant);
        return "restaurant";
    }

    @GetMapping("/addrestaurant")
    public String addRestaurant(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "addRestaurant";
    }

    @PostMapping("/addrestaurant")
    public String addRestaurant(@Valid Restaurant restaurant, HttpSession session) {
        System.out.println("hello...........");
        User user = (User) session.getAttribute("user");
        restaurant.setUser(user);
        restaurantService.createRestaurant(restaurant);
        return "redirect:/restaurant/";
    }


    @GetMapping("/new")
    public String showRestaurant(HttpSession session, @Valid Restaurant restaurant) {
        Object userObj = session.getAttribute("user");
        return "restaurant";
    }

    @GetMapping("/view")
    public String showRestaurant(HttpSession session) {
        Object userObj = session.getAttribute("user");
        return "restaurant";
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable("id") long id){
        restaurantService.deleteRestaurantById(id);
        return "redirect:/restaurant/";
    }

    @GetMapping("/edit/{id}")
    public String editRestaurants(@PathVariable ("id") long id, Model model){
        model.addAttribute("restaurant", restaurantService.findRestaurantById(id));
        restaurantService.deleteRestaurantById(id);
        return "editRestaurant";
    }

    @PostMapping("/editRestaurant")
    public String editRestaurant( Restaurant restaurant) {
        restaurantService.save(restaurant);
        return "redirect:/restaurant/";
    }
}
