package com.order.demo.controller;

import com.order.demo.model.Item;
import com.order.demo.model.Restaurant;
import com.order.demo.model.User;
import com.order.demo.service.ItemService;
import com.order.demo.service.RestaurantService;
import com.order.demo.service.UserService;
import com.order.demo.util.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequestMapping("/auth")
public class SigninController {
    private UserService userService;
    private ItemService itemService;
    private RestaurantService restaurantService;


    public SigninController(UserService userService, RestaurantService restaurantService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        return "signin";
    }

    @PostMapping("/login")
    public String login (HttpSession session, @Valid User user, Model model, BindingResult bindingResult) {
        User gottenUser = userService.getUser(user.getEmail());
        String a = user.getEmail();
        String b= user.getPassword();

        if (!userService.findByEmail(user.getEmail()).isPresent() || userService.findByPassword(a,b) != true) {
            bindingResult.rejectValue("email", "error.user", "Email/password does not exist");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            log.info("Incorrect username/password");
            return "redirect:/auth/login";
        } else {

            if (gottenUser == null || !gottenUser.getPassword().equals(user.getPassword())) {
                model.addAttribute("invalid", "Invalid username or password");
                return "signin";
            }

            session.setAttribute("user", gottenUser);
            model.addAttribute("items", itemService.getItems());

            if (gottenUser.getApp_User_Type().equals(Type.admin)) {
                gottenUser.setPassword("");
                return "adminLanding";
            }
            return "landing";
        }
    }

    @GetMapping("/about")
    public String aboutus(){
        return "aboutus";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("items", itemService.getItems());
        return "landing";
    }
}

