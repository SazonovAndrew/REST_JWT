package com.sazonov.springboot.task.springboot.service;

import com.sazonov.springboot.task.springboot.dao.RoleDao;
import com.sazonov.springboot.task.springboot.dao.UserDao;
import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;
   @Autowired
   private RoleDao roleDao;

   @Override
   public boolean create(User user) {
      return userDao.create(user);
   }

   @Override
   public User getUserById(int id) {
      return userDao.getUserById(id);
   }

   @Override
   public List<User> index() {
      return userDao.index();
   }

   @Override
   public void update(User user) {
      userDao.update(user);
   }

   @Override
   public void delete(int id) {
      userDao.delete(id);
   }

   @Override
   public User findByUserForUsername(String username) {
      return userDao.findByUserForUsername(username);
   }

   @Override
   public List<Role> allRoles() {
      return roleDao.allRoles();
   }

   @Override
   public Role findById(Long id) {
      return roleDao.findById(id);
   }

   @Override
   public void deleteRole(Long id) {
      roleDao.deleteRole(id);
   }

   @Override
   public boolean saveRole(Role role) {
      return roleDao.saveRole(role);
   }

   @Override
   public boolean userExist(String username) {
      return userDao.userExist(username);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return findByUserForUsername(username);
   }
}

