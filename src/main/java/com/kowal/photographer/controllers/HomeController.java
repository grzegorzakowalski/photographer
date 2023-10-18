package com.kowal.photographer.controllers;

import com.kowal.photographer.PageSettings;
import com.kowal.photographer.services.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class HomeController {
    private final ConfigurationService configurationService;
    private PageSettings pageSettings;

    public HomeController(ConfigurationService configurationService, PageSettings pageSettings) {
        this.configurationService = configurationService;
        this.pageSettings = pageSettings;
    }

    @GetMapping
    public String homePageView(Model model){
        configurationService.checkConfigurationIfEmptyCreate();
        model.addAttribute("aboutMe",configurationService.getStringAboutMe());
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","home");
        return "home";
    }

    @GetMapping("/page-settings")
    public String pageSettingsView(Model model){
        model.addAttribute("pageSettings", pageSettings);
        model.addAttribute("siteColor", pageSettings.getColor());
        model.addAttribute("colorMap", configurationService.getColorMap());
        model.addAttribute("actualColorName", configurationService.getColorMap().entrySet().stream()
                .filter(entry -> pageSettings.getColor().equals(entry.getValue()))
                .findFirst()
                .orElse( Map.entry("lipa","nie działa")).getKey());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","aboutMe");
        return "page-settings";
    }

    @PostMapping("/page-settings")
    public String pageSettings(PageSettings pageSettings){
        pageSettings.save();
        return "redirect:/page-settings";

    }
}
