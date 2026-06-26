package com.jobPortal.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Payload to update salary of a specific job
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateSalRequest {

    private Long companyId;
    private String jobTitle;
    private Double salary;
}
