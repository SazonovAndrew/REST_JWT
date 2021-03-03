package com.sazonov.springboot.task.springboot.dao;


import com.sazonov.springboot.task.springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
