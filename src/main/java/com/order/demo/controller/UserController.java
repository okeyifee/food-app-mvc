package com.order.demo.controller;

import com.order.demo.model.Restaurant;
import com.order.demo.model.User;
import com.order.demo.service.RestaurantService;
import com.order.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController{

    UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/user")
    public String getUsers(Model model, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        model.addAttribute("user", (User) userObj);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newuser", new User());
        return "user";
    }

    @GetMapping("/new")
    public String showUser(HttpSession session, @Valid User user) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        userService.getUsers();
        return "user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editUsers(@PathVariable ("id") long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        userService.deleteUserById(id);
        return "editUser";
    }

    @PostMapping("/editUser")
    public String editUser(User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @RequestMapping("/view")
    public String viewUsers() {
        return "user";
    }
}
