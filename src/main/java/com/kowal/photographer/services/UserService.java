package com.kowal.photographer.services;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User getTempUser(){
        User user = new User();
//        user.setUsername("Anon" + userRepository.findMaxId());
        user.setRole("ROLE_TEMP");
        user.setActive(false);
        user.setPassword(passwordEncoder.encode("yolo"));
        return user;
    }
}
