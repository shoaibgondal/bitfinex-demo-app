package com.example.bitfinex.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class OauthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String oauthProvider;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date lastLoginAt;

public OauthUser() {
    }

    public OauthUser(String oauthProvider, String name, String firstName, String lastName, String email) {
        this.oauthProvider = oauthProvider;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}
