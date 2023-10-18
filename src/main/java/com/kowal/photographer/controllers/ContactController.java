package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final ConfigurationService configurationService;

    public ContactController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public String contactView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","contact");
        model.addAttribute("contactPhoneNumber",configurationService.getStringContactPhoneNumber());
        model.addAttribute("contactEmail",configurationService.getStringContactEmail());
        return "contact";
    }
}
