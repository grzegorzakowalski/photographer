package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final ConfigurationService configurationService;

    public HomeController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
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
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("aboutMe",configurationService.getStringAboutMe());
        model.addAttribute("colorMap",configurationService.getColorMap());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","aboutMe");
        return "page-settings";
    }

    @PostMapping("/page-settings")
    public String pageSettings(@RequestParam(name = "aboutMe") String aboutMe,
                               @RequestParam(name = "color") String color){
        configurationService.setAboutMe(aboutMe == null? configurationService.getStringAboutMe() : aboutMe);
        String actualColor = configurationService.getStringSiteColor();
        configurationService.setSiteColor(color == null? configurationService.getColorMap().entrySet().stream()
                .filter(el -> el.getValue().equals(actualColor))
                .findFirst().get().getKey() : color); // Aktualny kolor musi być na tej mapie
        return "redirect:/page-settings";

    }
}
