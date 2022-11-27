package com.aishatmoshood.facebookclone.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private LocalDateTime createdAt;

    @ManyToOne(cascade={PERSIST, DETACH}, fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(cascade={PERSIST, DETACH}, fetch = FetchType.LAZY)
    private User user;
}

