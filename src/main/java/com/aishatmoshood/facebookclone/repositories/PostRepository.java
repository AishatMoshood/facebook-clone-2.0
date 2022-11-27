package com.aishatmoshood.facebookclone.repositories;

import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);
}
