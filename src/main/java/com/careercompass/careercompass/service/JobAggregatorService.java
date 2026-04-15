package com.careercompass.careercompass.service;

import com.careercompass.careercompass.model.JobListing;
import com.careercompass.careercompass.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class JobAggregatorService {

    @Autowired
    private JobFetchService adzunaService;

    @Autowired
    private JSearchFetchService jSearchService;

    @Autowired
    private JobListingRepository jobListingRepository;

    public List<JobListing> fetchJobs(String role, String city) {
        // Run both providers in parallel
        CompletableFuture<List<JobListing>> adzunaFuture =
                CompletableFuture.supplyAsync(() -> adzunaService.fetchJobs(role, city));

        CompletableFuture<List<JobListing>> jSearchFuture =
                CompletableFuture.supplyAsync(() -> jSearchService.fetchJobs(role, city));

        CompletableFuture.allOf(adzunaFuture, jSearchFuture).join();

        // Merge results (each provider handles its own errors and returns empty list on failure)
        List<JobListing> merged = new ArrayList<>();
        try { merged.addAll(adzunaFuture.get()); } catch (Exception e) { System.out.println("Adzuna result error: " + e.getMessage()); }
        try { merged.addAll(jSearchFuture.get()); } catch (Exception e) { System.out.println("JSearch result error: " + e.getMessage()); }

        // Deduplicate by normalized title + company
        List<JobListing> deduplicated = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (JobListing job : merged) {
            String key = normalize(job.getTitle()) + "|" + normalize(job.getCompany());
            if (seen.add(key)) {
                deduplicated.add(job);
            }
        }

        System.out.println("Aggregated " + merged.size() + " jobs, " + deduplicated.size() + " after deduplication");

        jobListingRepository.deleteAll();
        jobListingRepository.saveAll(deduplicated);

        return deduplicated;
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase().trim();
    }
}
