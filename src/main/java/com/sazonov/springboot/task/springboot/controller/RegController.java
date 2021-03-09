package com.sazonov.springboot.task.springboot.controller;

import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import com.sazonov.springboot.task.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.*;
import java.util.Collections;


@Controller
@RequestMapping
public class RegController {
    private final UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String showRegPage(Model model){
        model.addAttribute("newUser", new User());
        return "reg";
    }
    @PostMapping("/reg")
    public String regUser(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult,  Model model){

        if(bindingResult.hasErrors()){
            return  "reg";
        }
        user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
        userService.create(user);
        return "redirect:/login";
    }
//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login";
//    }
}