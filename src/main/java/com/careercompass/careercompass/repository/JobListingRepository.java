package com.careercompass.careercompass.repository;

import com.careercompass.careercompass.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    
}
