package com.kowal.photographer.controllers;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import com.kowal.photographer.security.CurrentUser;
import com.kowal.photographer.services.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/panel")
@Slf4j
public class PanelController {
    private final ConfigurationService configurationService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Validator validator;
    public PanelController(ConfigurationService configurationService, PasswordEncoder passwordEncoder, UserRepository userRepository, Validator validator) {
        this.configurationService = configurationService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @GetMapping
    public String getPanelView(@AuthenticationPrincipal CurrentUser currentUser,
                               Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("navIsActive", "panel");
        model.addAttribute("footerIsActive", "panel");
        // dodaj wyświetlanie twoich terminów
        return "panel";
    }

    @PostMapping
    public String changePassword(String password,
                                 @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/logout";
    }

    @GetMapping("/user-control")
    public String userControlView(Model model){
        List<User> all = userRepository.findAll();
        model.addAttribute("navIsActive", "panel");
        model.addAttribute("footerIsActive", "user-control");
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("allUsers", all.stream()
                .filter(user -> !user.getRole().equals("ROLE_TEMP"))
                .sorted(Comparator.comparing(User::getRole))
                .toList());
        return "panel-user-control";
    }

    @GetMapping("/change-user")
    public String modifyUserView(@RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "error", required = false) String error,
                                 Model model){
        if( id == null){
            return "redirect:/panel/user-control";
        }
        model.addAttribute("navIsActive", "panel");
        model.addAttribute("footerIsActive", "user-control");
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        model.addAttribute("error", error);
        return "panel-change-user";
    }

    @PostMapping("/change-user")
    public String modifyUserData(User user){
        if(user.getPassword().isEmpty()){
            user.setPassword(userRepository.findById(user.getId()).orElse(user).getPassword());
        }
        Set<ConstraintViolation<User>> validated = validator.validate(user);
        if(!validated.isEmpty()){
            return "redirect:/panel/change-user?id=" + user.getId() + "&error=true";
        }
        userRepository.save(user);
        return "redirect:/panel/user-control";
    }

    @GetMapping("/new-user")
    public String addNewUser(Model model,
                             @RequestParam(name = "error", required = false) String error){
        model.addAttribute("navIsActive", "panel");
        model.addAttribute("footerIsActive", "user-control");
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("error", error);
        model.addAttribute("user", new User());
        return "panel-new-user";
    }

    @PostMapping("/new-user")
    public String addNewUser(User user){
        Set<ConstraintViolation<User>> validated = validator.validate(user);
        if(!validated.isEmpty()){
            return "redirect:/panel/new-user?error=true";
        }
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/panel/user-control";
    }

    @PostMapping("/delete-user")
    public String deleteUser(Long id){
        userRepository.delete(userRepository.findById(id).orElse(new User()));
        return "redirect:/panel/user-control";
    }
}
