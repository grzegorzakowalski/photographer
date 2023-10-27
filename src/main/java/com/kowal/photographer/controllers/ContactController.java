package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ConfigurationService configurationService;

    @GetMapping
    public String contactView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","contact");
        model.addAttribute("pageSettings", configurationService.getPageSettings());
        return "contact";
    }
}
