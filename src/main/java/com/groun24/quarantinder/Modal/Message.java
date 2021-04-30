package com.groun24.quarantinder.Modal;

// import javax.persistence.*;
// import java.util.Set;

// @Entity
// @Table(name = "messages")
public class Message {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    // @Column
    // private String message;
    // @Column
    // private String from;
    // @Column
    // private String to;
    private String to;
    private String from;
    private Long id;
    private String message;

    public Message(String message, String from, String to){
        this.message = message;
        this.from = from;
        this.to= to;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


}
