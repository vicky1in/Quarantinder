package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.Message;
import com.groun24.quarantinder.Modal.OutputMessage;

import com.groun24.quarantinder.dao.MessageDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageDAOImpl messageDAO;

    public List<OutputMessage> getAllFromUserMap(String receiver, String sender){
        return messageDAO.getAll(receiver, sender);
    }

    public void save(OutputMessage message){
        messageDAO.save(message);
    }

    public OutputMessage convertFromMessageToOutputMessage(Message message){
        OutputMessage converted = new OutputMessage();
        converted.setSender(message.getFrom());
        converted.setReceiver(message.getTo());
        converted.setMessage(message.getMessage());
        converted.setDateTimeSent();
        return converted;
    }
}
