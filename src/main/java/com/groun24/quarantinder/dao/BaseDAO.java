package com.groun24.quarantinder.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDAO<T> {
    
    @Autowired
    private EntityManager entityManager; 

    final Class<T> typeParameterClass;

    protected BaseDAO(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    protected List<T> queryDatabase(String queryString) {
        final Session session = entityManager.unwrap(Session.class);
        Query<T> query = session.createQuery(queryString, typeParameterClass);
        return query.getResultList();
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public T get(int id) {
        final Session session = getSession();
        final T object = session.get(typeParameterClass, id);
        return object;
    }

    public void save(T object) {
        final Session currSession = getSession();
        currSession.saveOrUpdate(object);
    }

    public void delete(int id) {
        final Session currSession = getSession();
        T object = currSession.get(typeParameterClass, id);
        currSession.delete(object);
    }
}
