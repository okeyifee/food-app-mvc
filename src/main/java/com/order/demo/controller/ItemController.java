package com.order.demo.controller;

import com.order.demo.model.Item;
import com.order.demo.model.Restaurant;
import com.order.demo.model.User;
import com.order.demo.service.ItemService;
import com.order.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/items")
public class ItemController{

    private final ItemService itemService;

    private final  RestaurantService restaurantService;

    @Autowired
    public ItemController(ItemService itemService, RestaurantService restaurantService) {
        this.itemService = itemService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String getItems(Model model, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        model.addAttribute("user", (User) userObj);
        model.addAttribute("items", itemService.getItems());
        model.addAttribute("newitem", new Item());
        return "item";
    }

    @PostMapping("/new")
    public String newItem(HttpSession session, @Valid Item item) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        itemService.createItem(item);
        return "restaurant";
    }

    @GetMapping("/additem/{id}")
    public String addItem(@PathVariable("id") long id, Model model, HttpSession session) {
        session.setAttribute("id", id);
        model.addAttribute("item", new Item());
        return "addItem";
    }


    @PostMapping("/additem")
    public String addItem(@Valid Item item, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/signin";
        }
        Long id = (Long) session.getAttribute("id");
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        item.setRestaurant(restaurant);
        String name = item.getItemName();
        String info = item.getItemInfo();
        double price = item.getItemPrice();
        Item newItem = new Item();

        newItem.setId(0);
        newItem.setItemInfo(info);
        newItem.setItemName(name);
        newItem.setItemPrice(price);
        newItem.setRestaurant(restaurant);

        itemService.createItem(item);
        return "redirect:/items/";
    }

    //public ResponseEntity<>


    @GetMapping("/new")

    public String showItem(HttpSession session, @Valid Item item) {
        Object userObj = session.getAttribute("user");
        return "restaurant";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id) {
        itemService.deleteItemById(id);
        return "redirect:/items/";
    }

    @GetMapping("/edit/{id}")
    public String editItems(@PathVariable("id") long id, Model model) {
        model.addAttribute("item", itemService.findItemById(id));
        itemService.deleteItemById(id);
        return "editItem";
    }

    @PostMapping("/editItem")
    public String editItem(Item item) {
        itemService.save(item);
        return "redirect:/items/";
    }
}
