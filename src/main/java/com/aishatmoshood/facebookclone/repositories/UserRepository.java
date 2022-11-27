package com.aishatmoshood.facebookclone.repositories;

import com.aishatmoshood.facebookclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
    List<User> findUsersByIdIsNot(Long id);

}
