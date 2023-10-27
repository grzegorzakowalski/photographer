package com.kowal.photographer.controllers;

import com.kowal.photographer.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ConfigurationService configurationService;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("siteColor", configurationService.getStringSiteColor());
    }
}
