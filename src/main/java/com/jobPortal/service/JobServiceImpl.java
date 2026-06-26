package com.jobPortal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPortal.custom_exceptions.InvalidInputException;
import com.jobPortal.custom_exceptions.ResourceNotFoundException;
import com.jobPortal.dtos.ApiResponse;
import com.jobPortal.dtos.PostJobRequest;
import com.jobPortal.dtos.UpdateJobStatusRequest;
import com.jobPortal.dtos.UpdateSalRequest;
import com.jobPortal.entities.Company;
import com.jobPortal.entities.Industry;
import com.jobPortal.entities.Job;
import com.jobPortal.entities.JobType;
import com.jobPortal.entities.Status;
import com.jobPortal.repository.CompanyRepository;
import com.jobPortal.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepo;
    private final CompanyRepository companyRepo;

    // API 2: Post a new job opening
    @Override
    public Job postJob(PostJobRequest request) {
        System.out.println("in post job " + request);
        // 1. validate company exists
        Company company = companyRepo.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invalid Company ID: " + request.getCompanyId()));

        // 2. build job entity from DTO
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setCompany(company);

        // 3. set server-controlled fields (client should NOT set these)
        job.setPostedDate(LocalDateTime.now());
        job.setStatus(Status.AVAILABLE); // every new job starts as AVAILABLE

        return jobRepo.save(job);
    }

    // API 3: List all jobs from a specific location
    @Override
    public List<Job> getJobsByLocation(String location) {
        System.out.println("in get jobs by location " + location);
        List<Job> jobs = jobRepo.findByLocation(location);
        if (jobs.isEmpty())
            throw new ResourceNotFoundException("No jobs found for location: " + location);
        return jobs;
    }

    // API 4: Delete all jobs of specified type for specified company
    @Override
    public ApiResponse deleteJobsByTypeAndCompany(JobType jobType, String companyName) {
        System.out.println("in delete jobs " + jobType + " " + companyName);
        // 1. validate company exists
        Company company = companyRepo.findByName(companyName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found: " + companyName));

        // 2. delete and get count
        long deletedCount = jobRepo.deleteByJobTypeAndCompanyId(jobType, company.getId());
        if (deletedCount == 0)
            throw new ResourceNotFoundException(
                    "No " + jobType + " jobs found for company: " + companyName);

        return new ApiResponse(deletedCount + " job(s) deleted successfully.", "Success");
    }

    // API 5: Update salary of a specific job
    @Override
    public Job updateSalary(UpdateSalRequest request) {
        System.out.println("in update salary " + request);
        Job job = jobRepo.findByCompanyIdAndTitle(request.getCompanyId(), request.getJobTitle())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Job not found: title='" + request.getJobTitle()
                        + "' for companyId=" + request.getCompanyId()));

        job.setSalary(request.getSalary());
        return jobRepo.save(job);
    }

    // API 6: List jobs from specific industry with salary >= minSalary
    @Override
    public List<Job> getJobsByIndustryAndMinSalary(Industry industry, Double minSalary) {
        System.out.println("in get jobs by industry and min salary");
        List<Job> jobs = jobRepo.findByIndustryAndMinSalary(industry, minSalary);
        if (jobs.isEmpty())
            throw new ResourceNotFoundException(
                    "No jobs found for industry: " + industry + " with minimum salary: " + minSalary);
        return jobs;
    }

    // API 7: Mark job as UNAVAILABLE (position filled)
    @Override
    public ApiResponse updateJobStatusToUnavailable(UpdateJobStatusRequest request) {
        System.out.println("in update job status " + request);
        Job job = jobRepo.findByCompanyNameAndTitle(request.getCompanyName(), request.getJobTitle())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Job not found: title='" + request.getJobTitle()
                        + "' for company='" + request.getCompanyName() + "'"));

        if (job.getStatus() == Status.UNAVAILABLE)
            throw new InvalidInputException("Job is already marked as UNAVAILABLE.");

        job.setStatus(Status.UNAVAILABLE);
        return new ApiResponse("Job status updated to UNAVAILABLE.", "Success");
    }
}
