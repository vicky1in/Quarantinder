package com.groun24.quarantinder;

import com.groun24.quarantinder.dao.MessageDAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.groun24.quarantinder.Controller.MessagingController;

import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.Message;
import com.groun24.quarantinder.Modal.OutputMessage;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.MatchManager;

import com.groun24.quarantinder.Services.UserService;

import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

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
public class MessageDAOTest {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    private MessagingController messagecontroller;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchManager matchManager;


    @Test
    public void getAndSetMultupleMessages() throws Exception {
        assertThat(messagecontroller).isNotNull();
        assertThat(userService).isNotNull();
        userService.createDummy("dyla", "dyla", "dyla@gmail.com", "dyla", Date.valueOf("2000-11-01"), "Male", "Female",
                18, 34, "612324");
        User dylan = userService.findByUsername("dyla");
        assertEquals(dylan.getName(), "dyla");

        userService.createDummy("grill3", "youknow", "as@om", "grila", Date.valueOf("2000-11-01"), "Female", "Male", 18,
                35, "694222");
        User grill = userService.findByUsername("youknow");
        assertEquals(grill.getName(), "grill3");

        assertEquals(matchManager.get(dylan, grill), null);
        Match loveAtFirstSight = new Match();
        loveAtFirstSight.setUserA(dylan);
        loveAtFirstSight.setUserB(grill);
        loveAtFirstSight.setAccepted(true);

        matchManager.save(loveAtFirstSight);
        // assertEquals(matchManager.get(dylan, grill), loveAtFirstSight);
        assertThat(new ReflectionEquals(loveAtFirstSight).matches(matchManager.get(dylan, grill)));

        // This is all setup. Now we actually test the messaging :D
        // This is all setup. Now we actually test the messaging :D
        // This would be done on the front end.
        User sender = grill;
        String receiverId = dylan.getUsername();
        // Message DAO Test
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).size(), 2);
        OutputMessage outputMessage = new OutputMessage();
        outputMessage.setMessage("yo yo");
        outputMessage.setReceiver(sender.getUsername());
        outputMessage.setSender(receiverId);
        messageDAO.save(outputMessage);
        // assertEquals(messageDAO.getAll(dylan.getUsername(),
        // grill.getUsername()).get(0).getMessage(), "hey");
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).size(), 3);
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(2).getMessage(), "yo yo");
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(2).getTo(), sender.getUsername());
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(2).getFrom(), receiverId);
        OutputMessage responseMessage = new OutputMessage();
        responseMessage.setMessage("yerlop");
        responseMessage.setReceiver(receiverId);
        responseMessage.setSender(sender.getUsername());
        messageDAO.save(responseMessage);

        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).size(), 4);
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(2).getMessage(), "yo yo");
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(3).getMessage(), "yerlop");
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(3).getTo(), receiverId);
        assertEquals(messageDAO.getAll(dylan.getUsername(), grill.getUsername()).get(3).getFrom(),
                sender.getUsername());
    }

}
