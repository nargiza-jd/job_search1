INSERT INTO users(id, name, email, password, phone, role)
VALUES
    (1, 'john_doe', 'john@example.com', 'pass123', '555-1111', 'APPLICANT'),
    (2, 'acme_corp', 'hr@acme.com', 'acmepass', '555-2222', 'EMPLOYER');


INSERT INTO resumes(id, title, category, expected_salary, telegram, email, phone, facebook, linkedin, published, user_id)
VALUES
    (1, 'Java Developer', 'IT', 1500, '@johndoe', 'john@example.com', '555-1111', null, 'linkedin.com/in/john', true, 1),
    (2, 'Frontend Developer', 'IT', 1300, '@johndoe2', 'john@example.com', '555-1111', null, null, true, 1);


INSERT INTO vacancies(id, title, description, salary, category, experience_from, experience_to, published, company_id)
VALUES
    (1, 'Senior Java Developer', 'Spring Boot project', 2500, 'IT', 2, 5, true, 2),
    (2, 'Junior QA Tester', 'Manual testing tasks', 1200, 'IT', 0, 2, true, 2);


INSERT INTO responses(id, resume_id, vacancy_id, applicant_id, employer_id)
VALUES (1, 1, 1, 1, 2);


INSERT INTO education(id, resume_id, institution, degree, year)
VALUES
    (1, 1, 'Kyrgyz State University', 'Bachelor in CS', 2021);


INSERT INTO experience(id, resume_id, company, position, years)
VALUES
    (1, 1, 'TechSoft', 'Java Intern', 1);