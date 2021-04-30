package com.groun24.quarantinder.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.groun24.quarantinder.Modal.UserProfile;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileDAOImp implements UserProfileDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserProfile> get() {
        final Session currSession = entityManager.unwrap(Session.class);
        final Query<UserProfile> query = currSession.createQuery("from UserProfile", UserProfile.class);
        final List<UserProfile> list = query.getResultList();
        return list;
    }

    @Override
    public UserProfile get(int id) {
        final Session currSession = entityManager.unwrap(Session.class);
        final UserProfile user = currSession.get(UserProfile.class, id);
        return user;
    }

    @Override
    public void save(UserProfile user) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(user);
    }

    @Override
    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        UserProfile user = currSession.get(UserProfile.class, id);
        currSession.delete(user);
    }
    
}
