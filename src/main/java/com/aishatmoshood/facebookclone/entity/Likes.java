package com.aishatmoshood.facebookclone.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long noOfLikes;
    @ManyToOne(cascade={PERSIST, DETACH}, fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(cascade={PERSIST, DETACH}, fetch = FetchType.LAZY)
    private Post post;

}

