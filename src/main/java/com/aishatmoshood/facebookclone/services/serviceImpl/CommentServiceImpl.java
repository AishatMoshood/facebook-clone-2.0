package com.aishatmoshood.facebookclone.services.serviceImpl;

import com.aishatmoshood.facebookclone.dto.commentdto.CreateCommentDto;
import com.aishatmoshood.facebookclone.entity.Comment;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.repositories.CommentRepository;
import com.aishatmoshood.facebookclone.repositories.PostRepository;
import com.aishatmoshood.facebookclone.repositories.UserRepository;
import com.aishatmoshood.facebookclone.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRespository;
    private final PostRepository postRepository;
    private final HttpSession httpSession;

    @Override
    public User findLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("userId");
        User user = userRespository.findById(userId).get();
        return user;
    }

    @Override
    public Comment createComment(Long id, CreateCommentDto createCommentDto) throws EmailNotValidException {
        if(createCommentDto.getComment() != ""){

            Comment comment = new Comment();
            comment.setComment(createCommentDto.getComment());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUser(findLoggedInUser());
            comment.setPost(postRepository.findById(id).get());

            return commentRepository.save(comment);
        } else {
            throw new EmailNotValidException("Comment cannot be empty");
        }
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findAllByPostId(postId);
    }


    @Override
    public Comment getCommentById(Long commentId){
        Comment commentUpdate = commentRepository.findById(commentId).get();
        return commentRepository.save(commentUpdate);
    }

    @Override
    public Comment updateComment(Long commentId, Comment newComment){
        Comment commentUpdate = getCommentById(commentId);
        commentUpdate.setId(commentId);
        commentUpdate.setComment(newComment.getComment());

        return commentRepository.save(commentUpdate);
    }

    @Override
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
