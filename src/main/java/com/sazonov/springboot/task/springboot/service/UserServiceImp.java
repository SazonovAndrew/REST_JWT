package com.sazonov.springboot.task.springboot.service;

import com.sazonov.springboot.task.springboot.dao.RoleRepository;
import com.sazonov.springboot.task.springboot.dao.UserRepository;
import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public User create(User user) {

      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
   }

   @Override
   public User getUserById(int id) {

      Optional<User> userFromDB = userRepository.findById(id);

      return userFromDB.orElse(new User());
   }

   @Override
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Override
   public void update(User user) {

      if(user.getPassword().equals(getUserById(user.getId()).getPassword())){
         user.setPassword(getUserById(user.getId()).getPassword());
      }else{
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      }
      userRepository.save(user);
   }

   @Override
   public void delete(int id) {
      userRepository.deleteById(id);
   }

   @Override
   public User findByUserForUsername(String username) {
      return userRepository.findByUsername(username);
   }

   @Override
   public List<Role> allRoles() {
      return roleRepository.findAll();
   }

   @Override
   public Role findById(Integer id) {

      Optional<Role> roleOptional = roleRepository.findById(id);
      return roleOptional.orElse(null);
   }

   @Override
   public void deleteRole(Integer id) {
      roleRepository.deleteById(id);
   }

   @Override
   public Role saveRole(Role role) {
      return roleRepository.save(role);
   }



   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return findByUserForUsername(username);
   }
}

