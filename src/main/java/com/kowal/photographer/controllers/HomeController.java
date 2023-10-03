package com.kowal.photographer.controllers;

import com.kowal.photographer.repositorys.ConfigRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    @GetMapping
    public String homePageView(Model model, HttpSession session){
        return "home";
    }
}
