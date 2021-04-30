package com.groun24.quarantinder.Services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.groun24.quarantinder.dao.MatchDAO;
import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicMatchManager implements MatchManager {

    @Autowired
    MatchDAO matchDAO;

    @Transactional
    @Override
    public List<Match> get() {
        return matchDAO.get();
    }

    @Transactional
    @Override
    public Match get(int matchID) {
        return matchDAO.get(matchID);
    }

    @Transactional
    @Override
    public Match get(User userA, User userB) {
        return matchDAO.get(userA, userB);
    }

    @Transactional
    @Override
    public List<Match> get(User user) {
        return matchDAO.get(user);
    }

    @Transactional
    @Override
    public List<Match> get(User user, boolean accepted) {
        List<Match> allMatches = get(user);
        List<Match> matchesByStatus = new ArrayList<Match>();
        for (Match match : allMatches) {
            if (match.getAccepted() == accepted) {
                matchesByStatus.add(match);
            }
        }
        return matchesByStatus;
    }

    @Transactional
    @Override
    public void save(Match match) {
        if (match.getUserA().equals(match.getUserB())) {
            return;
        }
        Match existingMatch = get(match.getUserA(), match.getUserB());
        if (existingMatch != null) {
            existingMatch.setAccepted(match.getAccepted());
            matchDAO.save(existingMatch);
        }
        else {
            matchDAO.save(match);
        }
    }

    @Transactional
    @Override
    public void delete(int matchID) {
        Match match = matchDAO.get(matchID);
        if (match != null) {
            matchDAO.delete(matchID);
        }
    }
}
