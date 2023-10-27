package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import com.kowal.photographer.services.GaleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GaleryController {
    private final GaleryService galeryService;
    private final ConfigurationService configurationService;

    @GetMapping("/galery")
    public String galeryView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","home");
        model.addAttribute("footerIsActive","galery");
        model.addAttribute("imgList", galeryService.FindAllAndSplitInHalf());
        return "galery";
    }
}
