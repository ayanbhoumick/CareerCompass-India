package com.careercompass.careercompass.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job_listings")
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double salaryMin;
    private Double salaryMax;

    // Constructor
    public JobListing(){}

    public JobListing(String title, String company, String location, String description, Double salaryMin, Double salaryMax){
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
    }

    // Getters and Setters

    public Long getId(){return id; }
    public String getTitle(){return title; }
    public void setTitle(String title){this.title = title; }
    public String getCompany(){return company; }
    public void setCompany(String company){this.company = company; }
    public String getLocation(){return location; }
    public void setLocation(String location){this.location = location; }
    public String getDescription(){return description; }
    public void setDescription(String description){this.description = description; }
    public Double getSalaryMin(){return salaryMin; }
    public void setSalaryMin(Double salaryMin){this.salaryMin = salaryMin; }
    public Double getSalaryMax(){return salaryMax; }
    public void setSalaryMax(Double salaryMax){this.salaryMax = salaryMax; }



    
}

