package com.sazonov.springboot.task.springboot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewController {


    @GetMapping("/user")
    public String show(){

        return "user";
    }
    @GetMapping("/admin")
    public String adminView(){
        return "users";
    }
    @GetMapping("/login")
    public String loginView(){
        return "login";
    }
    @GetMapping("/logout")
    public String exit(){
        return "/login";
    }
}