package com.groun24.quarantinder.Services;

import org.json.JSONException;
import org.springframework.web.util.UriComponents;

public interface ZoomAuthCallService {
    int getAccessToken (String code) throws JSONException;
    UriComponents getAuthRedirectionURI();
}
