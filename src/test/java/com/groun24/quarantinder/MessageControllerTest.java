package com.groun24.quarantinder;

import com.groun24.quarantinder.Controller.MessagingController;
import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.Message;
import com.groun24.quarantinder.Modal.OutputMessage;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.MatchManager;

import com.groun24.quarantinder.Services.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@SpringBootTest
public class MessageControllerTest {
    @Autowired
    private MessagingController messagecontroller;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchManager matchManager;

    @Test
    public void Test_NormalUseCase_oneMessage() throws Exception {
        assertThat(messagecontroller).isNotNull();
        assertThat(userService).isNotNull();
        userService.createDummy("Bryn", "bryn", "bryn@gmail.com", "bryn", Date.valueOf("2000-11-01"), "Male", "Female",
                18, 34, "612323");
        User bryn = userService.findByUsername("Bryn");
        assertEquals(bryn.getName(), "Bryn");

        userService.createDummy("SomeGrilName", "gril", "asdn@gmail.com", "gril", Date.valueOf("2000-11-01"), "Female",
                "Male", 18, 35, "694220");
        User grill = userService.findByUsername("gril");
        assertEquals(grill.getName(), "SomeGrilName");

        assertEquals(matchManager.get(bryn, grill), null);
        Match loveAtFirstSight = new Match();
        loveAtFirstSight.setUserA(bryn);
        loveAtFirstSight.setUserB(grill);
        loveAtFirstSight.setAccepted(true);

        matchManager.save(loveAtFirstSight);
        assertThat(new ReflectionEquals(loveAtFirstSight).matches(matchManager.get(bryn, grill)));

        // This is all setup. Now we actually test the messaging :D
        // This would be done on the front end.
        Message firstMessage = new Message("hey", grill.getUsername(), bryn.getUsername());
        messagecontroller.send(firstMessage);

        Principal mockprincipal = mock(Principal.class);
        User sender = grill;
        when(mockprincipal.getName()).thenReturn(sender.getUsername());
        String receiverId = bryn.getUsername();
        Model mod = new ConcurrentModel();
        String pageRedirect = messagecontroller.registration(mod, mockprincipal, receiverId);

        // These two people are matched hence the messaging page should be returned.
        assertEquals(pageRedirect, "messaging", "Went to home page instead of messaging");
        assertNotEquals(mod.getAttribute("messages"), null, "Doesnt have any messages, But went to the messaging page");

        List<OutputMessage> previousMessages = (List<OutputMessage>) (mod.getAttribute("messages"));

        assertEquals(previousMessages.size(), 1);
        assertEquals(previousMessages.get(0).getMessage(), "hey");
        assertEquals(previousMessages.get(0).getFrom(), sender.getUsername());
        assertEquals(previousMessages.get(0).getTo(), receiverId);
    }

    @Test
    public void Test_NonMatchedMessageAttempt() throws Exception {
        assertThat(messagecontroller).isNotNull();
        assertThat(userService).isNotNull();
        userService.createDummy("Beta", "beta", "asdasdil.com", "asd", Date.valueOf("2000-11-01"), "Male", "Female", 18,
                34, "612323");
        User unmatcheduser = userService.findByUsername("beta");
        assertEquals(unmatcheduser.getName(), "Beta");

        userService.createDummy("lady", "lady", "asdns@gmail.com", "lady", Date.valueOf("2000-11-01"), "Female", "Male",
                18, 35, "694220");
        User grill = userService.findByUsername("lady");
        assertEquals(grill.getName(), "lady");

        assertEquals(matchManager.get(unmatcheduser, grill), null);

        // Dont match the users

        // This is all setup. Now we actually test the messaging :D
        // This would be done on the front end.
        Message firstMessage = new Message("hey", grill.getUsername(), unmatcheduser.getUsername());
        messagecontroller.send(firstMessage);

        Principal mockprincipal = mock(Principal.class);
        User sender = grill;
        when(mockprincipal.getName()).thenReturn(sender.getUsername());
        String receiverId = unmatcheduser.getUsername();
        Model mod = new ConcurrentModel();
        String pageRedirect = messagecontroller.registration(mod, mockprincipal, receiverId);

        // These two people are NOT matched hence the home page should be returned.
        assertEquals(pageRedirect, "welcome", "Went to messaging page instead of home");
        assertEquals(mod.getAttribute("error"), "You are not matched to this user and cannot message");
    }

    @Test
    public void Test_NormalUseCaseNoMessagesYet() throws Exception {
        assertThat(messagecontroller).isNotNull();
        assertThat(userService).isNotNull();
        userService.createDummy("George", "George", "George@gmail.com", "George", Date.valueOf("2000-11-01"), "Male",
                "Female", 18, 34, "612323");
        User George = userService.findByUsername("George");
        assertEquals(George.getName(), "George");

        userService.createDummy("Stephanie", "Stephanie", "Stephanie@gmail.com", "Stephanie",
                Date.valueOf("2000-11-01"), "Female", "Male", 18, 35, "694220");
        User grill = userService.findByUsername("Stephanie");
        assertEquals(grill.getName(), "Stephanie");

        assertEquals(matchManager.get(George, grill), null);
        Match loveAtFirstSight = new Match();
        loveAtFirstSight.setUserA(George);
        loveAtFirstSight.setUserB(grill);
        loveAtFirstSight.setAccepted(true);

        matchManager.save(loveAtFirstSight);
        // assertEquals(matchManager.get(bryn, grill), loveAtFirstSight);
        assertThat(new ReflectionEquals(loveAtFirstSight).matches(matchManager.get(George, grill)));

        // This is all setup. Now we actually test the messaging :D
        // This would be done on the front end.

        Principal mockprincipal = mock(Principal.class);
        User sender = grill;
        when(mockprincipal.getName()).thenReturn(sender.getUsername());
        String receiverId = George.getUsername();
        Model mod = new ConcurrentModel();
        String pageRedirect = messagecontroller.registration(mod, mockprincipal, receiverId);

        // These two people are matched hence the messaging page should be returned.
        // However no messages have been sent between them, so it wont return any
        // additional messages included
        assertEquals(pageRedirect, "messaging", "Went to home page instead of messaging");
        assertEquals(mod.getAttribute("messages"), null, "Has messages, when expecting none");
    }

}
