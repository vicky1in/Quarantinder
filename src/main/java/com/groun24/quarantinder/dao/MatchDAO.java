package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;

import org.springframework.stereotype.Repository;


@Repository(value = "MatchDAO")
public class MatchDAO extends BaseDAO<Match> {
    
    public MatchDAO() {
        super(Match.class);
    }

    public List<Match> get() { 
        return queryDatabase("FROM Match");

        // Session session = getSession();
        // CriteriaBuilder builder = session.getCriteriaBuilder();
        // CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
        // criteria.from(Match.class);
        // List<Match> matches = session.createQuery(criteria).getResultList();
        // session.close();
        // return matches;
    }

    public Match get(User userA, User userB) {
        String queryString = String.format("FROM Match WHERE userA = %d AND userB = %d", userA.getId(), userB.getId());
        final List<Match> list = queryDatabase(queryString);
        queryString = String.format("FROM Match WHERE userA = %d AND userB = %d", userB.getId(), userA.getId());
        list.addAll(queryDatabase(queryString));
        if (list.isEmpty()) {
            return null; 
        }
        else {
            return list.get(0);
        }
    }
    
    public List<Match> get(User user) {
        String queryString = String.format("FROM Match WHERE userA = %d", user.getId());
        final List<Match> list = queryDatabase(queryString);
        queryString = String.format("FROM Match WHERE userB = %d", user.getId());
        list.addAll(queryDatabase(queryString));
        return list;
    } 
}
