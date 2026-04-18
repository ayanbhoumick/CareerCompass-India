package com.careercompass.careercompass.service;

import com.careercompass.careercompass.model.JobListing;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class JSearchFetchService {

    @Value("${jsearch.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JSearchFetchService() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10_000);
        factory.setReadTimeout(15_000);
        this.restTemplate = new RestTemplate(factory);
    }

    public List<JobListing> fetchJobs(String role, String city) {
        try {
            String queryText = role + " jobs in " + city;
            String query = URLEncoder.encode(queryText, StandardCharsets.UTF_8);
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
                    String title    = job.path("job_title").asText("Unknown");
                    String company  = job.path("employer_name").asText("Unknown");
                    String location = job.path("job_city").asText(city);
                    String desc     = job.path("job_description").asText("");
                    Double salMin   = job.path("job_min_salary").asDouble(0.0);
                    Double salMax   = job.path("job_max_salary").asDouble(0.0);
                    listings.add(new JobListing(title, company, location, desc, salMin, salMax));
                }
            }
            return listings;

        } catch (Exception e) {
            System.out.println("JSearch API failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
