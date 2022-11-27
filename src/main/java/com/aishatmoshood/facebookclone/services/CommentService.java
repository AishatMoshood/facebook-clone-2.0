package com.aishatmoshood.facebookclone.services;

import com.aishatmoshood.facebookclone.dto.commentdto.CreateCommentDto;
import com.aishatmoshood.facebookclone.entity.Comment;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;

import java.util.List;

public interface CommentService {
    User findLoggedInUser();
    Comment createComment(Long id, CreateCommentDto createCommentDto) throws EmailNotValidException;

    List<Comment> findCommentsByPostId(Long postId);

    Comment getCommentById(Long commentId);

    Comment updateComment(Long commentId, Comment newComment);

    void deleteComment(Long commentId);
}
