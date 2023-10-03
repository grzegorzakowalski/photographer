package com.kowal.photographer.controllers;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registry")
    public String registrationView(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registry")
    public String processRegistration(User user){
        user.setActive(true);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        //TODO dodaj kodowanie hasła po konfiguracji spring security
        return "/login";
    }

}
