package com.example.msusers.service;

import com.example.msusers.domain.User;
import com.example.msusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return this.userRepository.findAllUsers();
    }

    public Optional<User> getUserById(String id){
        return this.userRepository.findUserById(id);
    }
}
