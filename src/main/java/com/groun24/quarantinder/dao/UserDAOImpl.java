package com.groun24.quarantinder.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.groun24.quarantinder.Modal.User;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> get() {
        final Session currSession = entityManager.unwrap(Session.class);
        final Query<User> query = currSession.createQuery("from User", User.class);
        final List<User> list = query.getResultList();
        return list;
    }

    @Override
    public User get( int id) {
        final Session currSession = entityManager.unwrap(Session.class);
        final User user = currSession.get(User.class, id);
        return user;
    }

    @Override
    public User save(User user) {
          Session currSession = entityManager.unwrap(Session.class);
          currSession.saveOrUpdate(user);
        return user;
    }
    
    @Override
    public void update( User user) {
          Session currSession = entityManager.unwrap(Session.class);
          currSession.merge(user);
          currSession.evict(user);
    }

    @Override
    public void delete( int id) {
    	  Session currSession = entityManager.unwrap(Session.class);
    	  User user = currSession.get(User.class, id);
    	  currSession.delete(user);
    }

}
