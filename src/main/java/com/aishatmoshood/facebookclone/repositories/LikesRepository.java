package com.aishatmoshood.facebookclone.repositories;

import com.aishatmoshood.facebookclone.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
