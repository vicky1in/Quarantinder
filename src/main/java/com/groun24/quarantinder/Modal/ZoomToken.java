package com.groun24.quarantinder.Modal;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class ZoomToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int tokenId;

    @Column
    @Type(type = "text")
    private String accessToken;

    public void setId(int id) {
        this.tokenId = id;
    }

    public int getId() {
        return this.tokenId;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

}
