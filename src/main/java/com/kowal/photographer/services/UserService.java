package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Pictures;
import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User getTempUser(){
        User user = new User();
        user.setFirstName("Anon");
        user.setRole("ROLE_TEMP");
        user.setActive(false);
        user.setPassword(passwordEncoder.encode("yolo"));
        return user;
    }

    public List<User> getAdminListWithoutPicture(Pictures picture){
        List<User> roleAdmin = userRepository.findAllByRole("ROLE_ADMIN");
        return roleAdmin.stream()
                .filter(admin -> !admin.getPictures().contains(picture))
                .toList();
    }
}
