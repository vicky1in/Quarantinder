package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.ZoomSchedule;
import org.json.JSONException;
import org.springframework.ui.Model;

public interface ZoomAPICallService {
    int getInvitation(String meetingId, Model model) throws JSONException;
    int getMeetingDetails(String meetingId, Model model) throws JSONException;
    int deleteMeeting(String meetingId);
    int listMeetings (Model model) throws JSONException;
    int scheduleMeeting(ZoomSchedule scheduleForm, Model model) throws JSONException;
    boolean isAuthenticated();
}
