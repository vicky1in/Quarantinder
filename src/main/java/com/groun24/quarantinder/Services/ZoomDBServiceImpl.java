package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.ZoomToken;
import com.groun24.quarantinder.dao.ZoomTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoomDBServiceImpl implements ZoomDBService {

    @Autowired
    ZoomTokenDAO zoomTokens;

    @Override
    public boolean isTableEmpty() {
        final List<ZoomToken> result = zoomTokens.getTokens();
        if (result.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ZoomToken> getAccessTokenList() {
        final List<ZoomToken> result = zoomTokens.getTokens();
        return result;
    }

    @Override
    public String getAccessToken() {
        if(isTableEmpty()) {
            return "something is wrong";
        }
        List<ZoomToken> tokens = getAccessTokenList();
        return tokens.get(tokens.size()-1).getAccessToken();
    }

    @Override
    public List<ZoomToken> getRefreshToken() {
        return getAccessTokenList();
    }

    @Override
    public void updateAccessToken (ZoomToken token) {
        if (isTableEmpty()) {
            zoomTokens.updateToken(token);
        } else {
            ZoomToken existingToken = getAccessTokenList().get(0);
            zoomTokens.deleteToken(existingToken.getId());
            zoomTokens.updateToken(token);
        }
    }

    @Override
    public void saveNewToken(String accessToken) {
        zoomTokens.createAndSave(accessToken);
    }

    @Override
    public void clearTokens() {
        for(ZoomToken token: getAccessTokenList()){
            zoomTokens.deleteToken(token.getId());
        }
    }


}
