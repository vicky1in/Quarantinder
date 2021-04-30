package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.ZoomSchedule;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ZoomDataPrepServiceImpl implements ZoomDataPrepService {

    @Autowired
    ZoomDBService dbService;

    @Override
    public HttpHeaders createAuthenticationHeaders(String clientID, String clientSecret){
        HttpHeaders header = new HttpHeaders();
        String auth = clientID + ":" + clientSecret;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII) );
        String authHeader = "Basic " + new String(encodedAuth);
        header.add("Authorization", authHeader);
        header.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        return header;
    }

    @Override
    public HttpHeaders createAccessTokenHeaders(){
        HttpHeaders header = new HttpHeaders();
        String authHeader = "Bearer " + dbService.getAccessToken();
        header.add("Authorization", authHeader);
        return header;
    }

    @Override
    public String createScheduleRequestBody(ZoomSchedule scheduleAttributes) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"topic\":\"").append(scheduleAttributes.getTopic()).append("\",")
                .append("\"type\":\"").append(scheduleAttributes.getType()).append("\",")
                .append("\"start_time\":\"").append(scheduleAttributes.getStartTime()).append("\",")
                .append("\"duration\":\"").append(scheduleAttributes.getDuration()).append("\",")
                .append("\"timezone\":\"").append(scheduleAttributes.getTimezone()).append("\"")
                .append("}");
        return builder.toString();
    }

}
