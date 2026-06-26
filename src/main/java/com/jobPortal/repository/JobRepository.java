package com.jobPortal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobPortal.entities.Industry;
import com.jobPortal.entities.Job;
import com.jobPortal.entities.JobType;

public interface JobRepository extends JpaRepository<Job, Long> {

    // API 3: jobs by location
    List<Job> findByLocation(String location);

    // API 4: delete jobs by type and company
    // @Modifying required for DELETE/UPDATE queries
    @Modifying
    Long deleteByJobTypeAndCompanyId(JobType jobType, Long companyId);

    // API 5: find one job by company and title to update salary
    Optional<Job> findByCompanyIdAndTitle(Long companyId, String title);

    // API 6: jobs from a specific industry with salary >= minSalary
    @Query("""
            SELECT j FROM Job j
            WHERE j.company.industry = :industry
            AND j.salary >= :salary
            """)
    List<Job> findByIndustryAndMinSalary(@Param("industry") Industry industry,
                                          @Param("salary") Double salary);

    // API 7: find job by company name and title to update status
    Optional<Job> findByCompanyNameAndTitle(String companyName, String title);
}
