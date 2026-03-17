package com.careercompass.careercompass.controller;

import com.careercompass.careercompass.model.Skill;
import com.careercompass.careercompass.model.SkillGapReport;
import com.careercompass.careercompass.model.CurriculumGapReport;
import com.careercompass.careercompass.service.JobFetchService;
import com.careercompass.careercompass.service.SkillExtractorService;
import com.careercompass.careercompass.service.SkillGapService;
import com.careercompass.careercompass.service.CurriculumGapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class SkillController {
    @Autowired
    private JobFetchService jobFetchService;

    @Autowired
    private SkillExtractorService skillExtractorService;

    @Autowired
    private SkillGapService skillGapService;

    @Autowired
    private CurriculumGapService curriculumGapService;

    // Health check
    @GetMapping("/health")
    public String health() {
        return "BTech Radar is running!";
    }

    // get top demanded skills for a role and city
    @GetMapping("/skills/demand")
    public List<Skill> getSkillDemand(
            @RequestParam(name = "role", defaultValue = "software engineer") String role,
            @RequestParam(name = "city", defaultValue = "Mumbai") String city,
            @RequestParam(name = "topN", defaultValue = "15") int topN) {

        List<String> descriptions = jobFetchService.fetchJobs(role, city)
                .stream()
                .map(job -> job.getDescription())
                .toList();

        return skillExtractorService.getTopDemandedSkills(descriptions, topN);
    }

    // get skill gap report for a student
    @PostMapping("/skills/gap")
    public SkillGapReport getSkillGap(
            @RequestParam(name = "role", defaultValue = "software engineer") String role,
            @RequestParam(name = "city", defaultValue = "Mumbai") String city,
            @RequestBody List<String> studentSkills) {

        return skillGapService.analyzeGap(studentSkills, role, city);
    }

    // get curriculum gap report
    @PostMapping("/curriculum/gap")
    public CurriculumGapReport getCurriculumGap(
            @RequestParam(name = "role", defaultValue = "software engineer") String role,
            @RequestParam(name = "city", defaultValue = "Mumbai") String city,
            @RequestBody List<String> studentSkills) {

        return curriculumGapService.analyzeCurriculum(studentSkills, role, city);
    }
}
