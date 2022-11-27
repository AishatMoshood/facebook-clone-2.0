package com.aishatmoshood.facebookclone.dto.likesdto;

import com.aishatmoshood.facebookclone.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeAPostDto {
    private long noOfLikes;
    private User user;
    private Long postId;
}
