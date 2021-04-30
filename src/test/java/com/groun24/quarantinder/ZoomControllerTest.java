package com.groun24.quarantinder;

import com.groun24.quarantinder.Controller.ZoomController;
import com.groun24.quarantinder.Modal.ZoomSchedule;
import com.groun24.quarantinder.Services.ZoomAPICallService;
import com.groun24.quarantinder.Services.ZoomAuthCallService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("zoomtest")
@RunWith(SpringJUnit4ClassRunner.class)
public class ZoomControllerTest {

    @Autowired
    private ZoomController zoomController;

    @Autowired
    private ZoomAuthCallService zoomAuthCallService;

    @Autowired
    private ZoomAPICallService zoomAPICallService;

    @Test
    public void redirectToAuthTest() {
        when(zoomAuthCallService.getAuthRedirectionURI()).thenReturn(UriComponentsBuilder.newInstance()
                .scheme("https").host("zoom.us")
                .path("oauth").buildAndExpand()); // set up mock

        assertEquals("redirect:https://zoom.us/oauth",zoomController.redirectToAuth());
    }

    @Test
    public void getAuthCodeTest() throws JSONException {
        when(zoomAuthCallService.getAuthRedirectionURI()).thenReturn(UriComponentsBuilder.newInstance()
                .scheme("https").host("test.com")
                .path("testing").buildAndExpand()); // set up mock

        Model model = new ConcurrentModel(); // get the current model

        assertEquals("zoomauth",zoomController.getAuthenticationCode("code",model));
        assertEquals("Congrats! Authentication Successful!", model.getAttribute("optionalAuthenticationMessage"));
        verify(zoomAuthCallService).getAccessToken("code");
    }

    @Test
    public void scheduleTest() {
        Model model = new ConcurrentModel(); // get the current model
        assertEquals("schedulemeeting",zoomController.schedule(model));
    }

    @Test
    public void processScheduleTestNormalCase() throws JSONException {
        ZoomSchedule zoomSchedule = mock(ZoomSchedule.class); // get zoom schedule mock
        Model model = new ConcurrentModel(); // get the current model
        BindingResult bindingResult = mock (BindingResult.class);

        when(zoomAPICallService.scheduleMeeting(zoomSchedule, model)).thenReturn(201); // set up mock

        assertEquals("meetingdisplay",zoomController.processSchedule(zoomSchedule, model,bindingResult));
        verify(zoomAPICallService).scheduleMeeting(zoomSchedule, model);
    }

    @Test
    public void processScheduleTestErrorCase() throws JSONException {
        ZoomSchedule zoomSchedule = mock(ZoomSchedule.class); // get zoom schedule mock
        Model model = new ConcurrentModel(); // get the current model
        BindingResult bindingResult = mock (BindingResult.class);

        when(zoomAPICallService.scheduleMeeting(zoomSchedule, model)).thenReturn(400); // set up mock

        assertEquals("redirect:/error",zoomController.processSchedule(zoomSchedule, model,bindingResult));
        verify(zoomAPICallService).scheduleMeeting(zoomSchedule, model);
    }


    @Test
    public void listMeetingTestNormalCase() throws JSONException {
        Model model = new ConcurrentModel(); // get the current model
        when(zoomAPICallService.listMeetings(model)).thenReturn(200); // set up mock

        assertEquals("listmeeting",zoomController.listMeetings(model));
    }

    @Test
    public void listMeetingTestErrorCase() throws JSONException {
        Model model = new ConcurrentModel(); // get the current model
        when(zoomAPICallService.listMeetings(model)).thenReturn(400); // set up mock

        assertEquals("redirect:/error",zoomController.listMeetings(model));
        verify(zoomAPICallService).listMeetings(model);
    }

    @Test
    public void deleteMeetingTestNormalCase() {
        when(zoomAPICallService.deleteMeeting("123")).thenReturn(204); // set up mock
        //204 indicate normal

        assertEquals("redirect:/zoom/meetings",zoomController.deleteMeeting("123"));
    }

    @Test
    public void deleteMeetingTestErrorCase()  {
        when(zoomAPICallService.deleteMeeting("123")).thenReturn(200); // set up mock
        // non 204 indicates error

        assertEquals("redirect:/error",zoomController.deleteMeeting("123"));
        verify(zoomAPICallService).deleteMeeting("123");
    }

    @Test
    public void getMeetingInvitationTestNormalCase() throws JSONException {
        Model model = new ConcurrentModel(); // get the current model
        when(zoomAPICallService.getInvitation("123", model)).thenReturn(200); // set up mock
        when(zoomAPICallService.getMeetingDetails("123", model)).thenReturn(200); // set up mock
        //200 indicate normal

        assertEquals("meetingdisplay",zoomController.getMeetingInvitation("123",model));
    }

    @Test
    public void getMeetingInvitationTestErrorCase() throws JSONException {
        Model model = new ConcurrentModel(); // get the current model
        when(zoomAPICallService.getInvitation("123", model)).thenReturn(201); // set up mock
        when(zoomAPICallService.getMeetingDetails("123", model)).thenReturn(200); // set up mock
        //201 indicate error

        assertEquals("redirect:/error",zoomController.getMeetingInvitation("123",model));
    }

}
