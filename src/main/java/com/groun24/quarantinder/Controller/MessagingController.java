package com.groun24.quarantinder.Controller;


import java.security.Principal;
import java.util.List;

import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.Message;
import com.groun24.quarantinder.Modal.OutputMessage;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.MatchManager;
import com.groun24.quarantinder.Services.MessageService;
import com.groun24.quarantinder.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessagingController {

    @Autowired
    MessageService messageServices;

    @Autowired
    UserService userServices;

    @Autowired
    MatchManager matchService;

    @GetMapping("/messaging")
    public String registration(Model model, Principal principal, @RequestParam String id) {
        System.out.println("logged in as: " + principal.getName() + ", sending messages to: " + id);
        String to = id;
        String from = principal.getName();

        // Do a check if they are matched
        User toObject = userServices.findByUsername(to);
        User fromObject = userServices.findByUsername(from);

        if (toObject == null || fromObject == null) {
            System.out.println("One doesnt exist");
            model.addAttribute("user", fromObject);
            model.addAttribute("error", "User does not exist");
            return "welcome";
        } else {
            Match match = matchService.get(toObject, fromObject);
            if (match == null) {
                System.out.println("Both exist but no match");
                model.addAttribute("user", fromObject);
                model.addAttribute("error", "You are not matched to this user and cannot message");
                return "welcome";
            } else if (!match.getAccepted()) {
                // Awaiting them to match
                System.out.println("Both exist but no match");
                model.addAttribute("user", fromObject);
                model.addAttribute("error", "You are not matched to this user and cannot message");
                return "welcome";
            } else {

                // There is a match and they can message, hence load previous messages
                System.out.println(match.getMatchID());
                List<OutputMessage> previousMessages = messageServices.getAllFromUserMap(to, from);
                if (previousMessages.size() > 0) {

                    int i = 0;
                    while (i < previousMessages.size()) {

                        if ((previousMessages.get(i).getFrom().equals(from)
                                && previousMessages.get(i).getTo().equals(to))
                                || (previousMessages.get(i).getFrom().equals(to)
                                        && previousMessages.get(i).getTo().equals(from))) {
                            // if the message is from or to the person in the communications channel
                            // We need to increment if its not removed
                            i++;
                        } else {
                            previousMessages.remove(i);
                        }
                    }

                    model.addAttribute("messages", previousMessages);

                }

                model.addAttribute("to", to);
                model.addAttribute("from", from);
                model.addAttribute("toName", toObject.getName());

                return "messaging";

            }
        }
        // System.out.println(toObject.getId());
        //
        //
        // }

    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) throws Exception {
        System.out.println(message.getFrom() + ": " + message.getMessage());
        OutputMessage converted = messageServices.convertFromMessageToOutputMessage(message);
        messageServices.save(converted);
        return message;
    }

    @MessageMapping("/getAllChat")
    @SendTo("/topic/messages")
    public List<Message> getAllMessages(Message fromdetails) {
        // if (fromdetails.getMessage() == "initial Load"){
        // messageServices.getAllFromUserMap()
        // }
        return null;

    }

}
