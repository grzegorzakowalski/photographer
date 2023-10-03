package com.kowal.photographer.controllers;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginView(){
        return "login";
    }

    @PostMapping("/login") //TODO usuń po konfiguracji spring security
    public String processLogin(String username, String password){
        User user = userRepository.findUserByUsername(username);
        if( user == null){
            return "login";
        }
        if( !user.getPassword().equals(password)){
            return "login";
        }
        //obsługa zalogowania
        return "redirect:/";
    }

}
