package com.jobPortal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPortal.entities.Company;
import com.jobPortal.entities.Industry;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // API 1: find by industry and location
    List<Company> findByIndustryAndLocation(Industry industry, String location);

    // Used in API 4 and API 7 to resolve company name -> Company object
    Optional<Company> findByName(String name);
}
