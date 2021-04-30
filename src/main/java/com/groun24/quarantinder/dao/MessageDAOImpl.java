package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.EntityManager;
import java.util.List;

import org.hibernate.Session;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private EntityManager entityManager;


    @Transactional
    @Override
    public void save(OutputMessage message) {
        // System.out.println(message);
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(message);

    }

    @Transactional
    public List<OutputMessage> getAll(String recipientId, String senderId) {
        final Session currSession = entityManager.unwrap(Session.class);

        @SuppressWarnings("unchecked")
        final List<OutputMessage> query = currSession.createQuery("FROM OutputMessage").getResultList();

        return query;

    }

}
