package com.meenakshi.Jobtracker.service;

import com.meenakshi.Jobtracker.entity.JobApplication;
import com.meenakshi.Jobtracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository repository;

    public JobApplication saveApplication(JobApplication application) {
        return repository.save(application);
    }

    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }
    public JobApplication getById(Long id) {
    return repository.findById(id).orElse(null);
}
public JobApplication getApplicationById(Long id) {
    return repository.findById(id).orElse(null);
}

    public JobApplication updateApplication(Long id, JobApplication application) {
        application.setId(id);
        return repository.save(application);
    }

    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }
}