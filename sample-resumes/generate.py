from fpdf import FPDF

W = 170
OUT = "/Users/abhoumic/Downloads/careercompass/sample-resumes/"

def new_pdf():
    pdf = FPDF()
    pdf.set_margins(20, 20, 20)
    pdf.add_page()
    return pdf

def heading(pdf, text):
    pdf.set_font("Helvetica", "B", 14)
    pdf.multi_cell(W, 7, text)

def body(pdf, text):
    pdf.set_font("Helvetica", "", 9)
    pdf.multi_cell(W, 5, text)

def subheading(pdf, text):
    pdf.set_font("Helvetica", "B", 9)
    pdf.set_text_color(60, 60, 60)
    pdf.multi_cell(W, 5, text)
    pdf.set_text_color(0, 0, 0)

def divider(pdf):
    pdf.set_draw_color(180, 180, 180)
    pdf.line(20, pdf.get_y() + 1, 190, pdf.get_y() + 1)
    pdf.ln(3)

def section_label(pdf, text):
    pdf.ln(3)
    pdf.set_font("Helvetica", "B", 8)
    pdf.set_text_color(100, 100, 100)
    pdf.multi_cell(W, 5, text.upper())
    pdf.set_text_color(0, 0, 0)
    divider(pdf)

def bullet(pdf, text):
    pdf.set_font("Helvetica", "", 9)
    pdf.multi_cell(W, 5, "- " + text)


# ════════════════════════════════════════════════════════════
# ATS-FRIENDLY 1 — Full Stack Engineer
# Keywords: react, javascript, html, css, typescript, node.js,
#           rest api, sql, mongodb, docker, git, postgresql
# Sections: experience, education, skills, projects
# Metrics: 35%, 10k+ reqs, 40%, 50%, 500+ users, 60%
# Contact: email + Indian mobile
# ════════════════════════════════════════════════════════════
pdf = new_pdf()
heading(pdf, "RAHUL SHARMA")
body(pdf, "rahul.sharma@gmail.com  |  +91 9876543210  |  Bengaluru, Karnataka")
divider(pdf)

section_label(pdf, "Summary")
body(pdf, "Full Stack Engineer with 2 years of experience building scalable web applications using React, Node.js, and MongoDB. Skilled in REST API design, Docker deployment, and SQL databases.")

section_label(pdf, "Skills")
body(pdf, "React, JavaScript, TypeScript, HTML, CSS, Node.js, REST API, SQL, MongoDB, Docker, Git, PostgreSQL")

section_label(pdf, "Experience")
subheading(pdf, "Software Engineer - Infosys, Bengaluru (2023-Present)")
bullet(pdf, "Built React dashboard reducing customer support tickets by 35%")
bullet(pdf, "Developed REST API with Node.js serving 10,000+ daily requests")
bullet(pdf, "Migrated PostgreSQL schema improving query performance by 40%")
bullet(pdf, "Deployed microservices on Docker cutting release cycle by 50%")
bullet(pdf, "Collaborated with 5-person team using Git and Agile methodology")

section_label(pdf, "Projects")
subheading(pdf, "E-Commerce Platform (React, Node.js, MongoDB)")
bullet(pdf, "Architected full stack application for 500+ concurrent users")
bullet(pdf, "Integrated REST API with JWT authentication and role-based access")
bullet(pdf, "Implemented Docker containerisation reducing deployment time by 60%")

section_label(pdf, "Education")
body(pdf, "B.Tech Computer Science - VIT Vellore (2019-2023)  |  CGPA: 8.4/10")

pdf.output(OUT + "ats_01_fullstack_strong.pdf")
print("ats_01 done")


# ════════════════════════════════════════════════════════════
# ATS-FRIENDLY 2 — Backend Engineer
# Keywords: java, spring boot, rest api, microservices, sql,
#           postgresql, mongodb, redis, docker, git, kubernetes
# ════════════════════════════════════════════════════════════
pdf = new_pdf()
heading(pdf, "PRIYA NAIR")
body(pdf, "priya.nair@outlook.com  |  +91 8765432109  |  Hyderabad, Telangana")
divider(pdf)

section_label(pdf, "Summary")
body(pdf, "Backend Engineer with experience in Java, Spring Boot, and microservices architecture. Built REST APIs handling 50,000+ requests per day. Strong SQL and PostgreSQL skills.")

section_label(pdf, "Skills")
body(pdf, "Java, Spring Boot, REST API, Microservices, SQL, PostgreSQL, MongoDB, Redis, Docker, Git, Kubernetes, Python")

section_label(pdf, "Experience")
subheading(pdf, "Backend Developer - Wipro, Hyderabad (2022-Present)")
bullet(pdf, "Developed Spring Boot microservices processing 50,000 transactions/day")
bullet(pdf, "Optimised SQL queries on PostgreSQL reducing average response time by 45%")
bullet(pdf, "Implemented Redis caching layer improving API throughput by 3x")
bullet(pdf, "Deployed services on Kubernetes managing 12 production pods")
bullet(pdf, "Reduced system downtime by 70% through improved error handling")

section_label(pdf, "Projects")
subheading(pdf, "Inventory Management System (Java, Spring Boot, PostgreSQL)")
bullet(pdf, "Built REST API managing inventory for 200+ warehouses across India")
bullet(pdf, "Integrated MongoDB for unstructured product catalogue data")
bullet(pdf, "Containerised application using Docker reducing setup time by 80%")

section_label(pdf, "Education")
body(pdf, "B.Tech Information Technology - BITS Pilani (2018-2022)  |  CGPA: 8.9/10")

pdf.output(OUT + "ats_02_backend_strong.pdf")
print("ats_02 done")


# ════════════════════════════════════════════════════════════
# ATS-FRIENDLY 3 — AI Engineer
# Keywords: python, machine learning, deep learning, tensorflow,
#           pandas, numpy, docker, kubernetes, aws, data analysis
# ════════════════════════════════════════════════════════════
pdf = new_pdf()
heading(pdf, "ARJUN MEHTA")
body(pdf, "arjun.mehta@gmail.com  |  +91 9123456780  |  Pune, Maharashtra")
divider(pdf)

section_label(pdf, "Summary")
body(pdf, "AI Engineer with hands-on experience in Python, machine learning, and deep learning. Built TensorFlow models achieving 92% accuracy. Proficient with Pandas, NumPy, and Docker.")

section_label(pdf, "Skills")
body(pdf, "Python, Machine Learning, Deep Learning, TensorFlow, Pandas, NumPy, Docker, Kubernetes, SQL, AWS, Git, Data Analysis")

section_label(pdf, "Experience")
subheading(pdf, "AI Engineer - TCS, Pune (2023-Present)")
bullet(pdf, "Built machine learning model for fraud detection achieving 92% precision")
bullet(pdf, "Deployed deep learning pipeline on AWS reducing inference time by 55%")
bullet(pdf, "Developed Pandas ETL pipeline processing 2M records daily")
bullet(pdf, "Containerised ML models with Docker enabling reproducible deployments")
bullet(pdf, "Automated data analysis reports saving 15 analyst hours per week")

section_label(pdf, "Projects")
subheading(pdf, "Crop Disease Detection (Python, TensorFlow, Docker)")
bullet(pdf, "Trained CNN model on 50,000 images achieving 89% validation accuracy")
bullet(pdf, "Deployed inference API using Docker serving 1,000+ daily requests")
bullet(pdf, "Preprocessed dataset using NumPy and Pandas reducing noise by 30%")

section_label(pdf, "Education")
body(pdf, "B.Tech Computer Science - IIT Kharagpur (2019-2023)  |  CGPA: 9.1/10")

pdf.output(OUT + "ats_03_ai_strong.pdf")
print("ats_03 done")


# ════════════════════════════════════════════════════════════
# NON-ATS 1 — Vague prose, no contact, no standard sections,
#             no keywords, no metrics
# ════════════════════════════════════════════════════════════
pdf = new_pdf()
heading(pdf, "Vikram Desai")
body(pdf, "Passionate about technology and innovation")
divider(pdf)

body(pdf, "I am a very hardworking and enthusiastic developer who loves to solve problems. I have always been interested in computers since childhood and enjoy working in teams.")
pdf.ln(3)
body(pdf, "I have worked on various things including websites, apps, and data. I know many languages and tools. I am a fast learner and adapt to new technologies quickly.")
pdf.ln(3)
body(pdf, "Previous Work:")
body(pdf, "Worked at a startup for about a year doing development work. Built some features that users liked. Also did some database work. Left because wanted to explore more opportunities.")
pdf.ln(3)
body(pdf, "College Project:")
body(pdf, "Made a shopping website with my team. It had login, products, and cart functionality. We presented it and got good feedback from professors.")
pdf.ln(3)
body(pdf, "What I Know:")
body(pdf, "Programming, web dev, databases, some cloud stuff, communication, teamwork, leadership, problem solving")
pdf.ln(3)
body(pdf, "Education: Did engineering from a private university. Graduated in 2023.")

pdf.output(OUT + "non_ats_01_vague_no_contact.pdf")
print("non_ats_01 done")


# ════════════════════════════════════════════════════════════
# NON-ATS 2 — Too short (under 300 words), missing sections,
#             no phone, no metrics, no bullets
# ════════════════════════════════════════════════════════════
pdf = new_pdf()
heading(pdf, "Sneha Iyer")
body(pdf, "sneha.iyer@yahoo.com")
divider(pdf)

body(pdf, "I am a fresher looking for a software development job. I have done some coding in college and worked on a few projects.")
pdf.ln(3)
body(pdf, "I know Java and a bit of Python. I have used MySQL in college assignments.")
pdf.ln(3)
body(pdf, "Project: Hotel booking system using Java and MySQL.")
pdf.ln(3)
body(pdf, "B.Tech from JNTU 2024. Aggregate: 68%")

pdf.output(OUT + "non_ats_02_too_short.pdf")
print("non_ats_02 done")


# ════════════════════════════════════════════════════════════
# NON-ATS 3 — Good experience but avoids all keywords (uses
#             generic terms), no quantification
# ════════════════════════════════════════════════════════════
pdf = new_pdf()
heading(pdf, "Karan Verma")
body(pdf, "karan.verma@hotmail.com  |  Noida, UP")
divider(pdf)

section_label(pdf, "About Me")
body(pdf, "Dedicated software professional with experience delivering web solutions for enterprise clients. Known for clean code and collaborative work style.")

section_label(pdf, "Technical Abilities")
body(pdf, "Front-end technologies, back-end scripting, database management, version control, cloud platforms, containerisation tools")

section_label(pdf, "Work History")
subheading(pdf, "Developer - Tech Solutions Pvt Ltd, Noida (2022-2024)")
body(pdf, "Responsible for developing and maintaining web applications for clients. Worked closely with designers and business analysts to deliver features. Handled database queries and server-side logic. Participated in code reviews and sprint planning meetings. Contributed to multiple product releases across the year.")

section_label(pdf, "Academic Projects")
subheading(pdf, "Online Learning Portal")
body(pdf, "Developed a portal allowing students to access course materials. Implemented user authentication and content management. Project was appreciated by faculty and demonstrated solid understanding of web application architecture.")

section_label(pdf, "Qualifications")
body(pdf, "B.Tech Computer Science - AKTU, Lucknow (2018-2022)")

pdf.output(OUT + "non_ats_03_no_keywords.pdf")
print("non_ats_03 done")

print("\nAll 6 resumes generated successfully.")
