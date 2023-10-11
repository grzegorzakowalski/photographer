package com.kowal.photographer.controllers;

import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping
    public String homePageView(Model model, @RequestParam(name = "added", required = false) Boolean added){
        model.addAttribute("added", added);
        return "home";
    }
}
