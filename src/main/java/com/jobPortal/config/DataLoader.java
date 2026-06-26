package com.jobPortal.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jobPortal.entities.Company;
import com.jobPortal.entities.Industry;
import com.jobPortal.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

// CommandLineRunner: Spring Boot calls run() automatically after the app starts
// and AFTER Hibernate has created/updated all DB tables.
// This replaces data.sql entirely — no SQL file needed.
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CompanyRepository companyRepo;

    @Override
    public void run(String... args) throws Exception {

        // Guard: only insert if the table is empty
        // Without this, every restart would crash with "Duplicate entry" error
        // because name and email have UNIQUE constraints
        if (companyRepo.count() == 0) {

            List<Company> companies = List.of(
                    new Company(null, "TechCorp Solutions",
                            "contact@techcorp.com", "Pune",
                            Industry.INFORMATION_TECHNOLOGY, LocalDateTime.now()),

                    new Company(null, "HealthPlus Hospital",
                            "info@healthplus.com", "Mumbai",
                            Industry.HEALTHCARE, LocalDateTime.now()),

                    new Company(null, "EduWorld Academy",
                            "admin@eduworld.com", "Bangalore",
                            Industry.EDUCATION, LocalDateTime.now()),

                    new Company(null, "BankFirst Ltd",
                            "hr@bankfirst.com", "Pune",
                            Industry.BANKING, LocalDateTime.now()),

                    new Company(null, "PharmaCare India",
                            "careers@pharmacare.com", "Hyderabad",
                            Industry.PHARMACEUTICAL, LocalDateTime.now()),

                    new Company(null, "Grand Hotels Group",
                            "jobs@grandhotels.com", "Delhi",
                            Industry.HOSPITALITY, LocalDateTime.now())
            );

            companyRepo.saveAll(companies);
            System.out.println("Sample data loaded: " + companies.size() + " companies inserted.");

        } else {
            System.out.println("Data already exists — skipping sample data load.");
        }
    }
}
