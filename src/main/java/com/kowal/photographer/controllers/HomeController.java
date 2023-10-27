package com.kowal.photographer.controllers;

import com.kowal.photographer.pojo.PageSettings;
import com.kowal.photographer.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ConfigurationService configurationService;


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
        PageSettings pageSettings = configurationService.getPageSettings();
        model.addAttribute("siteColor", pageSettings.getSiteColor());
        pageSettings.setSiteColor(configurationService.getSiteColorAsName());
        model.addAttribute("pageSettings", pageSettings);
        model.addAttribute("colorMap", ConfigurationService.getCOLOR_MAP());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","aboutMe");
        return "page-settings";
    }

    @PostMapping("/page-settings")
    public String pageSettings(PageSettings pageSettings){
        configurationService.savePageSettings(pageSettings);
        return "redirect:/page-settings";
    }
}
