package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;

public interface MatchManager {

    public List<Match> get();

    public Match get(int matchID);

    public Match get(User userA, User userB);
    
    public List<Match> get(User user);

    public List<Match> get(User user, boolean accepted);

    public void save(Match match);

    public void delete(int matchID);
}
