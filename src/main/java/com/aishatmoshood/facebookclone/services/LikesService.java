package com.aishatmoshood.facebookclone.services;

import com.aishatmoshood.facebookclone.dto.likesdto.LikeAPostDto;
import com.aishatmoshood.facebookclone.entity.Likes;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;

public interface LikesService {
    User findLoggedInUser();
    Post findPostById();
    Likes LikeAPost(LikeAPostDto likeAPostDto);
}
