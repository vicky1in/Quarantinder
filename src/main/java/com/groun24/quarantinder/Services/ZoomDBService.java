package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.ZoomSchedule;
import com.groun24.quarantinder.Modal.ZoomToken;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface ZoomDBService {
    boolean isTableEmpty();
     List<ZoomToken> getAccessTokenList();
     List<ZoomToken> getRefreshToken();
     String getAccessToken();
     void updateAccessToken (ZoomToken token);
     void saveNewToken(String accessToken);
     void clearTokens();

}
