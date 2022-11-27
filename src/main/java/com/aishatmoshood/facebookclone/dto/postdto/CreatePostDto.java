package com.aishatmoshood.facebookclone.dto.postdto;

import com.aishatmoshood.facebookclone.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreatePostDto {
    private String postTitle;
    private String postBody;
    private LocalDate createdAt;
    private User user;
}
