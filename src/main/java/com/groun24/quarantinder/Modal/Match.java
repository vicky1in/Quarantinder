package com.groun24.quarantinder.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Match_") 
public class Match {

    // public enum Status {
    //     PENDING,
    //     ACCEPTED,
    //     DECLINED,
    //     DATING
    // }

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int matchID;
    
    @ManyToOne
    @JoinColumn(name = "UserA", referencedColumnName="UserID")
    @NotNull
    private User userA;

    @ManyToOne
    @JoinColumn(name = "UserB", referencedColumnName="UserID")
    @NotNull
    private User userB;
    
    @Column
    private boolean accepted; 

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public User getUserB() {
        return userB;
    }

    public void setUserB(User userB) {
        this.userB = userB;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
    
    
