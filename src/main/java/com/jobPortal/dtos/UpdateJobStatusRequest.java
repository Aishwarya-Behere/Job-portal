package com.jobPortal.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Payload to mark a job position as filled (UNAVAILABLE)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateJobStatusRequest {

    private String companyName;
    private String jobTitle;
}
