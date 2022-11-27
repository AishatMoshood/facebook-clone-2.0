package com.aishatmoshood.facebookclone.services.serviceImpl;

import com.aishatmoshood.facebookclone.dto.userdto.UserResponseDto;
import com.aishatmoshood.facebookclone.dto.userdto.UserSignupDto;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.exceptions.UserAlreadyExistsException;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.repositories.UserRepository;
import com.aishatmoshood.facebookclone.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public UserServiceImpl(UserRepository userRepository, HttpSession httpSession) {
        super();
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @Override
    public boolean isEmailValid(String email){
        boolean emailValidStatus = false;
        if(email != null && email.contains("@") && email.contains(".com"))
            emailValidStatus = true;

        return emailValidStatus;
    }

    @Override
    public boolean isEmailExist(String email){
        boolean userExistStatus = false;

        User newUser = userRepository.findUserByEmail(email);

        if(newUser != null)
            userExistStatus = true;

        return userExistStatus;
    }

    @Override
    public User signUp(UserSignupDto userSignupDto) throws UserAlreadyExistsException, EmailNotValidException {
        boolean emailValidStatus = isEmailValid(userSignupDto.getEmail());
        boolean userExistStatus =  isEmailExist(userSignupDto.getEmail());

        if(emailValidStatus == true){
            if(userExistStatus == false){
                if(userSignupDto.getFirstName() != "" || userSignupDto.getLastName()!="" || userSignupDto.getPassword()!="" || userSignupDto.getDob()!=""){
                    User user = new User();

                    user.setFirstName(userSignupDto.getFirstName());

                    user.setLastName(userSignupDto.getLastName());
                    user.setEmail(userSignupDto.getEmail());
                    user.setPassword(userSignupDto.getPassword());
                    user.setDob(userSignupDto.getDob());
                    user.setGender(userSignupDto.getGender());
                    user.setDateCreated(LocalDateTime.now());
                    return userRepository.save(user);
                } else {
                    throw new EmailNotValidException("You're missing one of the required inputs");
                }

            } else {
                throw new UserAlreadyExistsException("Signup failed as user already exists");
            }
        } else {
            throw new EmailNotValidException("Email supplied is not valid");
        }
    }

    @Override
    public UserResponseDto login(String email, String password) throws EmailNotValidException {
       if(email == null || password == null){
           System.out.println("No email or password provided");
           throw new EmailNotValidException("Email or password not provided");
       } else {
           Optional<User> user = Optional.ofNullable(userRepository.findByEmailAndPassword(email, password).orElse(null));

           UserResponseDto userResponseDto = new UserResponseDto();
           userResponseDto.setId(user.get().getId());
           userResponseDto.setFirstName(user.get().getFirstName());
           userResponseDto.setLastName(user.get().getLastName());
           userResponseDto.setEmail(user.get().getEmail());
           userResponseDto.setDob(user.get().getDob());
           userResponseDto.setGender(user.get().getGender());

           httpSession.setAttribute("userId", userResponseDto.getId());
           return userResponseDto;
       }
    }

    @Override
    public List<User> getAllContacts() {
        Long userId = (Long) httpSession.getAttribute("userId");
        return userRepository.findUsersByIdIsNot(userId);
    }
}
