package com.sazonov.springboot.task.springboot.dao;


import com.sazonov.springboot.task.springboot.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> allRoles();
    Role findById(Long id);
    void deleteRole(Long id);
    boolean saveRole(Role role);
}
