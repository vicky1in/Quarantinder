package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.ZoomSchedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZoomAPICallServiceImpl implements ZoomAPICallService{

    @Autowired
    ZoomDataPrepService zoomDataPrepService;

    @Autowired
    ZoomDBService zoomDBService;

    public int getInvitation(String meetingId, Model model) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        long ID = Long.parseLong(meetingId);
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.zoom.us")
                .path("v2/meetings/" + ID +"/invitation")
                .buildAndExpand();
        HttpHeaders header = zoomDataPrepService.createAccessTokenHeaders();
        HttpEntity requestEntity = new HttpEntity(header);

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                requestEntity, String.class);

        String resultString = response.getBody();
        JSONObject obj = new JSONObject(resultString);
        String originalString = obj.getString("invitation");
        String meetingInvitation = (originalString).replace("\r\n","<br />");
        model.addAttribute("meetingInvitation", meetingInvitation);
        model.addAttribute("meetingInvitationString", originalString);
        return response.getStatusCodeValue();
    }

    @Override
    public int getMeetingDetails(String meetingId, Model model) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        long ID = Long.parseLong(meetingId);
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.zoom.us")
                .path("v2/meetings/" + ID)
                .buildAndExpand();

        HttpHeaders header = zoomDataPrepService.createAccessTokenHeaders();
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                new HttpEntity(header), String.class);

        String resultString = response.getBody();
        JSONObject obj = new JSONObject(resultString);

        extractJsonContents(model, ID, obj, "");
        return response.getStatusCodeValue();
    }

    private void extractJsonContents(Model model, long ID, JSONObject obj, String optionalMessage) throws JSONException {
        model.addAttribute("meetingID",ID);
        model.addAttribute("startURL", obj.getString("start_url"));
        model.addAttribute("startTime", obj.getString("start_time"));
        model.addAttribute("duration", obj.getString("duration"));
        model.addAttribute("topic", obj.getString("topic"));
        model.addAttribute("meetingURL",obj.getString("join_url"));
        model.addAttribute("createdAt",obj.getString("created_at"));
        model.addAttribute("optionalMessage", optionalMessage);
    }

    @Override
    public int deleteMeeting(String meetingId) {
        RestTemplate restTemplate = new RestTemplate();
        long ID = Long.parseLong(meetingId);
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.zoom.us")
                .path("v2/meetings/" + ID)
                .buildAndExpand();
        HttpHeaders header = zoomDataPrepService.createAccessTokenHeaders();
        HttpEntity requestEntity = new HttpEntity(header);
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.DELETE,
                requestEntity, String.class);
        return response.getStatusCodeValue();
    }

    @Override
    public int listMeetings (Model model) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        // build uri
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.zoom.us")
                .path("v2/users/me/meetings")
                .buildAndExpand();

        // build header with access token
        HttpHeaders header = zoomDataPrepService.createAccessTokenHeaders();

        // build request with header and send request
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                new HttpEntity(header), String.class);

        //process json contents
        String resultString = response.getBody();
        JSONObject obj = new JSONObject(resultString);
        int totalRecords = Integer.parseInt(obj.getString("total_records"));
        JSONArray jsonArray = obj.getJSONArray("meetings");
        List<Map<String,String>> data = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i ++ ) {
            JSONObject object = (JSONObject)jsonArray.get(i);
            Map<String,String> map = new HashMap<>();
            map.put("meetingId",object.getString("id"));
            map.put("meetingTopic", object.getString("topic"));
            map.put("meetingStartTime", object.getString("start_time"));
            map.put("meetingDuration", object.getString("duration"));
            map.put("meetingJoinURL", object.getString("join_url"));
            data.add(map);
        }
        model.addAttribute("totalrecord", totalRecords);
        model.addAttribute("list",data);
        return response.getStatusCodeValue();
    }

    @Override
    public int scheduleMeeting(ZoomSchedule scheduleForm, Model model) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        // build uri
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.zoom.us")
                .path("v2/users/me/meetings")
                .buildAndExpand();

        // build header with access token
        HttpHeaders header = zoomDataPrepService.createAccessTokenHeaders();

        // build request with body and header
        RequestEntity<String> request = RequestEntity.
                post(uriComponents.toUri()).
                contentType(MediaType.APPLICATION_JSON).
                headers(header).
                body(zoomDataPrepService.createScheduleRequestBody(scheduleForm));

        //send request
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST,
                request, String.class);

        String resultString = response.getBody();
        JSONObject obj = new JSONObject(resultString);
        String IDString = obj.getString("id");
        long ID = Long.parseLong(IDString);
        extractJsonContents(model, ID, obj,"Your video chat has been scheduled successfully!" );
        getInvitation(IDString,  model);
        return response.getStatusCodeValue();
    }

    @Override
    public boolean isAuthenticated() {
        if (zoomDBService.isTableEmpty()) { // table empty -> not authenticated, return false
            return false;
        }
        return true;
    }

}
