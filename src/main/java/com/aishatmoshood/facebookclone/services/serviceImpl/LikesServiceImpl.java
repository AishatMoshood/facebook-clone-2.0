package com.aishatmoshood.facebookclone.services.serviceImpl;

import com.aishatmoshood.facebookclone.dto.likesdto.LikeAPostDto;
import com.aishatmoshood.facebookclone.entity.Likes;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.repositories.LikesRepository;
import com.aishatmoshood.facebookclone.repositories.PostRepository;
import com.aishatmoshood.facebookclone.repositories.UserRepository;
import com.aishatmoshood.facebookclone.services.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final HttpSession httpSession;

    @Override
    public User findLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("userId");
        User user = userRepository.findById(userId).get();
        return user;
    }

    @Override
    public Post findPostById(){
        Long postId = (Long) httpSession.getAttribute("postId");
        Post post = postRepository.findById(postId).get();
        return post;
    }

    @Override
    public Likes LikeAPost(LikeAPostDto likeAPostDto){
        Likes likes = new Likes();

        likes.setNoOfLikes(likeAPostDto.getNoOfLikes());
        likes.setUser(findLoggedInUser());
        likes.setPost(findPostById());
        likesRepository.save(likes);

        return likes;
    }
}
