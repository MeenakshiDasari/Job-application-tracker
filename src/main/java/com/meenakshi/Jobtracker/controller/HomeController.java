package com.meenakshi.Jobtracker.controller;

import com.meenakshi.Jobtracker.entity.JobApplication;
import com.meenakshi.Jobtracker.service.JobApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    private JobApplicationService service;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
public String dashboardPage(Model model) {

    var jobs = service.getAllApplications();

    long appliedCount = jobs.stream()
            .filter(job -> "Applied".equals(job.getStatus()))
            .count();

    long interviewCount = jobs.stream()
            .filter(job -> "Interview Scheduled".equals(job.getStatus()))
            .count();

    long rejectedCount = jobs.stream()
            .filter(job -> "Rejected".equals(job.getStatus()))
            .count();

    model.addAttribute("jobs", jobs);
    model.addAttribute("appliedCount", appliedCount);
    model.addAttribute("interviewCount", interviewCount);
    model.addAttribute("rejectedCount", rejectedCount);

    return "dashboard";
}

    @PostMapping("/add-job")
    public String addJob(
            @RequestParam String company,
            @RequestParam String role,
            @RequestParam String status) {

        JobApplication job = new JobApplication();

        job.setCompany(company);
        job.setRole(role);
        job.setStatus(status);
        job.setApplicationDate(LocalDate.now());

        service.saveApplication(job);

        return "redirect:/dashboard";
    }
    @GetMapping("/delete-job/{id}")
public String deleteJob(@PathVariable Long id) {

    service.deleteApplication(id);

    return "redirect:/dashboard";
}
@GetMapping("/edit-job/{id}")
public String editJob(@PathVariable Long id, Model model) {

    JobApplication job = service.getApplicationById(id);

    model.addAttribute("job", job);

    return "edit-job";
}

@PostMapping("/update-job")
public String updateJob(
        @RequestParam Long id,
        @RequestParam String company,
        @RequestParam String role,
        @RequestParam String status) {

    JobApplication job = new JobApplication();

    job.setId(id);
    job.setCompany(company);
    job.setRole(role);
    job.setStatus(status);
    job.setApplicationDate(LocalDate.now());

    service.updateApplication(id, job);

    return "redirect:/dashboard";
}
@PostMapping("/login")
public String login(
        @RequestParam String email,
        @RequestParam String password,
        Model model) {

    if(email.equals("admin@gmail.com")
            && password.equals("admin123")) {

        return "redirect:/dashboard";
    }

    model.addAttribute("error", true);

    return "login";
}
}