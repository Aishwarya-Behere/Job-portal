package com.jobPortal.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

// Generic response DTO for success/failure messages
// Same pattern used in the reference healthcare project
@Getter
@Setter
public class ApiResponse {

    private LocalDateTime timeStamp;
    private String message;
    private String status;

    public ApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }
}
