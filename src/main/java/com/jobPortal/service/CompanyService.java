package com.jobPortal.service;

import java.util.List;

import com.jobPortal.entities.Company;
import com.jobPortal.entities.Industry;

public interface CompanyService {

    // API 1
    List<Company> getCompaniesByIndustryAndLocation(Industry industry, String location);
}
