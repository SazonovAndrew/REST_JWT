package com.sazonov.springboot.task.springboot.controller;

import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class UserController {


	@GetMapping("/user")
	public String show( Model model){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
		model.addAttribute("user", user);
		return "user";
	}









}