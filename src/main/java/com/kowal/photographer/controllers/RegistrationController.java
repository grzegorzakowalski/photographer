package com.kowal.photographer.controllers;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@Slf4j
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Validator validator;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @GetMapping("/registry")
    public String registrationView(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registry")
    public String processRegistration(User user, Model model){
        user.setActive(true);
        user.setRole("ROLE_USER");
        Set<ConstraintViolation<User>> validated = validator.validate(user);
        if( !validated.isEmpty()){
            model.addAttribute("validated", validated);
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "/login";
    }

}
