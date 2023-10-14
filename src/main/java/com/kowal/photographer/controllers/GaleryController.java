package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import com.kowal.photographer.services.GaleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GaleryController {
    private final GaleryService galeryService;
    private final ConfigurationService configurationService;

    public GaleryController(GaleryService galeryService, ConfigurationService configurationService) {
        this.galeryService = galeryService;
        this.configurationService = configurationService;
    }

    @GetMapping("/galery")
    public String galeryView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","galery");
        model.addAttribute("imgList", galeryService.FindAllAndSplitInHalf());
        return "galery";
    }
}
