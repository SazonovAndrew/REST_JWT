package com.sazonov.springboot.task.springboot.service;

import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User getUserById(int id);
    List<User> getAllUsers();
    User create(User user);
    void update(User user);
    void delete(int id);
    User findByUserForUsername(String username);
    List<Role> allRoles();
    Role findById(Integer id);
    void deleteRole(Integer id);
    Role saveRole(Role role);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    List<String> mapToRoleName(Set<Role> roleList);

}
