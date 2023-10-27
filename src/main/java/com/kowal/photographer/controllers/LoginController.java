package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ConfigurationService configurationService;


    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("siteColor", configurationService.getStringSiteColor());
        return "login";
    }


}
