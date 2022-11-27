package com.aishatmoshood.facebookclone.dto.userdto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}
