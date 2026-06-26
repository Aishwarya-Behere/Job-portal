package com.jobPortal.dtos;

import com.jobPortal.entities.JobType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Payload sent by client to post a new job opening
// Only sends companyId (not full Company object)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostJobRequest {

    private Long companyId;
    private String title;
    private String description;
    private Double salary;
    private String location;
    private JobType jobType;
}
