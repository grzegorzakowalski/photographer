package com.kowal.photographer.controllers;

import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {
    private final UserRepository userRepository;
    private final TimetableRepository timetableRepository;
    public PanelController(UserRepository userRepository, TimetableRepository timetableRepository) {
        this.userRepository = userRepository;
        this.timetableRepository = timetableRepository;
    }

    @GetMapping
    public String getPanelView(@AuthenticationPrincipal UserDetails userDetails,
                               Model model){
        model.addAttribute("notConfirmedTimetable", timetableRepository.findAllByConfirmed(false));
        model.addAttribute("user", userRepository.getWithPicturesByUsername(userDetails.getUsername()));
        return "panel";
    }
}
