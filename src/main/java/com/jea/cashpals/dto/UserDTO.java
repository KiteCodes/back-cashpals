package com.jea.cashpals.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
