package com.example.bitfinex.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDto {

    private String client;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private Date registeredAt;

    public UserInfoDto() {
    }

    public UserInfoDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserInfoDto(String firstName, String lastName, String email, Date registeredAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredAt = registeredAt;
    }

    public UserInfoDto(String client, String name, String firstName, String lastName, String email) {
        this.client = client;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
