package com.groun24.quarantinder.Controller;

import com.groun24.quarantinder.Modal.ZoomSchedule;
import com.groun24.quarantinder.Services.ZoomAPICallService;
import com.groun24.quarantinder.Services.ZoomAuthCallService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

@Controller
public class ZoomController {

    @Autowired
    private ZoomAuthCallService zoomAuthCallService;

    @Autowired
    private ZoomAPICallService zoomAPICallService;

    @RequestMapping(value="/zoom",method = RequestMethod.GET)
    public String redirectToAuth() {
        UriComponents uriComponents = zoomAuthCallService.getAuthRedirectionURI();
        return "redirect:" + uriComponents.toUriString();
    }

    @RequestMapping(value = "/zoom/auth", method = RequestMethod.GET)
    public String getAuthenticationCode(@RequestParam String code, Model model) throws JSONException{
        int statusCode = zoomAuthCallService.getAccessToken(code);
        model.addAttribute("optionalAuthenticationMessage","Congrats! Authentication Successful!");
        return "zoomauth";
    }

    @RequestMapping(value= "/zoom/schedule", method = RequestMethod.GET)
    public String schedule(Model model) {
        model.addAttribute("scheduleForm", new ZoomSchedule());
        return "schedulemeeting";
    }


    @RequestMapping(value= "/zoom/schedule", method = RequestMethod.POST)
    public String processSchedule(@ModelAttribute("scheduleForm") ZoomSchedule scheduleForm, Model model, BindingResult bindingResult) throws JSONException {
        if (bindingResult.hasErrors()) {
            return "redirect: /error";
        }
        int code = zoomAPICallService.scheduleMeeting(scheduleForm, model);
        if (code != 201) {
            return "redirect:/error";
        }
        return "meetingdisplay";
    }


    @RequestMapping(value= "/zoom/meetings", method = RequestMethod.GET)
    public String listMeetings(Model model) throws JSONException {
        int code = zoomAPICallService.listMeetings(model);
        if (code != 200) {
            return "redirect:/error";
        }
        return "listmeeting";
    }


    @RequestMapping(value= "/zoom/delete/{meetingId}")
    public String deleteMeeting(@PathVariable String meetingId) {
        int code = zoomAPICallService.deleteMeeting(meetingId);
        if (code != 204) {
            return "redirect:/error";
        }
        return "redirect:/zoom/meetings";
    }


    @RequestMapping(value= "/zoom/detail/{meetingId}",  method = RequestMethod.GET)
    public String getMeetingInvitation(@PathVariable String meetingId, Model model) throws JSONException {
        int statusCode = zoomAPICallService.getInvitation(meetingId, model);
        int detailStatusCode = zoomAPICallService.getMeetingDetails(meetingId, model);
        if (!(detailStatusCode == 200 && statusCode == 200)) {
            return "redirect:/error";
        }
        return "meetingdisplay";
    }

}
