package com.kowal.photographer.controllers;

import com.kowal.photographer.repositorys.UserRepository;
import com.kowal.photographer.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {
    private final UserRepository userRepository;

    public PanelController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getPanelView(@AuthenticationPrincipal UserDetails userDetails,
                               Model model){
        model.addAttribute("user", userRepository.findUserByUsername(userDetails.getUsername()));
        return "panel";
    }
}
