package com.kowal.photographer.controllers;

import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public String homePageView(Model model, HttpSession session){
        model.addAttribute("user",userRepository.getWithPicturesByUsername("q"));
        return "home";
    }
}
