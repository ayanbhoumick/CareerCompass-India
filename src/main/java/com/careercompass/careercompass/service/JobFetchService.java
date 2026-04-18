package com.careercompass.careercompass.service;

import com.careercompass.careercompass.model.JobListing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobFetchService {

    @Value("${adzuna.app.id}")
    private String appId;

    @Value("${adzuna.app.key}")
    private String appKey;

    private final RestTemplate restTemplate;

    public JobFetchService() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10_000);
        factory.setReadTimeout(15_000);
        this.restTemplate = new RestTemplate(factory);
    }

    public List<JobListing> fetchJobs(String role, String city) {
        try {
            String encodedRole = URLEncoder.encode(role, StandardCharsets.UTF_8);
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String url = "https://api.adzuna.com/v1/api/jobs/in/search/1" +
                    "?app_id=" + appId +
                    "&app_key=" + appKey +
                    "&what=" + encodedRole +
                    "&where=" + encodedCity +
                    "&results_per_page=50";

            String response = restTemplate.getForObject(url, String.class);
            JsonNode json = new ObjectMapper().readTree(response);
            JsonNode results = json.get("results");

            List<JobListing> listings = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                JsonNode job = results.get(i);
                String title    = job.path("title").asText("Unknown");
                String desc     = job.path("description").asText("");
                String location = job.path("location").path("display_name").asText(city);
                String company  = job.path("company").path("display_name").asText("Unknown");
                Double salMin   = job.path("salary_min").asDouble(0.0);
                Double salMax   = job.path("salary_max").asDouble(0.0);
                listings.add(new JobListing(title, company, location, desc, salMin, salMax));
            }
            return listings;

        } catch (Exception e) {
            System.out.println("Adzuna API failed: " + e.getMessage());
            return getMockJobs();
        }
    }

    private List<JobListing> getMockJobs() {
        List<JobListing> mockJobs = new ArrayList<>();
        mockJobs.add(new JobListing("Java Developer", "TCS", "Mumbai",
                "We need java spring boot microservices docker kubernetes aws sql developer", 600000.0, 1200000.0));
        mockJobs.add(new JobListing("Python Developer", "Infosys", "Bangalore",
                "Python django machine learning data analysis sql postgresql developer needed", 500000.0, 1000000.0));
        mockJobs.add(new JobListing("Full Stack Developer", "Wipro", "Hyderabad",
                "React javascript html css node.js sql mongodb git agile developer", 700000.0, 1400000.0));
        mockJobs.add(new JobListing("Data Engineer", "HCL", "Pune",
                "Python sql data analysis tableau aws docker postgresql machine learning", 800000.0, 1500000.0));
        mockJobs.add(new JobListing("DevOps Engineer", "Tech Mahindra", "Chennai",
                "Docker kubernetes aws jenkins linux git microservices python shell scripting", 900000.0, 1800000.0));
        return mockJobs;
    }
}
