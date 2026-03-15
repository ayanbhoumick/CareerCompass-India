package com.careercompass.careercompass.service;

import com.careercompass.careercompass.model.Skill;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillExtractorService {

    // The skill dictionary — skill name → category
    private static final Map<String, String> SKILL_DICTIONARY = new HashMap<>();

    static {
        // Backend
        SKILL_DICTIONARY.put("java", "Backend");
        SKILL_DICTIONARY.put("python", "Backend");
        SKILL_DICTIONARY.put("node.js", "Backend");
        SKILL_DICTIONARY.put("spring boot", "Backend");
        SKILL_DICTIONARY.put("django", "Backend");
        SKILL_DICTIONARY.put("rest api", "Backend");
        SKILL_DICTIONARY.put("microservices", "Backend");

        // Frontend
        SKILL_DICTIONARY.put("react", "Frontend");
        SKILL_DICTIONARY.put("angular", "Frontend");
        SKILL_DICTIONARY.put("vue", "Frontend");
        SKILL_DICTIONARY.put("javascript", "Frontend");
        SKILL_DICTIONARY.put("html", "Frontend");
        SKILL_DICTIONARY.put("css", "Frontend");
        SKILL_DICTIONARY.put("typescript", "Frontend");

        // Database
        SKILL_DICTIONARY.put("sql", "Database");
        SKILL_DICTIONARY.put("mysql", "Database");
        SKILL_DICTIONARY.put("postgresql", "Database");
        SKILL_DICTIONARY.put("mongodb", "Database");
        SKILL_DICTIONARY.put("redis", "Database");
        SKILL_DICTIONARY.put("hibernate", "Database");

        // DevOps
        SKILL_DICTIONARY.put("docker", "DevOps");
        SKILL_DICTIONARY.put("kubernetes", "DevOps");
        SKILL_DICTIONARY.put("aws", "DevOps");
        SKILL_DICTIONARY.put("azure", "DevOps");
        SKILL_DICTIONARY.put("git", "DevOps");
        SKILL_DICTIONARY.put("jenkins", "DevOps");
        SKILL_DICTIONARY.put("linux", "DevOps");

        // Data
        SKILL_DICTIONARY.put("machine learning", "Data");
        SKILL_DICTIONARY.put("deep learning", "Data");
        SKILL_DICTIONARY.put("tensorflow", "Data");
        SKILL_DICTIONARY.put("pandas", "Data");
        SKILL_DICTIONARY.put("numpy", "Data");
        SKILL_DICTIONARY.put("data analysis", "Data");
        SKILL_DICTIONARY.put("tableau", "Data");

        // Fundamentals
        SKILL_DICTIONARY.put("data structures", "Fundamentals");
        SKILL_DICTIONARY.put("algorithms", "Fundamentals");
        SKILL_DICTIONARY.put("object oriented", "Fundamentals");
        SKILL_DICTIONARY.put("system design", "Fundamentals");
        SKILL_DICTIONARY.put("agile", "Fundamentals");
        SKILL_DICTIONARY.put("communication", "Fundamentals");
    }

    // METHOD OVERLOAD 1 — extract skills from a single description
    public Map<String, Integer> extractSkills(String description) {
        Map<String, Integer> skillCount = new HashMap<>();
        String lowerDesc = description.toLowerCase();

        for (String skill : SKILL_DICTIONARY.keySet()) {
            if (lowerDesc.contains(skill)) {
                skillCount.put(skill, 1);
            }
        }
        return skillCount;
    }

    // METHOD OVERLOAD 2 — extract skills from a list of descriptions
    public Map<String, Integer> extractSkills(List<String> descriptions) {
        Map<String, Integer> skillCount = new HashMap<>();

        for (String description : descriptions) {
            String lowerDesc = description.toLowerCase();
            for (String skill : SKILL_DICTIONARY.keySet()) {
                if (lowerDesc.contains(skill)) {
                    skillCount.put(skill, skillCount.getOrDefault(skill, 0) + 1);
                }
            }
        }
        return skillCount;
    }

    // Get top N demanded skills as a ranked list
    public List<Skill> getTopDemandedSkills(List<String> descriptions, int topN) {
        Map<String, Integer> skillCount = extractSkills(descriptions);

        List<Skill> skills = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : skillCount.entrySet()) {
            String skillName = entry.getKey();
            int count = entry.getValue();
            String category = SKILL_DICTIONARY.get(skillName);
            skills.add(new Skill(skillName, count, category));
        }

        Collections.sort(skills);

        if (skills.size() > topN) {
            return skills.subList(0, topN);
        }
        return skills;
    }

    // Helper — get all known skills
    public static Set<String> getAllKnownSkills() {
        return SKILL_DICTIONARY.keySet();
    }
}