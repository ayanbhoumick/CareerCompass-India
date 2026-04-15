package com.careercompass.careercompass.service;

import com.careercompass.careercompass.model.JobListing;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JSearchFetchService {

    @Value("${jsearch.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<JobListing> fetchJobs(String role, String city) {
        try {
            String query = (role + " jobs in India").replace(" ", "%20");
            String url = "https://jsearch.p.rapidapi.com/search?query=" + query + "&page=1&num_pages=2&country=in";

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", apiKey);
            headers.set("X-RapidAPI-Host", "jsearch.p.rapidapi.com");

            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            JsonNode json = objectMapper.readTree(response.getBody());
            JsonNode data = json.get("data");

            List<JobListing> listings = new ArrayList<>();
            if (data != null && data.isArray()) {
                for (JsonNode job : data) {
                    String title = job.path("job_title").asText("Unknown");
                    String company = job.path("employer_name").asText("Unknown");
                    String location = job.path("job_city").asText(city);
                    String description = job.path("job_description").asText("");
                    Double salaryMin = job.path("job_min_salary").asDouble(0.0);
                    Double salaryMax = job.path("job_max_salary").asDouble(0.0);
                    listings.add(new JobListing(title, company, location, description, salaryMin, salaryMax));
                }
            }
            return listings;

        } catch (Exception e) {
            System.out.println("JSearch API failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
