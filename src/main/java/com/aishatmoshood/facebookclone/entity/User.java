package com.aishatmoshood.facebookclone.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;
    private String gender;
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy ="user", cascade = {PERSIST, DETACH})
//    @JoinColumn(name="user_id", referencedColumnName = "id")
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade={PERSIST, DETACH})
    private List<Comment> userComments;

    @OneToMany(mappedBy = "user", cascade={PERSIST, DETACH})
    private List<Likes> userLikes;
}
