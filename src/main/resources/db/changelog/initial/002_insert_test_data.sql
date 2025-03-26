INSERT INTO users (id, username, email, password, phone, profile_image_url, role) VALUES
                                                                                      (1, 'applicant1', 'applicant1@example.com', 'pass123', '0500123456', 'applicant1.png', 'APPLICANT'),
                                                                                      (2, 'employer1', 'employer1@example.com', 'pass456', '0500654321', 'employer1.png', 'EMPLOYER');

INSERT INTO resume (id, title, category, expected_salary, telegram, email, phone, facebook, linkedin, published, user_id) VALUES
                                                                                                                              (1, 'Java Developer', 'IT', 1000, '@java_dev', 'applicant1@example.com', '0500123456', 'https://fb.com/applicant1', 'https://linkedin.com/in/applicant1', true, 1),
                                                                                                                              (2, 'Frontend Developer', 'IT', 900, '@frontend_dev', 'applicant1@example.com', '0500123456', 'https://fb.com/frontend', 'https://linkedin.com/in/frontend', true, 1);

INSERT INTO vacancies (id, title, description, salary, category, experience_from, experience_to, published, company_id) VALUES
                                                                                                                            (1, 'Backend Developer', 'Spring Boot, PostgreSQL required', 1200, 'IT', 1, 3, true, 2),
                                                                                                                            (2, 'UI/UX Designer', 'Experience with Figma and Adobe XD', 1100, 'Design', 0, 2, true, 2);

INSERT INTO education (id, resume_id, institution, course, start_year, end_year) VALUES
                                                                                     (1, 1, 'Kyrgyz State Technical University', 'Bachelor in Computer Science', 2016, 2020),
                                                                                     (2, 2, 'International Design School', 'Diploma in UI/UX', 2019, 2021);

INSERT INTO experience (id, resume_id, company, position, responsibilities, start_year, end_year) VALUES
                                                                                                      (1, 1, 'Tech Solutions', 'Junior Java Developer', 'Backend API development using Spring Boot', 2020, 2022),
                                                                                                      (2, 2, 'Creative Agency', 'UX Intern', 'Assisted in designing user flows and mockups', 2021, 2022);

INSERT INTO response (resume_id, vacancy_id) VALUES
                                                 (1, 1),
                                                 (2, 2);