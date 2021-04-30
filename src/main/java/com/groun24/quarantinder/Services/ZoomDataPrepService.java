package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.ZoomSchedule;
import org.springframework.http.HttpHeaders;

public interface ZoomDataPrepService {
    HttpHeaders createAuthenticationHeaders(String clientID, String clientSecret);
    HttpHeaders createAccessTokenHeaders();
    String createScheduleRequestBody(ZoomSchedule scheduleAttributes);
}
