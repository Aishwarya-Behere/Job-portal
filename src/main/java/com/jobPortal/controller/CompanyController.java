package com.jobPortal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.entities.Company;
import com.jobPortal.entities.Industry;
import com.jobPortal.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    // API 1: GET /companies?industry=INFORMATION_TECHNOLOGY&location=Pune
    @GetMapping
    @Operation(description = "List all companies by industry type and location")
    public ResponseEntity<?> getCompaniesByIndustryAndLocation(
            @RequestParam Industry industry,
            @RequestParam String location) {

        System.out.println("in get companies " + industry + " " + location);
        List<Company> companies = companyService.getCompaniesByIndustryAndLocation(industry, location);
        return ResponseEntity.ok(companies); // 200 OK
    }
}
