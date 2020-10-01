package com.order.demo.controller;

import com.order.demo.dto.UserResponse;
import com.order.demo.model.User;
import com.order.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/new")
    public String showUser(HttpSession session, @Valid User user) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        userService.getUsers();
        return "user";
    }


    @GetMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




//
//    @GetMapping("/edit/{id}")
//    public String editUsers(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUser(id));
//        userService.deleteUserById(id);
//        return "editUser";
//    }

    @GetMapping("edit/{id}")
    public ResponseEntity<User> editUsers(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
// i am supposed to redirect to another page
    }



    @PostMapping("/editUser")
    public String editUser(User user) {
        userService.save(user);
        return "redirect:/user";
    }


    @RequestMapping("user")
    public ResponseEntity<List<UserResponse>> viewUsers() {
//    public String viewUsers() {
        return new ResponseEntity<>(HttpStatus.OK);//"user";
    }

//    @GetMapping("user")
//    public ResponseEntity<List<UserResponse>> getUsers() {
//        List<UserResponse> users = userService.getUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }


}
