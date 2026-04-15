# BTech Radar India

Live job market intelligence for Indian CS students.

## What It Does
- Fetches real job listings from Adzuna and JSearch APIs (aggregated in parallel)
- Analyses which skills are most in demand for any role and city
- Compares your skills against market demand and gives a match score
- Scores your college curriculum against real market needs

## Tech Stack
- **Backend:** Java, Spring Boot, Spring Data JPA, H2 In-Memory DB
- **APIs:** Adzuna Jobs API, JSearch (RapidAPI) — deduplicated and merged
- **Frontend:** Vanilla HTML/CSS/JavaScript, Chart.js
- **Fonts:** Geist Mono, Space Grotesk
- **Build:** Maven

## Pages
| Page | Path | Description |
|------|------|-------------|
| Homepage | `/` | Dark starfield landing with animated hero, glass cards |
| Loading | `/loading.html` | Star parallax + Tetris loader (10s transition) |
| Radar | `/radar.html` | Skill demand, gap analysis, curriculum audit |

## How To Run
```bash
./mvnw spring-boot:run
```
Open `localhost:8080` in your browser.

## Features
- **Skill Demand Chart** — top demanded skills for any role and city
- **Skill Gap Analyser** — match score and missing skills vs. market
- **Curriculum Gap Score** — how relevant your college subjects are to live job data
- **Dual API aggregation** — Adzuna + JSearch fetched in parallel, deduplicated by title + company
- **Animated homepage** — 3D starfield background, text scramble on load, animated subtitle words
- **Loading transition** — CSS star parallax background with Tetris loader before entering Radar

## Team
- Ayan Bhoumick — Backend development
- Dhruv Jain — Frontend layout
- Porush Tyagi — Chart integration
