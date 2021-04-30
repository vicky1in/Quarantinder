package com.groun24.quarantinder.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.groun24.quarantinder.Modal.Location;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Location> get() {
        final Session currSession = entityManager.unwrap(Session.class);
        final Query<Location> query = currSession.createQuery("from Location", Location.class);
        final List<Location> list = query.getResultList();
        return list;
    }

    public Location get(int id) {
        final Session currSession = entityManager.unwrap(Session.class);
        final Location user = currSession.get(Location.class, id);
        return user;
    }

    public void save(Location location) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(location);
    }

    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        Location location = currSession.get(Location.class, id);
        currSession.delete(location);
    }
    
}
