package com.jobPortal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPortal.custom_exceptions.ResourceNotFoundException;
import com.jobPortal.entities.Company;
import com.jobPortal.entities.Industry;
import com.jobPortal.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepo;

    @Override
    public List<Company> getCompaniesByIndustryAndLocation(Industry industry, String location) {
        List<Company> companies = companyRepo.findByIndustryAndLocation(industry, location);
        if (companies.isEmpty())
            throw new ResourceNotFoundException(
                    "No companies found for industry: " + industry + " and location: " + location);
        return companies;
    }
}
