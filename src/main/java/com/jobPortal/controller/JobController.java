package com.jobPortal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.dtos.PostJobRequest;
import com.jobPortal.dtos.UpdateJobStatusRequest;
import com.jobPortal.dtos.UpdateSalRequest;
import com.jobPortal.entities.Industry;
import com.jobPortal.entities.JobType;
import com.jobPortal.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    // API 2: POST /jobs
    // Returns 201 Created because a new resource is being created
    @PostMapping
    @Operation(description = "Post a new job opening")
    public ResponseEntity<?> postJob(@RequestBody PostJobRequest request) {
        System.out.println("in post job " + request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.postJob(request)); // 201
    }

    // API 3: GET /jobs?location=Pune
    @GetMapping
    @Operation(description = "List all jobs from a specific location")
    public ResponseEntity<?> getJobsByLocation(@RequestParam String location) {
        System.out.println("in get jobs by location " + location);
        return ResponseEntity.ok(jobService.getJobsByLocation(location)); // 200
    }

    // API 4: DELETE /jobs?jobType=FULL_TIME&companyName=TechCorp Solutions
    @DeleteMapping
    @Operation(description = "Delete all jobs of specified type for specified company")
    public ResponseEntity<?> deleteJobs(
            @RequestParam JobType jobType,
            @RequestParam String companyName) {
        System.out.println("in delete jobs " + jobType + " " + companyName);
        return ResponseEntity.ok(jobService.deleteJobsByTypeAndCompany(jobType, companyName)); // 200
    }

    // API 5: PATCH /jobs/salary
    // PATCH (not PUT) because we're updating only one field
    @PatchMapping("/salary")
    @Operation(description = "Update salary of a job by company and title")
    public ResponseEntity<?> updateSalary(@RequestBody UpdateSalRequest request) {
        System.out.println("in update salary " + request);
        return ResponseEntity.ok(jobService.updateSalary(request)); // 200
    }

    // API 6: GET /jobs/search?industry=HEALTHCARE&minSalary=50000
    @GetMapping("/search")
    @Operation(description = "List jobs from a specific industry with minimum salary")
    public ResponseEntity<?> getJobsByIndustryAndMinSalary(
            @RequestParam Industry industry,
            @RequestParam Double minSalary) {
        System.out.println("in get jobs by industry and min salary");
        return ResponseEntity.ok(jobService.getJobsByIndustryAndMinSalary(industry, minSalary)); // 200
    }

    // API 7: PATCH /jobs/status
    // PATCH because we're updating only the status field
    @PatchMapping("/status")
    @Operation(description = "Mark a job as UNAVAILABLE (position filled)")
    public ResponseEntity<?> updateJobStatus(@RequestBody UpdateJobStatusRequest request) {
        System.out.println("in update job status " + request);
        return ResponseEntity.ok(jobService.updateJobStatusToUnavailable(request)); // 200
    }
}
