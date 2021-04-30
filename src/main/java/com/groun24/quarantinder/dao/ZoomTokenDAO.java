package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.ZoomToken;

import java.util.List;

public interface ZoomTokenDAO {

    List<ZoomToken> getTokens();

    void updateToken(ZoomToken token);

    void deleteToken(int id);

    void createAndSave(String accessToken);
}
