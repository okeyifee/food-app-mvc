package com.order.demo.controller;

import com.order.demo.model.User;
import com.order.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "auth")
public class SignupController{
    UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid User user, BindingResult bindingResult, Model model) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            log.info("User Already exist");
            return "redirect:/auth/signup";
        } else {
            userService.createUser(user);
            return "signin";
        }
    }
}
