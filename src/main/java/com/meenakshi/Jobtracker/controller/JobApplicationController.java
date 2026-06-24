package com.meenakshi.Jobtracker.controller;

import com.meenakshi.Jobtracker.entity.JobApplication;
import com.meenakshi.Jobtracker.service.JobApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;

    @PostMapping
    public JobApplication saveApplication(@RequestBody JobApplication application) {
        return service.saveApplication(application);
    }

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return service.getAllApplications();
    }
    @GetMapping("/{id}")
public JobApplication getById(@PathVariable Long id) {
    return service.getById(id);
}

    @PutMapping("/{id}")
    public JobApplication updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application) {

        return service.updateApplication(id, application);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
        service.deleteApplication(id);
        return "Job Application Deleted Successfully";
    }
    @GetMapping("/test")
public String test() {
    return "Controller Working";
}
}