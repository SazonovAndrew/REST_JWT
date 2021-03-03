package com.sazonov.springboot.task.springboot.dao;


import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
     User findByUsername(String username);

}
