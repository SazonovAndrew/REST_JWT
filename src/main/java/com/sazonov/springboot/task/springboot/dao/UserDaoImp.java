package com.sazonov.springboot.task.springboot.dao;

import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;


@Repository
public class UserDaoImp implements  UserDao {

   private EntityManagerFactory emf;
   @Autowired
   public void setEmf(EntityManagerFactory emf) {
      this.emf = emf;
   }
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public List<User> index() {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      List<User> userList = em.createQuery("SELECT c from User c").getResultList();
      em.close();
      return userList;
   }

   @Override
   public User getUserById(int id) {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      User user = em.find(User.class, id);
      em.getTransaction().commit();
      em.close();
      return user;
   }

   @Override
   public boolean create(User user) {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      User userFromBD = findByUserForUsername(user.getUsername());
      if (userFromBD != null){
         return false;
      }
      Set<Role> roleSet = (Set<Role>) user.getAuthorities();
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      user.setRoles(roleSet);
      em.persist(user);
      em.getTransaction().commit();
      em.close();
      return true;
   }

   @Override
   public boolean update(User user) {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();



      Set<Role> roleSet = (Set<Role>) user.getAuthorities();
      user.setRoles(roleSet);
      if(user.getPassword().equals("")){
         user.setPassword(getUserById(user.getId()).getPassword());
      }else{
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      }
      em.merge(user);

      em.getTransaction().commit();
      em.close();
      return true;
   }

   @Override
   public void delete(int id) {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      em.remove(em.find(User.class,id));
      em.getTransaction().commit();
      em.close();
   }

   @Override
   public User findByUserForUsername(String username) {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      User user = null;
      try {
         Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:username");
         query.setParameter("username", username);
         user = (User) query.getSingleResult();
      } catch (Exception e) {
      }
      em.getTransaction().commit();
      em.close();
      return user;
   }

   @Override
   public boolean userExist(String username) {
      return findByUserForUsername(username) != null;
   }
}
