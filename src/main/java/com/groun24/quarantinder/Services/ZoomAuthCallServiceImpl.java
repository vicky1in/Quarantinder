package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.config.ZoomConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ZoomAuthCallServiceImpl implements ZoomAuthCallService {
    @Autowired
    ZoomConfig zoomConfig;

    @Autowired
    ZoomDataPrepService zoomDataPrepService;

    @Autowired
    ZoomDBService zoomDBService;

    @Override
    public int getAccessToken (String code) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("zoom.us")
                .path("oauth/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", code)
                .queryParam("redirect_uri", zoomConfig.getRedirectAuthLink())
                .buildAndExpand();
        HttpHeaders header = zoomDataPrepService.createAuthenticationHeaders(zoomConfig.getClientID(), zoomConfig.getClientSecret());

        HttpEntity requestEntity = new HttpEntity(header);
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST,
                requestEntity, String.class);

        String resultString = response.getBody();
        JSONObject obj = new JSONObject(resultString);
        String accessToken = obj.getString("access_token");

        zoomDBService.clearTokens();
        zoomDBService.saveNewToken(accessToken);
        return response.getStatusCodeValue();
    }

    @Override
    public UriComponents getAuthRedirectionURI() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("zoom.us")
                .path("oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", zoomConfig.getClientID())
                .queryParam("redirect_uri", zoomConfig.getRedirectAuthLink())
                .buildAndExpand();
        return uriComponents;
    }


}
