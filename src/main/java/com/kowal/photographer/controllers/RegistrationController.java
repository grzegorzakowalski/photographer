package com.kowal.photographer.controllers;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registry")
    public String registrationView(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registry")
    public String processRegistration(User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        if(userRepository.findUserByUsername(user.getUsername()) != null){
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        //TODO dodaj kodowanie hasła po konfiguracji spring security
        return "/login";
    }

}
