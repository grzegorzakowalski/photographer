package com.kowal.photographer.controllers;

import com.kowal.photographer.Month;
import com.kowal.photographer.entitys.Timetable;
import com.kowal.photographer.repositorys.ConfigRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import com.kowal.photographer.security.CurrentUser;
import com.kowal.photographer.services.ConfigurationService;
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
    private final Validator validator;
    private final ConfigurationService configurationService;
    private final TimetableRepository timetableRepository;

    public TimetableController(TimetableService timetableService, UserService userService, UserRepository userRepository, ConfigRepository configRepository, TimetableRepository timetableRepository, Validator validator, ConfigurationService configurationService) {
        this.timetableService = timetableService;
        this.userService = userService;
        this.configurationService = configurationService;
        this.timetableRepository = timetableRepository;
        this.validator = validator;
    }

    @GetMapping
    public String timetableView(Model model, @RequestParam(name = "shift", defaultValue = "0") Integer shift, HttpSession session){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","timetable");

        LocalDate actualDate = LocalDate.now().plusMonths(shift);
        MonthService monthService = new MonthService(actualDate);
        Month month = new Month(actualDate);
        Integer maxSize = configurationService.getIntegerMaxPerDay();
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
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","timetable");

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
        timetableService.add(timetable);
        return "redirect:/";
    }

    @GetMapping("/confirm")
    public String timetableConfirmByAdminView( @RequestParam(name = "id") Long id, Model model){
        Optional<Timetable> byId = timetableRepository.findById(id);
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","timetableList");

        if(byId.isEmpty()){
            return "/panel";
        }
        Timetable timetable = byId.get();
        model.addAttribute("timetable", timetable);
        return "timetable-confirm";
    }
    @PostMapping("/confirm")
    public String confirmTimetable(Timetable timetable, Model model){
        Set<ConstraintViolation<Timetable>> validate = validator.validate(timetable);
        if(!validate.isEmpty()){
            model.addAttribute("validate", validate);
            return "/timetable-confirm";
        }
        timetable.setConfirmed(true);
        timetableRepository.save(timetable);
        return "redirect:/timetable/list";
    }

    @GetMapping("/delete")
    public String deleteConfirmation(@RequestParam("id") long id, Model model){
        Optional<Timetable> optional = timetableRepository.findById(id);
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","timetableList");
        if(optional.isPresent()) {
            model.addAttribute("timetable", optional.get());
            return "timetable-delete-confirmation";
        }
        return "redirect:/timetable/list?msg=delete-error"; // dodaj error
    }

    @PostMapping("/delete")
    public String deleteTimetable(Timetable timetable){
        timetableRepository.delete(timetable);
        return "redirect:/panel";
    }

    @GetMapping("/list")
    public String timetableList(Model model,
                                @RequestParam(name = "msg", required = false)String msg){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","timetable");
        model.addAttribute("footerIsActive","timetableList");
        model.addAttribute("allNotConfirmed", timetableService.getListByConfirmed(false));
        model.addAttribute("allConfirmed", timetableService.getListByConfirmed(true));
        model.addAttribute("msg",msg);
        model.addAttribute("maxPerDay",configurationService.getIntegerMaxPerDay());
        return "timetable-list";

    }

    @PostMapping("/list")
    public String timetableListForm(Integer maxPerDay){
        configurationService.setMaxPerDay(maxPerDay.toString());
        return "redirect:/timetable/list";
    }
}
