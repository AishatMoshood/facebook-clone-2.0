package com.aishatmoshood.facebookclone.services.serviceImpl;

import com.aishatmoshood.facebookclone.dto.postdto.CreatePostDto;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.repositories.PostRepository;
import com.aishatmoshood.facebookclone.repositories.UserRepository;
import com.aishatmoshood.facebookclone.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public User findLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("userId");
        User user = userRepository.findById(userId).get();
        return user;
    }

    @Override
    public Post createPost(CreatePostDto createPost) throws EmailNotValidException {
        if(createPost.getPostTitle() !="" || createPost.getPostBody()!=""){
            Post post = new Post();

            post.setPostTitle(createPost.getPostTitle());
            post.setPostBody(createPost.getPostBody());
            post.setCreatedAt(LocalDateTime.now());
            post.setUser(findLoggedInUser());

            Post post1 = postRepository.save(post);
            //httpSession.setAttribute("postId", post1.getId());
            return post1;
        } else {
            throw new EmailNotValidException("You're missing one of the required inputs to create a post");
        }
    }

    @Override
    public List<Post> getAllPosts(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return postRepository.findAll(sort);
    }

    @Override
    public Post getPostById(Long id){
        Post postUpdate = postRepository.findById(id).get();
        return postRepository.save(postUpdate);
    }

    @Override
    public Post updatePost(Long postId, Post newPost){
        Post postUpdate = getPostById(postId);
        postUpdate.setId(postId);
        postUpdate.setPostTitle(newPost.getPostTitle());
        postUpdate.setPostBody(newPost.getPostBody());

        return postRepository.save(postUpdate);
    }

    @Override
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getUserPosts(User user) {
      return postRepository.findAllByUser(user);
    }

//    @Override
//    public Long getUserPosts(User user, Long id) {
//        List<Post> contactPosts = postRepository.findAllByUser(user);
//        return user.getId();
//    }

}
