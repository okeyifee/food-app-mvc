package com.order.demo.controller;

import com.order.demo.model.ContactUs;
import com.order.demo.model.User;
import com.order.demo.service.ContactUsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping
public class FirstPageController{

    ContactUsService contactUsService;

    public FirstPageController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

        @GetMapping("/")
        public String firstpage() {
            return "firstpage";
        }

        @GetMapping("/contactus")
        public String contactus(Model model) {
            model.addAttribute("contactus", new ContactUs());
            return "contactus";
        }

        @PostMapping("/contactus")
        public String contactus(HttpSession session, @Valid ContactUs contactUs) {
            Object userObj = session.getAttribute("user");
            contactUsService.createContact(contactUs);
            if (userObj == null){
                return "redirect:/auth/login";
            }
                return "redirect:/contactus";
        }

    }

