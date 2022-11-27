package com.aishatmoshood.facebookclone.dto.userdto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserSignupDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;
    private String gender;
   // private LocalDate dateCreated;
}
