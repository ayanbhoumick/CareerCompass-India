package com.careercompass.careercompass.service;

import com.careercompass.careercompass.model.JobListing;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareerJetFetchService {

    @Value("${careerjet.affiliate.id}")
    private String affiliateId;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<JobListing> fetchJobs(String role, String city) {
        try {
            String keywords = role.replace(" ", "%20");
            String location = (city + "%20India").replace(" ", "%20");
            String url = "http://public.api.careerjet.co.in/jobs" +
                    "?keywords=" + keywords +
                    "&location=" + location +
                    "&affid=" + affiliateId +
                    "&user_ip=127.0.0.1" +
                    "&url=http://localhost" +
                    "&user_agent=BTechRadar" +
                    "&format=json" +
                    "&pagesize=50";

            String response = restTemplate.getForObject(url, String.class);
            JsonNode json = objectMapper.readTree(response);
            JsonNode jobs = json.get("jobs");

            List<JobListing> listings = new ArrayList<>();
            if (jobs != null && jobs.isArray()) {
                for (JsonNode job : jobs) {
                    String title = job.path("title").asText("Unknown");
                    String company = job.path("company").asText("Unknown");
                    String location2 = job.path("locations").asText(city);
                    String description = job.path("description").asText("");
                    listings.add(new JobListing(title, company, location2, description, 0.0, 0.0));
                }
            }
            return listings;

        } catch (Exception e) {
            System.out.println("CareerJet API failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
