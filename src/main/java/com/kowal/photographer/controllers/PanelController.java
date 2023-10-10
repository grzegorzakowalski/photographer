package com.kowal.photographer.controllers;

import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import com.kowal.photographer.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {
    private final TimetableRepository timetableRepository;
    public PanelController(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    @GetMapping
    public String getPanelView(@AuthenticationPrincipal CurrentUser currentUser,
                               Model model){
        model.addAttribute("notConfirmedTimetable", timetableRepository.findAllByConfirmed(false));
        model.addAttribute("user", currentUser.getUser());
        return "panel";
    }
}
