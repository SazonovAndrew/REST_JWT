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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }
   @Transactional
   @Override
   public User create(User user) {

      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
   }
   @Transactional
   @Override
   public User getUserById(int id) {

      Optional<User> userFromDB = userRepository.findById(id);

      return userFromDB.orElse(new User());
   }
   @Transactional
   @Override
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Transactional
   @Override
   public void update(User user) {

      if(user.getPassword().equals(getUserById(user.getId()).getPassword())){
         user.setPassword(getUserById(user.getId()).getPassword());
      }else{
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      }
      userRepository.save(user);
   }

   @Transactional
   @Override
   public void delete(int id) {
      userRepository.deleteById(id);
   }

   @Transactional
   @Override
   public User findByUserForUsername(String username) {
      return userRepository.findByUsername(username);
   }

   @Transactional
   @Override
   public List<Role> allRoles() {
      return roleRepository.findAll();
   }

   @Transactional
   @Override
   public Role findById(Integer id) {
      Optional<Role> roleOptional = roleRepository.findById(id);
      return roleOptional.orElse(null);
   }

   @Transactional
   @Override
   public void deleteRole(Integer id) {
      roleRepository.deleteById(id);
   }

   @Transactional
   @Override
   public Role saveRole(Role role) {
      return roleRepository.save(role);
   }

   @Transactional
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return  findByUserForUsername(username);
   }

   @Override
   public List<String> mapToRoleName(Set<Role> roleList){
      return roleList.stream()
              .map(Role::getName
              ).collect(Collectors.toList());
   }
}

