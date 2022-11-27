package com.aishatmoshood.facebookclone.services;

import com.aishatmoshood.facebookclone.dto.userdto.UserResponseDto;
import com.aishatmoshood.facebookclone.dto.userdto.UserSignupDto;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.exceptions.UserAlreadyExistsException;
import com.aishatmoshood.facebookclone.entity.User;

import java.util.List;

public interface UserService {
    boolean isEmailValid(String email);
    boolean isEmailExist(String email);
    User signUp(UserSignupDto userSignupDto) throws UserAlreadyExistsException, EmailNotValidException;
    UserResponseDto login(String email, String password) throws EmailNotValidException;
    List<User> getAllContacts();
}
