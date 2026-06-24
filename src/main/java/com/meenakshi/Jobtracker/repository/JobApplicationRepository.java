package com.meenakshi.Jobtracker.repository;

import com.meenakshi.Jobtracker.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}