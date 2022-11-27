package com.aishatmoshood.facebookclone.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postTitle;
    private String postBody;
    private LocalDateTime createdAt;

    @ManyToOne(cascade={PERSIST, DETACH}, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Comment> postComments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Likes> postLikes;
}
