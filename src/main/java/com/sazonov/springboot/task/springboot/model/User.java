package com.sazonov.springboot.task.springboot.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private int id;

   @Column(name = "name")
   private String name;

   @Column(name = "surname")
   private String surname;


   @Column(name = "age")
   private int age;


   @Column(name = "username")
   @Size(min = 4, message = "Must be more than 4 symbols")
   private String username;

   @Column(name = "password")
   @Size(min = 4, message = "Must be more than 4 symbols")
   private String password;
   @Transient
   private String passwordConfirm;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {
   }

   public User(int id, String name, String surname, int age, String username, String password) {
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.age = age;
      this.username = username;
      this.password = password;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPasswordConfirm() {
      return passwordConfirm;
   }

   public void setPasswordConfirm(String passwordConfirm) {
      this.passwordConfirm = passwordConfirm;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + name + '\'' +
              ", lastName='" + surname + '\'' +
              ", age=" + age +
              '}';
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public Set<Role> getRoles() {
      return roles;
   }
   public String rolesString(){
      return getRoles().stream()
              .map(Object::toString)
              .map(p -> p.replace("ROLE_", " "))
              .collect(Collectors.joining(", "));
   }
   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }
}

