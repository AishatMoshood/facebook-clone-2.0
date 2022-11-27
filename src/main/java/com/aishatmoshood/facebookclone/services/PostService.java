package com.aishatmoshood.facebookclone.services;

import com.aishatmoshood.facebookclone.dto.postdto.CreatePostDto;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;

import java.util.List;

public interface PostService {
    User findLoggedInUser();
    Post createPost(CreatePostDto createPost) throws EmailNotValidException;
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post updatePost(Long postId, Post newPost);
    void deletePost(Long id);
    List<Post> getUserPosts(User user);
}
