package com.aishatmoshood.facebookclone.controllers;

import com.aishatmoshood.facebookclone.dto.userdto.UserResponseDto;
import com.aishatmoshood.facebookclone.dto.userdto.UserSignupDto;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.exceptions.UserAlreadyExistsException;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.repositories.UserRepository;
import com.aishatmoshood.facebookclone.services.PostService;
import com.aishatmoshood.facebookclone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserResponseDto());
        model.addAttribute("userSignUpDto", new UserSignupDto());
        return "index";
    }

    @GetMapping("/feed")
    public String getFeedPage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        } else {
            model.addAttribute("feedRequest", new UserResponseDto());

            List<Post> allPosts = postService.getAllPosts();
            model.addAttribute("allPosts",allPosts);

            Long userId = (Long) httpSession.getAttribute("userId");
            User verifiedUser = userRepository.findById(userId).get();

            model.addAttribute("loggedInUserName", verifiedUser.getFirstName());
            model.addAttribute("loggedInUserId", verifiedUser.getId());

            List<User> contacts = userService.getAllContacts();
            model.addAttribute("allContacts", contacts);
            return "feed";
        }
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") UserSignupDto userSignupDto, Model model) throws EmailNotValidException, UserAlreadyExistsException {
        model.addAttribute("success", "Signup Successful, please login below");
        userService.signUp(userSignupDto);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) throws EmailNotValidException {
        UserResponseDto authenticated = userService.login(user.getEmail(), user.getPassword());
        if (authenticated != null) {
            System.out.println("Login Successful");

            return "redirect:/feed";
        } else {
            System.out.println("Invalid login credentials");
            return "index";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}