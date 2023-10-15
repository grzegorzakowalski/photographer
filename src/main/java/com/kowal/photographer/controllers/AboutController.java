package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {
    private final ConfigurationService configurationService;

    public AboutController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public String aboutView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","about");
        model.addAttribute("footerIsActive","about");
        return "about";
    }
}
