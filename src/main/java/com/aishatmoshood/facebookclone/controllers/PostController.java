package com.aishatmoshood.facebookclone.controllers;

import com.aishatmoshood.facebookclone.dto.postdto.CreatePostDto;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create/post")
    public String createPost(@ModelAttribute CreatePostDto createPostDto ,Model model) throws EmailNotValidException {
        postService.createPost(createPostDto);
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts",allPosts);
        return "redirect:/feed";
    }

    @GetMapping("/post/update/{id}")
    public String updatePostForm(@PathVariable Long id, Model model){
        Post post= postService.getPostById(id);
        model.addAttribute("postById",post);
        return "editpost";
    }

    @PostMapping("/post/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute("postById") Post post, Model model){
            postService.updatePost(id, post);
            model.addAttribute("deleteSuccess","Post edited successfully");
            return "redirect:/feed";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id, Model model){
        postService.deletePost(id);
        model.addAttribute("deleteSuccess","Post deleted successfully");
        return "redirect:/feed";
    }

    @GetMapping("/user/all/posts")
    public String allUserPosts(@PathVariable Long id, Model model){
        return "all_user_posts";
    }

    @GetMapping("/user/all/posts/{id}")
    public String showAllUserPosts(@PathVariable Long id, Model model, User user){
        postService.getUserPosts(user);
        return "redirect:/user/all/posts";
    }
}
