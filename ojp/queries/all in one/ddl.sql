create table if not exists company_followers
(
    company_followers_id int not null
        primary key,
    company_id           int not null,
    candidate_id         int not null
);

create table if not exists contact_types
(
    contact_type_id int auto_increment
        primary key,
    contact_type    varchar(20) not null,
    constraint contact_type
        unique (contact_type)
);

create table if not exists contacts
(
    contact_id      int auto_increment
        primary key,
    contact_type_id int         not null,
    user_id         int         not null,
    contact         varchar(50) not null,
    constraint fk_contacts_contact_types
        foreign key (contact_type_id) references contact_types (contact_type_id)
);

create table if not exists countries
(
    country_id int auto_increment
        primary key,
    country    varchar(30) not null
);

create table if not exists candidates
(
    candidate_id        int auto_increment
        primary key,
    date_of_birth       date          null,
    gender              tinyint(1)    null,
    hobbies             varchar(100)  null,
    user_id             int           not null,
    marital_status      tinyint(1)    null,
    country_id          int           null,
    intrests            varchar(1000) null,
    profile_pic_path    varchar(100)  null,
    resume_file_path    varchar(100)  null,
    resume_upload_date  date          null,
    strengths           varchar(1000) null,
    weaknesses          varchar(1000) null,
    curricular_activity varchar(1000) null,
    father_name         varchar(50)   null,
    mother_name         varchar(50)   null,
    constraint fk_candidates_countries
        foreign key (country_id) references countries (country_id)
);

create index fk_candidates_users
    on candidates (user_id);

create table if not exists course_types
(
    course_type_id int auto_increment
        primary key,
    course_type    varchar(50) null
);

create table if not exists courses
(
    course_id      int auto_increment
        primary key,
    course         varchar(50) not null,
    course_type_id int         null
);

create table if not exists departments
(
    department_id int auto_increment
        primary key,
    department    varchar(50) null
);

create table if not exists designations
(
    designation_id int auto_increment
        primary key,
    designation    varchar(50) not null
);

create table if not exists industry_types
(
    industry_type_id int auto_increment
        primary key,
    industry_type    varchar(30) not null
);

create table if not exists experiences
(
    experience_id     int auto_increment
        primary key,
    candidate_id      int         not null,
    organization_name varchar(50) not null,
    industry_type_id  int         not null,
    department_id     int         not null,
    designation_id    int         not null,
    joining_date      date        not null,
    relieving_date    date        null,
    ctc               int         not null,
    constraint fk_experiences_candidates
        foreign key (candidate_id) references candidates (candidate_id),
    constraint fk_experiences_departments
        foreign key (department_id) references departments (department_id),
    constraint fk_experiences_designations
        foreign key (designation_id) references designations (designation_id),
    constraint fk_experiences_industry_types
        foreign key (industry_type_id) references industry_types (industry_type_id)
);

create table if not exists language_capabilities
(
    language_capability_id int auto_increment
        primary key,
    language_capability    varchar(30) not null
);

create table if not exists languages
(
    language_id int auto_increment
        primary key,
    language    varchar(20) not null
);

create table if not exists candidate_language
(
    candidate_language_id  int auto_increment
        primary key,
    language_id            int not null,
    language_capability_id int not null,
    candidate_id           int not null,
    constraint fk_candidate_language_candidate
        foreign key (candidate_id) references candidates (candidate_id),
    constraint fk_candidate_language_laguages
        foreign key (language_id) references languages (language_id),
    constraint fk_candidate_language_language_capabilities
        foreign key (language_capability_id) references language_capabilities (language_capability_id)
);

create table if not exists organization_types
(
    organization_type_id int auto_increment
        primary key,
    organization_id      varchar(50) not null,
    constraint organization_id
        unique (organization_id)
);

create table if not exists organization
(
    organization_id      int auto_increment
        primary key,
    organization         varchar(100) not null,
    organization_type_id int          not null,
    constraint fk_organization_organization_types
        foreign key (organization_type_id) references organization_types (organization_type_id)
);

create table if not exists projects
(
    project_id    int auto_increment
        primary key,
    title         varchar(30)   not null,
    candidate_id  int           not null,
    members       int           not null,
    duration      varchar(20)   not null,
    details       varchar(2000) not null,
    other_details varchar(1000) null,
    constraint fk_projects_candidates
        foreign key (candidate_id) references candidates (candidate_id)
);

create table if not exists qualifications
(
    qualification_id int auto_increment
        primary key,
    course_id        int          not null,
    candidate_id     int          not null,
    organization_id  int          not null,
    passing_year     date         not null,
    score            float        not null,
    certificate_path varchar(150) null,
    constraint fk_qualifications_candidates
        foreign key (candidate_id) references candidates (candidate_id),
    constraint fk_qualifications_courses
        foreign key (course_id) references courses (course_id),
    constraint fk_qualifications_oragnization
        foreign key (organization_id) references organization (organization_id)
);

create table if not exists skills
(
    skill_id int auto_increment
        primary key,
    skill    varchar(20) not null,
    constraint skill
        unique (skill)
);

create table if not exists candidate_skills
(
    candidate_skill_id int auto_increment
        primary key,
    candidate_id       int         not null,
    skill_id           int         not null,
    version            varchar(10) null,
    duration           varchar(20) not null,
    certificate        varchar(50) null,
    constraint fk_candidate_skills_candidates
        foreign key (candidate_id) references candidates (candidate_id),
    constraint fk_candidate_skills_skills
        foreign key (skill_id) references skills (skill_id)
);

create table if not exists states
(
    state_id   int auto_increment
        primary key,
    state      varchar(30) not null,
    country_id int         not null,
    constraint fk_states_countries
        foreign key (country_id) references countries (country_id)
);

create table if not exists cities
(
    city_id  int auto_increment
        primary key,
    city     varchar(30) not null,
    state_id int         not null,
    constraint fk_cities_states
        foreign key (state_id) references states (state_id)
);

create table if not exists pin_codes
(
    pin_code_id int auto_increment
        primary key,
    pin_code    int not null,
    city_id     int not null,
    constraint pin_code
        unique (pin_code),
    constraint fk_pin_codes_cities
        foreign key (city_id) references cities (city_id)
);

create table if not exists status
(
    status_id int auto_increment
        primary key,
    status    varchar(15) not null
);

create table if not exists user_types
(
    user_type_id int auto_increment
        primary key,
    user_type    varchar(20) not null
);

create table if not exists users
(
    user_id      int auto_increment
        primary key,
    user_name    varchar(50) not null,
    email        varchar(50) not null,
    password     varchar(30) not null,
    user_type_id int         not null,
    constraint email
        unique (email),
    constraint fk_users_user_types
        foreign key (user_type_id) references user_types (user_type_id)
);

create table if not exists companies
(
    company_id       int auto_increment
        primary key,
    user_id          int           null,
    industry_type_id int           null,
    logo_path        varchar(50)   null,
    website          varchar(50)   null,
    mission          varchar(2000) null,
    vision           varchar(2000) null,
    history          varchar(2000) null,
    awards           varchar(2000) null,
    about_us         varchar(2000) null,
    constraint fk_companies_industry_types
        foreign key (industry_type_id) references industry_types (industry_type_id),
    constraint fk_companies_users
        foreign key (user_id) references users (user_id)
);

create table if not exists branches
(
    branch_id       int auto_increment
        primary key,
    branch_name     varchar(50)  not null,
    address         varchar(500) not null,
    pin_code_id     int          not null,
    contact_person  varchar(50)  not null,
    conntact_number varchar(50)  not null,
    email           varchar(50)  not null,
    company_id      int          not null,
    constraint email
        unique (email),
    constraint fk_branches_companies
        foreign key (company_id) references companies (company_id),
    constraint fk_branches_pin_codes
        foreign key (pin_code_id) references pin_codes (pin_code_id)
);

create table if not exists jobs
(
    job_id          int auto_increment
        primary key,
    job_title       varchar(50)   not null,
    branch_id       int           not null,
    department_id   int           not null,
    designation_id  int           not null,
    no_of_vacancies int           not null,
    launch_date     date          not null,
    apply_last_date date          not null,
    job_description varchar(3000) not null,
    experience_min  int           not null,
    experience_max  int           not null,
    ctc_min         int           not null,
    ctc_max         int           not null,
    constraint fk_jobs_branches
        foreign key (branch_id) references branches (branch_id),
    constraint fk_jobs_departments
        foreign key (department_id) references departments (department_id),
    constraint fk_jobs_designations
        foreign key (designation_id) references designations (designation_id)
);

create table if not exists applicants
(
    applicant_id int auto_increment
        primary key,
    job_id       int  not null,
    candidate_id int  not null,
    status_id    int  not null,
    apply_date   date not null,
    constraint fk_applicants_candidates
        foreign key (candidate_id) references candidates (candidate_id),
    constraint fk_applicants_jobs
        foreign key (job_id) references jobs (job_id),
    constraint fk_applicants_status
        foreign key (status_id) references status (status_id)
);

create table if not exists job_qualification
(
    job_qualification_id int auto_increment
        primary key,
    job_id               int not null,
    course_id            int not null,
    constraint job_qualification_ibfk_1
        foreign key (job_id) references jobs (job_id),
    constraint job_qualification_ibfk_2
        foreign key (course_id) references courses (course_id)
);

create index course_id
    on job_qualification (course_id);

create index job_id
    on job_qualification (job_id);

create table if not exists job_score_criteria
(
    job_score_criteria_id int auto_increment
        primary key,
    job_id                int not null,
    ssc_score             int not null,
    hsc_diploma_score     int not null,
    graduation            int not null,
    constraint job_score_criteria_ibfk_1
        foreign key (job_id) references jobs (job_id),
    constraint fk_job_score_criteria_jobs
        foreign key (job_id) references jobs (job_id)
);

create table if not exists job_shift
(
    job_shift_id int auto_increment
        primary key,
    job_id       int  not null,
    start_hour   time not null,
    end_hour     time not null,
    constraint fk_job_shift_jobs
        foreign key (job_id) references jobs (job_id)
);

create table if not exists jobs_skills
(
    job_skill_id int auto_increment
        primary key,
    job_id       int not null,
    skill_id     int not null,
    constraint jobs_skills_ibfk_1
        foreign key (job_id) references jobs (job_id),
    constraint jobs_skills_ibfk_2
        foreign key (skill_id) references skills (skill_id)
);

create index job_id
    on jobs_skills (job_id);

create index skill_id
    on jobs_skills (skill_id);

create table if not exists user_addresses
(
    user_address_id int auto_increment
        primary key,
    user_id         int          not null,
    contact_type_id int          not null,
    pin_code_id     int          not null,
    address         varchar(300) not null,
    constraint fk_user_addresses_contact_types
        foreign key (contact_type_id) references contact_types (contact_type_id),
    constraint fk_user_addresses_pin_codes
        foreign key (pin_code_id) references pin_codes (pin_code_id),
    constraint fk_user_addresses_users
        foreign key (user_id) references users (user_id)
);

create or replace definer = root@localhost view job_search as
select `j`.`job_id`                  AS `job_id`,
       `b`.`company_id`              AS `company_id`,
       `it`.`industry_type_id`       AS `industry_type_id`,
       `s`.`country_id`              AS `country_id`,
       `c`.`state_id`                AS `state_id`,
       `p`.`city_id`                 AS `city_id`,
       `b`.`pin_code_id`             AS `pin_code_id`,
       `j`.`designation_id`          AS `designation_id`,
       `j`.`department_id`           AS `department_id`,
       `j`.`branch_id`               AS `branch_id`,
       `j`.`job_title`               AS `job_title`,
       `j`.`no_of_vacancies`         AS `no_of_vacancies`,
       `j`.`launch_date`             AS `launch_date`,
       `j`.`apply_last_date`         AS `apply_last_date`,
       `j`.`job_description`         AS `job_description`,
       `j`.`experience_min`          AS `experience_min`,
       `j`.`experience_max`          AS `experience_max`,
       `j`.`ctc_min`                 AS `ctc_min`,
       `j`.`ctc_max`                 AS `ctc_max`,
       `b`.`branch_name`             AS `branch_name`,
       `b`.`address`                 AS `address`,
       `b`.`contact_person`          AS `contact_person`,
       `b`.`conntact_number`         AS `conntact_number`,
       `b`.`email`                   AS `email`,
       `dep`.`department`            AS `department`,
       `des`.`designation`           AS `designation`,
       `p`.`pin_code`                AS `pin_code`,
       `c`.`city`                    AS `city`,
       `s`.`state`                   AS `state`,
       `cnt`.`country`               AS `country`,
       `it`.`industry_type`          AS `industry_type`,
       `com`.`user_id`               AS `user_id`,
       `com`.`logo_path`             AS `logo_path`,
       `com`.`website`               AS `website`,
       `com`.`mission`               AS `mission`,
       `com`.`vision`                AS `vision`,
       `com`.`history`               AS `history`,
       `com`.`awards`                AS `awards`,
       `com`.`about_us`              AS `about_us`,
       `jsk`.`job_skill_id`          AS `job_skill_id`,
       `jsk`.`skill_id`              AS `skill_id`,
       `jsc`.`job_score_criteria_id` AS `job_score_criteria_id`,
       `jsc`.`ssc_score`             AS `ssc_score`,
       `jsc`.`hsc_diploma_score`     AS `hsc_diploma_score`,
       `jsc`.`graduation`            AS `graduation`,
       `jq`.`job_qualification_id`   AS `job_qualification_id`,
       `jq`.`course_id`              AS `course_id`,
       `jsh`.`job_shift_id`          AS `job_shift_id`,
       `jsh`.`start_hour`            AS `start_hour`,
       `jsh`.`end_hour`              AS `end_hour`
from (((((((((((((`ojp`.`jobs` `j` join `ojp`.`branches` `b` on ((`j`.`branch_id` = `b`.`branch_id`))) join `ojp`.`departments` `dep` on ((`j`.`department_id` = `dep`.`department_id`))) join `ojp`.`designations` `des` on ((`j`.`designation_id` = `des`.`designation_id`))) join `ojp`.`pin_codes` `p` on ((`b`.`pin_code_id` = `p`.`pin_code_id`))) join `ojp`.`cities` `c` on ((`p`.`city_id` = `c`.`city_id`))) join `ojp`.`states` `s` on ((`c`.`state_id` = `s`.`state_id`))) join `ojp`.`countries` `cnt` on ((`s`.`country_id` = `cnt`.`country_id`))) join `ojp`.`industry_types` `it`) join `ojp`.`companies` `com` on ((
        (`b`.`company_id` = `com`.`company_id`) and
        (`it`.`industry_type_id` = `com`.`industry_type_id`)))) join `ojp`.`jobs_skills` `jsk` on ((`j`.`job_id` = `jsk`.`job_id`))) join `ojp`.`job_score_criteria` `jsc` on ((`j`.`job_id` = `jsc`.`job_id`))) join `ojp`.`job_qualification` `jq` on ((`j`.`job_id` = `jq`.`job_id`)))
         join `ojp`.`job_shift` `jsh` on ((`j`.`job_id` = `jsh`.`job_id`)));

create or replace definer = root@localhost view job_skills_qualifications as
select `ojp`.`jobs_skills`.`job_id`                     AS `job_id`,
       `ojp`.`jobs_skills`.`job_skill_id`               AS `job_skill_id`,
       `ojp`.`jobs_skills`.`skill_id`                   AS `skill_id`,
       `ojp`.`job_qualification`.`job_qualification_id` AS `job_qualification_id`,
       `ojp`.`job_qualification`.`course_id`            AS `course_id`
from (`ojp`.`jobs_skills`
         join `ojp`.`job_qualification` on ((`ojp`.`jobs_skills`.`job_id` = `ojp`.`job_qualification`.`job_id`)));

