package com.groun24.quarantinder.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int blockID;
    
    @ManyToOne
    @JoinColumn(name = "Blocker", referencedColumnName="UserID")
    private User blocker;

    @ManyToOne
    @JoinColumn(name = "Blockee", referencedColumnName="UserID")
    private User blockee;

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public User getBlocker() {
        return blocker;
    }

    public void setBlocker(User blocker) {
        this.blocker = blocker;
    }

    public User getBlockee() {
        return blockee;
    }

    public void setBlockee(User blockee) {
        this.blockee = blockee;
    }
}
