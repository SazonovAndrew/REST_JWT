package com.sazonov.springboot.task.springboot.dao;

import com.sazonov.springboot.task.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public List<Role> allRoles() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Role> roleList = em.createQuery("SELECT c from Role c").getResultList();
        em.getTransaction().commit();
        em.close();
        return roleList;
    }

    @Override
    public Role findById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role role = em.find(Role.class, id);
        em.getTransaction().commit();
        em.close();
        return role;
    }

    @Override
    public void deleteRole(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public boolean saveRole(Role role) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
