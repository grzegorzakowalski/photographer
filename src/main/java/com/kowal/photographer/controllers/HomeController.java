package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final ConfigurationService configurationService;

    public HomeController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public String homePageView(Model model, @RequestParam(name = "added", required = false) Boolean added){
        configurationService.checkConfigurationIfEmptyCreate();
        model.addAttribute("added", added);
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","home");
        return "home";
    }
}
