package com.kowal.photographer.controllers;

import com.kowal.photographer.Month;
import com.kowal.photographer.repositorys.ConfigRepository;
import com.kowal.photographer.services.MonthService;
import com.kowal.photographer.services.TimetableService;
import com.kowal.photographer.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private final TimetableService timetableService;
    private final UserService userService;
    private final ConfigRepository configRepository;

    public TimetableController(TimetableService timetableService, UserService userService, ConfigRepository configRepository) {
        this.timetableService = timetableService;
        this.userService = userService;
        this.configRepository = configRepository;
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
                                   @RequestParam(name = "day") Integer day,
                                   @RequestParam(name = "month") Integer month,
                                   @RequestParam(name = "year") Integer year){
        model.addAttribute("dateTime", LocalDate.of(year,month,day));
        // TODO tutaj dodaj konto jeżeli zalogowany jeśli nie utwórz tempa
        model.addAttribute("user", userService.getTempUser());
        return "timetable-add";
    }
}
