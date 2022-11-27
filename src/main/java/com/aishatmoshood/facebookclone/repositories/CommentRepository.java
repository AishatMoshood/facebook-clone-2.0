package com.aishatmoshood.facebookclone.repositories;

import com.aishatmoshood.facebookclone.entity.Comment;
import com.aishatmoshood.facebookclone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);
}
