package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.*;

public interface MessageDAO {
    // OutputMessage get(int recipientId, int senderId);
    void save(OutputMessage message);
    List<OutputMessage> getAll(String recipientId, String senderId);
}
