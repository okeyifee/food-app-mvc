package com.order.demo.controller;


import com.order.demo.model.Cart;
import com.order.demo.model.Item;
import com.order.demo.model.Order;
import com.order.demo.model.User;
import com.order.demo.service.CartService;
import com.order.demo.service.ItemService;
import com.order.demo.service.OrderService;
import com.order.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class OrderController{

    CartService cartService;
    ItemService itemService;
    UserService userService;
    OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, ItemService itemService, UserService userService, OrderService orderService) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        model.addAttribute("users", (User) userObj);
        model.addAttribute("carts", cartService.itemsInCart());
        model.addAttribute("orders", orderService.OrdersInDatabase());


        List<Cart> find = cartService.itemsInCart();
        List<Order> check = orderService.OrdersInDatabase();
        double toTal = 0;
        double sum = 0;


        for (Cart var : find) {
            double amount = var.getItemPrice() * var.getQuantity();
            LocalDate timestamp = LocalDate.now();
            LocalTime time = LocalTime.now();

            Order newOrder = new Order();

            newOrder.setTimeStamp(timestamp);
            newOrder.setTime(time);
            double newAmount = (toTal + amount);
            newOrder.setAmount(newAmount);
            orderService.addOrder(newOrder);
        }

        for (Order var2 : check) {
            double total = var2.getAmount();
            System.out.println(total);
            LocalDate timestamp = var2.getTimeStamp();
            LocalTime time = var2.getTime();

            Order newOrder = new Order();

            newOrder.setTimeStamp(timestamp);
            newOrder.setTime(time);
            sum = sum + total;
            System.out.println(sum);
            newOrder.setTotal(sum);
            System.out.println(newOrder.getTotal());
            orderService.addOrder(newOrder);
        }
        toTal = 0;
        sum = 0;
        cartService.clearItems();
        return "orderpage";
    }

    @GetMapping("/order/add")
    public String addOrder(@PathVariable("id") long id, Model model, HttpSession session) {

        User newuser = (User) session.getAttribute("user");
        if (newuser == null) return "redirect:/auth/login";

        model.addAttribute("users", (User) newuser);
        model.addAttribute("carts", cartService.itemsInCart());

//      Assigns the item properties to a variable
        Cart check = cartService.findCartByItemid(id);

        return "orderpage";
    }
}