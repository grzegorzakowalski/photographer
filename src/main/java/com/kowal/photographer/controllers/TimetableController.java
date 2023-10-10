package com.kowal.photographer.controllers;

import com.kowal.photographer.Month;
import com.kowal.photographer.entitys.Timetable;
import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.ConfigRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import com.kowal.photographer.security.CurrentUser;
import com.kowal.photographer.services.MonthService;
import com.kowal.photographer.services.TimetableService;
import com.kowal.photographer.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private final TimetableService timetableService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ConfigRepository configRepository;
    private final TimetableRepository timetableRepository;
    private final Validator validator;

    public TimetableController(TimetableService timetableService, UserService userService, UserRepository userRepository, ConfigRepository configRepository, TimetableRepository timetableRepository, Validator validator) {
        this.timetableService = timetableService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.configRepository = configRepository;
        this.timetableRepository = timetableRepository;
        this.validator = validator;
    }

    @GetMapping
    public String timetableView(Model model, @RequestParam(name = "shift", defaultValue = "0") Integer shift, HttpSession session){
        LocalDate actualDate = LocalDate.now().plusMonths(shift);
        MonthService monthService = new MonthService(actualDate);
        Month month = new Month(actualDate);
        Integer maxSize = configRepository.getMaxPerDay();
        model.addAttribute("maxSize", maxSize);
        model.addAttribute("firstDayOfMonth", monthService.getFirstDayAsNumberOfWeekDay());
        model.addAttribute("lastDayOfMonth", monthService.getMonthLength());
        model.addAttribute("allUnavailable", timetableService.getUnavailableListForMonth(actualDate, maxSize));
        model.addAttribute("shift", shift);
        model.addAttribute("month", actualDate.getMonth().getValue());
        model.addAttribute("year", actualDate.getYear());
        model.addAttribute("myMonth", month);
        return "timetable";
    }

    @GetMapping("/add")
    public String timetableAddView(Model model,
                                   @RequestParam(name = "day", defaultValue = "1") Integer day,
                                   @RequestParam(name = "month", defaultValue = "1") Integer month,
                                   @RequestParam(name = "year", defaultValue = "2023") Integer year,
                                   @AuthenticationPrincipal CurrentUser currentUser){
        Timetable timetable = new Timetable();
        timetable.setOwner( currentUser != null ? currentUser.getUser() : userService.getTempUser());
        timetable.setDate( LocalDate.of(year, month, day));
        model.addAttribute("date", timetable.getDate());
        model.addAttribute("timetable", timetable);
        return "timetable-add";
    }

    @PostMapping("/add")
    public String timetableAdd(Timetable timetable, Model model){
        Set<ConstraintViolation<Timetable>> validated = validator.validate(timetable);
        if( !validated.isEmpty()){
            model.addAttribute("validated", validated);
            model.addAttribute("timetable", timetable);
            return "timetable-add";
        }
        timetable.setConfirmed(false);

        timetableService.add(timetable, configRepository.getMaxPerDay());// zwraca czy dodano czy nie
        return "redirect:/"; // TODO dodaj obsługę potwierdzenia
    }

    @GetMapping("/confirm")
    public String timetableConfirmByAdminView( @RequestParam(name = "id") Long id, Model model){
        Optional<Timetable> byId = timetableRepository.findById(id);
        if(byId.isEmpty()){
            return "/panel";
        }
        Timetable timetable = byId.get();
        model.addAttribute("timetable", timetable);
        model.addAttribute("user", userRepository.findUserByUsername(timetable.getOwner().getUsername()));
        return "timetable-confirm";
    }
}
