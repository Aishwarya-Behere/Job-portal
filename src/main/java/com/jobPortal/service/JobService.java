package com.jobPortal.service;

import java.util.List;

import com.jobPortal.dtos.ApiResponse;
import com.jobPortal.dtos.PostJobRequest;
import com.jobPortal.dtos.UpdateJobStatusRequest;
import com.jobPortal.dtos.UpdateSalRequest;
import com.jobPortal.entities.Industry;
import com.jobPortal.entities.Job;
import com.jobPortal.entities.JobType;

public interface JobService {

    // API 2: post a new job
    Job postJob(PostJobRequest request);

    // API 3: get jobs by location
    List<Job> getJobsByLocation(String location);

    // API 4: delete jobs by type and company
    ApiResponse deleteJobsByTypeAndCompany(JobType jobType, String companyName);

    // API 5: update salary
    Job updateSalary(UpdateSalRequest request);

    // API 6: jobs by industry and minimum salary
    List<Job> getJobsByIndustryAndMinSalary(Industry industry, Double minSalary);

    // API 7: mark job as unavailable
    ApiResponse updateJobStatusToUnavailable(UpdateJobStatusRequest request);
}
