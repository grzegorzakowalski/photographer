package com.kowal.photographer.services;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getTempUser(){
        User user = new User();
        user.setRole("ROLE_TEMP");
        user.setActive(false);
        user.setPassword("yolo");
        return user;
    }
}
