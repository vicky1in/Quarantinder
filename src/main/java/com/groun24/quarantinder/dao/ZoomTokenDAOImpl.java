package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.ZoomToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ZoomTokenDAOImpl implements ZoomTokenDAO{
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<ZoomToken> getTokens() {
        final Session currentSession = entityManager.unwrap(Session.class);
        final Query<ZoomToken> query = currentSession.createQuery("From ZoomToken", ZoomToken.class);
        final List<ZoomToken> result = query.getResultList();
        return result;
    }

    @Transactional
    @Override
    public void updateToken(ZoomToken token) {
        final Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(token);
    }

    @Transactional
    @Override
    public void deleteToken(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        ZoomToken token = currentSession.get(ZoomToken.class, id);
        currentSession.delete(token);
    }

    @Transactional
    @Override
    public void createAndSave(String accessToken) {
        final Session currentSession = entityManager.unwrap(Session.class);
        ZoomToken newToken = new ZoomToken();
        newToken.setAccessToken(accessToken);
        currentSession.saveOrUpdate(newToken);
    }
}
