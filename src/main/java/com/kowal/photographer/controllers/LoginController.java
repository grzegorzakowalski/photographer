package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private final ConfigurationService configurationService;

    public LoginController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("siteColor", configurationService.getStringSiteColor());
        return "login";
    }


}
