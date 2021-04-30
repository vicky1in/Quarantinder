package com.groun24.quarantinder.Modal;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
// import java.util.Set;

@Entity
@Table(name = "message")
public class OutputMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;
    @Column
    private String sender;
    @Column
    private String receiver;
    @Column
    private String dateTimeSent;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return sender;
    }

    public String getTo() {
        return receiver;
    }

    public String getDateTimeSent(){
        return dateTimeSent;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    public void setDateTimeSent(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        this.dateTimeSent = dtf.format(now).toString();
    }


}
