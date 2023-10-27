package com.kowal.photographer.controllers;

import com.kowal.photographer.entities.Issue;
import com.kowal.photographer.repositorys.IssueRepository;
import com.kowal.photographer.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ConfigurationService configurationService;
    private final IssueRepository issueRepository;


    @GetMapping
    public String problemView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","about");
        model.addAttribute("footerIsActive","problem");
        model.addAttribute("issue", new Issue());
        return "problem";
    }

    @PostMapping
    public String addIssue(Issue issue){
        issue.setResolved(false);
        issueRepository.save(issue);
        return "redirect:/?added=true";
    }

    @GetMapping("/list")
    public String issueListView(Model model){
        model.addAttribute("siteColor",configurationService.getStringSiteColor());
        model.addAttribute("navIsActive","about");
        model.addAttribute("footerIsActive","problemList");
        model.addAttribute("issues", issueRepository.findAllByResolved(false));
        return "problem-list";
    }

    @PostMapping("/resolve")
    public String resolveIssue(Long id){
        Optional<Issue> byId = issueRepository.findById(id);
        if(byId.isPresent()){
            Issue issue = byId.get();
            issue.setResolved(true);
            issueRepository.save(issue);
        }
        return "redirect:/problem/list";
    }
}
