package com.sazonov.springboot.task.springboot.dao;


import com.sazonov.springboot.task.springboot.model.User;
import java.util.List;

public interface UserDao {

   User getUserById(int id);
   List<User> index();
   boolean create(User user);
   boolean update(User user);
   void delete(int id);
   User findByUserForUsername(String username);
   boolean userExist(String username);

}
