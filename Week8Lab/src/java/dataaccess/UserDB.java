package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.*;

public class UserDB {

    public static List<User> getAll(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
           List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
           
           return users;
        } finally {
           em.close();
        }

    }

    public User get(String email) throws Exception {
     EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
           User user = em.find(User.class, email);
                 
            return user;
        } finally {
          em.close();
        }

    }

    public void insert(User user) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
           em.persist(user);
           et.commit();
        } catch(Exception e){
            et.rollback();
        }finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
       
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
           em.merge(user);
           et.commit();
        }  catch(Exception e){
            et.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(String email) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
         User user = em.find(User.class, email);
        
        try {
            et.begin();
           em.remove(em.merge(user));
           et.commit();
        } catch(Exception e){
            et.rollback();
        } finally {
            em.close();
        }
    }

}
