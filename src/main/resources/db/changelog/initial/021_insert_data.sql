--liquibase formatted sql
--changeset nargiza:21-insert data.sql
insert into role(ROLE)
values ( 'APPLICANT' ),
       ( 'EMPLOYER');

insert into CONTACT_TYPES(TYPE)
values ('Email'),
       ('Phone'),
       ('Telegram'),
       ('LinkedIn'),
       ('Facebook');

insert into USERS(USERNAME, SURNAME, AGE, EMAIL, PASSWORD, PHONE_NUMBER, AVATAR, ENABLED, ROLE_ID)
values ( 'Nargiza',
         'Testova',
         25,
         'nargiza@example.com',
         '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
         '0500123456',
         'avatar.jpg',
         true,
         (select id from role where role = 'APPLICANT'));

insert into CATEGORIES(CATEGORY_NAME)
values ('IT'),
       ('Design'),
       ('Marketing'),
       ('Finance'),
       ('Education'),
       ('Healthcare'),
       ('Sales'),
       ('Construction');

insert into VACANCIES(
    USERNAME,
    DESCRIPTION,
    SALARY,
    EXP_FROM,
    EXP_TO,
    IS_ACTIVE,
    CREATED_DATE,
    UPDATE_TIME,
    CATEGORY_ID,
    AUTHOR_ID
)
values
    (
        'Backend Developer',
        'Spring Boot, PostgreSQL required',
        1200,
        1,
        3,
        true,
        CURRENT_DATE,
        CURRENT_TIMESTAMP,
        (select id from CATEGORIES where CATEGORY_NAME like 'IT'),
        (select id from USERS where USERS.EMAIL like 'nargiza@example.com')
    ),
    (
        'UI/UX Designer',
        'Experience with Figma and Adobe XD',
        1100,
        0,
        2,
        true,
        CURRENT_DATE,
        CURRENT_TIMESTAMP,
        (select id from CATEGORIES where CATEGORY_NAME like 'Education'),
        (select id from USERS where USERS.EMAIL like 'nargiza@example.com')
    );

insert into RESUMES(USERNAME, SALARY, IS_ACTIVE, CREATED_DATE, UPDATE_TIME, APPLICANT_ID, CATEGORY_ID)
values (     'Java Developer',
             1000,
             true,
             CURRENT_DATE,
             CURRENT_TIMESTAMP,
             (select id from USERS where USERS.EMAIL like 'nargiza@example.com'),
             (select id from CATEGORIES where CATEGORY_NAME like 'IT')
       ),
       (
           'Frontend Developer',
           900,
           true,
           CURRENT_DATE,
           CURRENT_TIMESTAMP,
           (select id from USERS where USERS.EMAIL like 'nargiza@example.com'),
           (select id from CATEGORIES where CATEGORY_NAME like 'IT')
       );

insert into CONTACTS_INFO(contact_value, TYPE_ID, RESUME_ID)
values
    ('applicant1@example.com',
     (select id from CONTACT_TYPES where type = 'Email'),
     (select id from RESUMES where RESUMES.USERNAME = 'Frontend Developer')
    ),

    ('0500123456',
     (select id from CONTACT_TYPES where type = 'Phone'),
     (select id from RESUMES where RESUMES.USERNAME = 'Java Developer')
    );


insert into EDUCATION_INFO(INSTITUTION, PROGRAM, START_DATE, END_DATE, DEGREE, EDUCATION_INFO_RESUME_ID)
values
    ('Kyrgyz State Technical University',
     'Bachelor in Computer Science',
     '2016-09-01', '2020-06-30',
     'Bachelor',
     (select id from RESUMES where RESUMES.USERNAME = 'Frontend Developer')),
    ('International Design School',
     'Diploma in UI/UX Design',
     '2019-01-01',
     '2021-12-31',
     'Diploma',
     (select id from RESUMES where RESUMES.USERNAME = 'Frontend Developer'));

insert into RESPONDED_APPLICANTS(CONFIRMATION, RESUME_ID, VACANCY_ID)
values
    (
        true,
        (select id from RESUMES where RESUMES.USERNAME = 'Frontend Developer'),
        (select id from VACANCIES where VACANCIES.USERNAME='UI/UX Designer')
    ),
    (
        false,
        (select id from RESUMES where RESUMES.USERNAME = 'Java Developer'),
        (select id from VACANCIES where VACANCIES.USERNAME='Backend Developer')

    );

insert into MESSAGES(CONTENT, TIMESTAMP, RESPONDED_APPLICANTS_MESSAGES_ID)
values
    ('Здравствуйте, я очень заинтересован в вакансии!',
     '2025-03-31 10:15:00',
     (select id from RESPONDED_APPLICANTS where RESPONDED_APPLICANTS.CONFIRMATION = false limit 1)),
    ('Спасибо за ваш отклик, мы свяжемся с вами.',
     '2025-03-31 11:00:00',
     (select id from RESPONDED_APPLICANTS where RESPONDED_APPLICANTS.CONFIRMATION = false limit 1)),
    ('Добрый день! Есть ли возможность пройти собеседование онлайн?',
     '2025-03-31 12:30:00',
     (select id from RESPONDED_APPLICANTS where RESPONDED_APPLICANTS.CONFIRMATION = true limit 1));

insert into WORK_EXPERIENCE_INFO(YEARS, COMPANY_NAME, POSITION, RESPONSIBILITIES, WORK_EXPERIENCE_INFO_RESUME_ID)
values
    (2,
     'Tech Solutions',
     'Java Developer',
     'Разработка REST API на Spring Boot',
     (select id from RESUMES where RESUMES.USERNAME = 'Frontend Developer')),
    (1,
     'Creative Agency',
     'UI Designer',
     'Создание пользовательских интерфейсов',
     (select id from RESUMES where RESUMES.USERNAME = 'Java Developer')
     );