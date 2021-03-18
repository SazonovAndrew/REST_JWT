package com.sazonov.springboot.task.springboot.controller;

import com.sazonov.springboot.task.springboot.config.jwt.JwtTokenProvider;
import com.sazonov.springboot.task.springboot.dto.AuthenticationRequestDto;
import com.sazonov.springboot.task.springboot.dto.AuthenticationResponseDto;
import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import com.sazonov.springboot.task.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AdminController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AdminController(UserService userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/admin/roles")
    public List<Role> loadAllRoles() {
        return userService.allRoles();
    }
    @GetMapping("/admin/users")
    public List<User> loadToUsersPage() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/users/{id}")
    public User getUser(@PathVariable("id")  int id){
        return userService.getUserById(id);
    }
    @GetMapping("/user")
    public User getAuthorizedUser(){
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
    }
    @PostMapping("/admin/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userService.create(user);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/admin/users")
    public ResponseEntity<User> editUser(@RequestBody User user){
        userService.update(user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto ){
        try {
            String username = authenticationRequestDto.getUsername();
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPassword()));
           User user = userService.findByUserForUsername(username);
           if(user == null){

               throw  new UsernameNotFoundException("Invalid username or password");
           }
           String token = jwtTokenProvider.createToken(username, user.getRoles());

           return ResponseEntity.ok(new AuthenticationResponseDto(token, userService.mapToRoleName(user.getRoles())));

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }

    }
}