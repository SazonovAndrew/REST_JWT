package com.sazonov.springboot.task.springboot.controller;

import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import com.sazonov.springboot.task.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/admin")
    public String loadToUsersPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
        model.addAttribute("userTable", userService.index());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", userService.allRoles());
        model.addAttribute("sessionUser", user);
        return "users";
    }
//    @GetMapping("/admin/view")
//    public String index(Model model) {
//        model.addAttribute("users", userService.index());
//        return "index";
//    }
//    @GetMapping("/admin/new")
//    public String newUser(Model model){
//        model.addAttribute("user", new User());
//        model.addAttribute("allRoles", userService.allRoles());
//        return "new";
//    }

    @PostMapping("/admin/new")
    public  String create (@ModelAttribute("user") User user,
                           @RequestParam(value = "addRole", required = false) String userRole, Model model){

        Set<Role> roleSet = new HashSet<>();
        if(userRole == null){
            user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
            userService.create(user);
            return "redirect:/admin";
        }
        if (userRole.contains("ROLE_ADMIN")){
            roleSet.add(new Role(1L, "ADMIN"));
            user.setRoles(roleSet);
        }
        if (userRole.contains("ROLE_USER")) {
            roleSet.add(new Role(2L, "USER"));
            user.setRoles(roleSet);
        }
        userService.create(user);

        return "redirect:/admin";
    }

    @PutMapping("/admin/edit")
    public  String update( @ModelAttribute("user") User userEdit,
                           @RequestParam(value = "addRoles", required = false) String userRole,
                           Model model){


        Set<Role> roleSet = new HashSet<>();
        if(userRole == null){
            userEdit.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
            userService.create(userEdit);
            return "redirect:/admin";
        }
        if (userRole.contains("ROLE_ADMIN")){
            roleSet.add(new Role(1L, "ADMIN"));
            userEdit.setRoles(roleSet);
        }
        if (userRole.contains("ROLE_USER")) {
            roleSet.add(new Role(2L, "USER"));
            userEdit.setRoles(roleSet);
        }

        userService.update(userEdit);
        return "redirect:/admin";
    }


    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}