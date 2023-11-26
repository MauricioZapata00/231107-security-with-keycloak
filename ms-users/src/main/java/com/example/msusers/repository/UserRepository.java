package com.example.msusers.repository;

import com.example.msusers.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository {
    List<User> findAllUsers();

    Optional<User> findUserById(String id);
}
