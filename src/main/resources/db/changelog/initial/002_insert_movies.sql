
INSERT INTO users (username, email, password, phone, profile_image_url, role) VALUES
                                                                                  ('applicant1', 'applicant1@example.com', 'pass123', '0500123456', 'applicant1.png', 'APPLICANT'),
                                                                                  ('employer1', 'employer1@example.com', 'pass456', '0500654321', 'employer1.png', 'EMPLOYER');

INSERT INTO resume (title, category, expected_salary, telegram, email, phone, facebook, linkedin, published, user_id) VALUES
                                                                                                                          ('Java Developer', 'IT', 1000, '@java_dev', 'applicant1@example.com', '0500123456', 'https://fb.com/applicant1', 'https://linkedin.com/in/applicant1', true, 1),
                                                                                                                          ('Frontend Developer', 'IT', 900, '@frontend_dev', 'applicant1@example.com', '0500123456', 'https://fb.com/frontend', 'https://linkedin.com/in/frontend', true, 1);

INSERT INTO vacancy (title, description, salary, category, experience_from, experience_to, published, company_id) VALUES
                                                                                                                      ('Backend Developer', 'Spring Boot, PostgreSQL required', 1200, 'IT', 1, 3, true, 2),
                                                                                                                      ('UI/UX Designer', 'Experience with Figma and Adobe XD', 1100, 'Design', 0, 2, true, 2);

INSERT INTO education (resume_id, institution, degree, "year") VALUES
                                                                   (1, 'Kyrgyz State Technical University', 'Bachelor in Computer Science', 2020),
                                                                   (2, 'International Design School', 'Diploma in UI/UX', 2021);

INSERT INTO experience (resume_id, company, position, years) VALUES
                                                                 (1, 'Tech Solutions', 'Junior Java Developer', 2),
                                                                 (2, 'Creative Agency', 'UX Intern', 1);

INSERT INTO response (resume_id, vacancy_id) VALUES
                                                 (1, 1),
                                                 (2, 2);