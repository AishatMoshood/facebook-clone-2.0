package com.aishatmoshood.facebookclone.dto.commentdto;

import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCommentDto {
    private String comment;
    private LocalDateTime created_at;
    private Long postId;
    private User user;
}
