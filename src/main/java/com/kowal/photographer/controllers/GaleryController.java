package com.kowal.photographer.controllers;

import com.kowal.photographer.entitys.Galery;
import com.kowal.photographer.services.GaleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GaleryController {
    private final GaleryService galeryService;

    public GaleryController(GaleryService galeryService) {
        this.galeryService = galeryService;
    }

    @GetMapping("/galery")
    public String galeryView(Model model){
        model.addAttribute("imgList", galeryService.FindAllAndSplitInHalf());
        return "galery";
    }
}
