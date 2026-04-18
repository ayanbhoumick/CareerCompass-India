package com.careercompass.careercompass.controller;

import com.careercompass.careercompass.model.Skill;
import com.careercompass.careercompass.model.SkillGapReport;
import com.careercompass.careercompass.model.CurriculumGapReport;
import com.careercompass.careercompass.service.JobAggregatorService;
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
    private JobAggregatorService jobFetchService;

    @Autowired
    private SkillExtractorService skillExtractorService;

    @Autowired
    private SkillGapService skillGapService;

    @Autowired
    private CurriculumGapService curriculumGapService;

    @GetMapping("/health")
    public String health() {
        return "BTech Radar is running!";
    }

    @GetMapping("/skills/demand")
    public List<Skill> getSkillDemand(
            @RequestParam(name = "role", defaultValue = "software engineer") String role,
            @RequestParam(name = "city", defaultValue = "Mumbai") String city,
            @RequestParam(name = "topN", defaultValue = "15") int topN) {

        int safeTopN = Math.min(Math.max(topN, 1), 20);

        List<String> descriptions = jobFetchService.fetchJobs(role, city)
                .stream()
                .map(job -> job.getDescription())
                .toList();

        return skillExtractorService.getTopDemandedSkills(descriptions, safeTopN);
    }

    @PostMapping("/skills/gap")
    public SkillGapReport getSkillGap(
            @RequestParam(name = "role", defaultValue = "software engineer") String role,
            @RequestParam(name = "city", defaultValue = "Mumbai") String city,
            @RequestBody List<String> studentSkills) {

        List<String> cleaned = studentSkills.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(String::toLowerCase)
                .distinct()
                .limit(50)
                .toList();

        return skillGapService.analyzeGap(cleaned, role, city);
    }

    @PostMapping("/curriculum/gap")
    public CurriculumGapReport getCurriculumGap(
            @RequestParam(name = "role", defaultValue = "software engineer") String role,
            @RequestParam(name = "city", defaultValue = "Mumbai") String city,
            @RequestBody List<String> studentSkills) {

        List<String> cleaned = studentSkills.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(String::toLowerCase)
                .distinct()
                .limit(50)
                .toList();

        return curriculumGapService.analyzeCurriculum(cleaned, role, city);
    }
}
