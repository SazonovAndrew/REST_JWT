package com.sazonov.springboot.task.springboot.dao;


import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
     @Query("SELECT p FROM User p JOIN FETCH p.roles WHERE p.username = :username")
     User findByUsername(@Param("username")String username);
     @Query("SELECT p FROM User p JOIN FETCH p.roles WHERE p.id = :id")
     Optional<User> findById(@Param("id") int id);
     @Query("SELECT DISTINCT p FROM User p JOIN FETCH p.roles ORDER BY p.id")
     List<User> findAll();

}
