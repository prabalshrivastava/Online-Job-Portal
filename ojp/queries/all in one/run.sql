drop database ojp;
create database ojp;
use ojp;
create table company_followers
(
    company_followers_id		int	not null	primary key	,
    company_id			int	not null			,
    candidate_id			int	not null
);




create table contact_types
(
    contact_type_id		int		not null	primary key	,
    contact_type		varchar(30)	not null
);

alter table contact_types
    change contact_type
        contact_type varchar(20) not null unique;

alter table contact_types
    change contact_type_id
        contact_type_id int not null auto_increment;


insert into contact_types(contact_type) values('Permanent Address'),('Local Address'),('Mobile Number');
insert into contact_types(contact_type) values('Alternate Mobile No'),('Landline Number'),('Alt Land'),('Email'),('Alt Email');
create table language_capabilities
(
    language_capability_id	int		not null	primary key	,
    language_capability	varchar(30)	not null
);

alter table language_capabilities
    change language_capability_id
        language_capability_id int not null auto_increment;

insert into language_capabilities(language_capability) values('read') , ('write') , ('speak') ,('read/write') , ('read/speak') , ('write/speak');
insert into language_capabilities(language_capability) values('read/write/speak');

create table contacts
(
    contact_id		int		not null	primary key	,
    contact_type_id		int		not null			,
    user_id			int		not null			,
    contact			varchar(50)	not null
);

alter table contacts add constraint fk_contacts_contact_types
    foreign key(contact_type_id)
        references contact_types(contact_type_id);



alter table contacts
    change contact_id
        contact_id int not null auto_increment;



create table countries
(
    country_id		int		not null	primary key	,
    country			varchar(30)	not null
);

alter table countries
    change country_id
        country_id int not null auto_increment;

insert into countries(country) value ('india');
create table courses
(
    course_id		int		not null	primary key	,
    course			varchar(50)	not null			,
    course_type_id	int

);

alter table courses
    change course_id
        course_id int not null auto_increment;



insert into courses(course) values ('BE/BTECH - Aerospace Engineering'),('BE/BTECH - Chemical Engineering'),('BE/BTECH - Civil Engineering'),('BE/BTECH - Computer Science & Engineering'),('BE/BTECH - Electrical Engineering'),('BE/BTECH - Mechanical Engineering'),('BE/BTECH - Metallurgical Engineering'),('BE/BTECH - Physics'),('BE/BTECH - Chemistry');
create table course_types(
                             course_type_id	int	not null primary key auto_increment,
                             course_type varchar(50) null
);

insert into course_types(course_type) values('Diploma'),('Graduation'),('PostGraduation'),('School');
create table languages
(
    language_id		int		not null	primary key	,
    language		varchar(20)	not null
);

alter table languages
    change language_id
        language_id int not null auto_increment;

insert into languages(language) values('French'),('Malay-Indonesian'),('Portuguese'),('Bengali'),('Arabic'),('Russian'),('Spanish'),('Hindustani');

create table organization
(
    organization_id		int		not null	primary key	,
    organization		varchar(100)	not null			,
    organization_type_id	int		not null

);



alter table organization
    change organization_id
        organization_id int not null auto_increment;

insert into organization(organization,organization_type_id) values ('CBSE',1),('ICSE',1),('IB',1),('State board',1);
insert into organization(organization,organization_type_id) values ('Indian Institute of Science, Bangalore',2);
insert into organization(organization,organization_type_id) values ('Indian Institute of Technology, Kharagpur',2),('Indian Institute of Technology, Roorkee',2),	('Indian Institute of Technology, Kanpur',2),	('National Institute of Pharmaceutical Education and Research, Mohali',2),	('VIT University, Vellore',2);
insert into organization(organization,organization_type_id) values ('University of Delhi, Delhi',2),	('Indian Institute of Technology, Bombay',2),	('Indian Institute of Technology, Delhi',2),	('Tata Institute of Fundamental Research, Mumbai',2);
insert into organization(organization,organization_type_id) values ('Indian Institute of Technology, Guwahati',2),('Anna University, Chennai',2),	('Panjab University, Chandigarh',2),	('Indian Institute of Technology, Madras',2),	('Jawaharlal Nehru Centre for Advanced Scientific Research, Bangalore',2),	('All India Institute of Medical Sciences, New Delhi',2),	('Jadavpur University, Kolkata',2);
insert into organization(organization,organization_type_id) values ('Banaras Hindu University, Varanasi',2),	('Institute of Chemical Technology, Mumbai',2),	('Postgraduate Institute of Medical Education and Research, Chandigarh',2),	('Aligarh Muslim University, Aligarh',2),	('University of Calcutta, Kolkata',2);
insert into organization(organization,organization_type_id) values ('University of Hyderabad, Hyderabad',2),	('SASTRA University, Thanjavur',2),	('Bharathiar University, Coimbatore',2);
insert into organization(organization,organization_type_id) values ('Sanjay Gandhi Postgraduate Institute of Medical Sciences, Lucknow',2),	('Indian Institute of Science Education and Research, Pune',2),	('Indian Institute of Technology, Indore',2),	('Jawaharlal Nehru University, New Delhi',2),	('National Institute of Technology, Tiruchirappalli',2);
insert into organization(organization,organization_type_id) values ('Indian Institute of Science Education and Research, Kolkata',2);
insert into organization(organization,organization_type_id) values ('Annamalai University, Annamalai Nagar',2),	('Visva Bharati, Santinikaten',2),	('Indian Institute of Science Education and Research, Thiruvananthapuram',2),	('National Institute of Technology, Rourkela',2),	('Guru Nanak Dev University, Amritsar',2);
insert into organization(organization,organization_type_id) values ('University of Madras, Chennai',2),	('University of Rajasthan, Jaipur',2),	('University of Jammu, Jammu',2),	('Bharathidasan University, Tiruchirappalli',2),	('Shivaji University, Kolhapur',2),	('Indian Institute of Science Education and Research, Mohali',2);
insert into organization(organization,organization_type_id) values ('Indian Statistical Institute, Kolkata',2),	('University of Pune, Pune',2),	('Indian Agricultural Research Institute, New Delhi',2);
insert into organization(organization,organization_type_id) values ('Tezpur University, Tezpur',2),	('National Brain Research Centre, Gurgaon',2),	('Madurai Kamaraj University, Madurai',2);
insert into organization(organization,organization_type_id) values ('Indian Institute of Technology, Ropar',2),	('Sri Venkateswara University, Tirupati',2),	('University of Allahabad, Allahabad',2),	('University of Burdwan, Bardhaman',2),	('Pondicherry University, Puducherry',2),	('Thapar University, Patiala',2);
insert into organization(organization,organization_type_id) values ('National Institute of Mental Health and Neuro Sciences, Bangalore',2),	('Indian Institute of Engineering Science and Technology, Shibpur',2);
insert into organization(organization,organization_type_id) values ('Jawaharlal Nehru Technological University, Hyderabad',2),	('National Institute of Technology, Durgapur',2),	('Sree Chitra Tirunal Institute for Medical Sciences and Technology, Thiruvananthapuram',2),	('Birla Institute of Technology and Science, Pilani',2);




/*
insert into organization(organization,organization_type_id) values ('SRM University, Chennai',2),	('University of Lucknow, Lucknow',2),	('King George's Medical University, Lucknow',2);
insert into organization(organization,organization_type_id) values ('Indian School of Mines, Dhanbad',2),	('Jamia Hamdard, New Delhi',2),	('Sardar Vallabhbhai National Institute of Technology, Surat',2),	('Dr Harisingh Gour Vishwavidyalaya, Sagar',2);
insert into organization(organization,organization_type_id) values ('Indian Veterinary Research Institute, Izatnagar',2),	('Birla Institute of Technology, Mesra',2),	('ABV- Indian Institute of Information Technology and Management, Gwalior',2);
insert into organization(organization,organization_type_id) values ('Sri Ramachandra University, Chennai',2),	('Indian Institute of Science Education and Research, Bhopal',2),	('Forest Research Institute, Dehradun',2),	('Motilal Nehru National Institute of Technology, Allahabad',2);
insert into organization(organization,organization_type_id) values ('Amrita Vishwa Vidyapeetham University, Coimbatore',2),('Indian Institute of Technology, Hyderabad',2),	('University of Kalyani, Kalyani',2),	('Maharshi Dayanand University, Rohtak',2);
insert into organization(organization,organization_type_id) values ('Maharaja Sayajirao University of Baroda, Vadodara',2),	('Kuvempu University, Shankaraghatta',2);
*/
create table departments
(
    department_id		int		not null	primary key	,
    department		varchar(50)
);

alter table departments
    change department_id
        department_id int not null auto_increment;

insert into departments(department) values('Sales-marketing'),('customer-care'),('Production'),('Research and Development'),('Purchasing'),('Human Resource Management'),('Accounting and Finance.');
create table designations
(
    designation_id		int		not null	primary key	,
    designation		varchar(50)	not null
);

alter table designations
    change designation_id
        designation_id int not null auto_increment;

insert into designations(designation) values('Module Lead'),('Team Lead'),('Senior Team Lead'),('Account Manager'),('Architect'),('Technical Specialist'),('Delivery Manager'),('Delivery Head'),('Business Analyst'),('Delivery Partner');
insert into designations(designation) values('associate'),('software Engineer'),('senior Software Engineer'),('team Lead/Technical Lead'),('Project Manager/Project Lead/Technical Architect'),('senior PM/Senior PL'),('associate Director(Purely managerial)'),('director'),('associate Vice President(AVP)'),('vice-President'),('chief Executive Officer.');
insert into designations(designation) values('associate HR'),('executive'),('senior executive'),('hR Manager'),('senior HR Manager'),('aVP'),('vP'),('cFO(Chief Financial Officer)'),('accountant'),('senior Accountant'),('manager'),('senior Manager'),('aVP'),('vP'),('cFO');
create table experiences
(
    experience_id		int		not null	primary key	,
    candidate_id		int		not null			,
    organization_name	varchar(50)	not null			,
    industry_type_id	int		not null			,
    department_id		int		not null			,
    designation_id		int		not null			,
    joining_date		date		not null			,
    relieving_date		date						,
    ctc			int		not null
);


create table industry_types
(
    industry_type_id	int		not null	primary key	,
    industry_type		varchar(30)	not null
);

alter table industry_types
    change industry_type_id
        industry_type_id int not null auto_increment;

insert into industry_types(industry_type) values('IT-Software'),('Retail'),('IT-Hadrware'),('Marketing'),('Manufacturing');
create table job_qualification
(
    job_qualification_id		int	not null primary key auto_increment,
    job_id				int	not null			,
    course_id			int	not null
);


insert into job_qualification(job_id,course_id) values (6,1),(7,2),(8,4),(9,5);
insert into job_qualification(job_id,course_id) values (6,6),(7,9),(8,7),(9,8);
insert into job_qualification(job_id,course_id) values (10,1),(11,2),(12,4),(3,5);
insert into job_qualification(job_id,course_id) values (5,7),(4,8);
create table organization_types
(
    organization_type_id	int		not null	primary key	auto_increment,
    organization_id		varchar(50)	not null	unique

);

insert into organization_types(organization_id) values('BOARD'),('UNIVERSITY');




create table pin_codes
(
    pin_code_id		int		not null	primary key	auto_increment,
    pin_code		varchar(10)	not null			,
    city_id			int		not null
);


insert into pin_codes(pin_code,city_id) values('482001',1),('482002',1),('482004',1),('223487',3),('213323',3);
insert into pin_Codes(pin_code,city_id) values('483001',2),('483773',2),('482053',2),('483775',2),('483222',2),('482006',5),('482010',5),('482051',5),('483220',5),('483221',5),('483445',5),('483880',4),('482015',4),('483004',4);
insert into pin_Codes(pin_code,city_id) values('583001',6),('583773',6),('582053',6),('583775',6),('583222',6),('582006',7),('582010',7),('582051',7),('583220',7),('583221',7),('583555',7),('583880',7),('582015',7),('583005',7);
insert into pin_codes(pin_code,city_id) values('40012',23),('40220',23),('40204',24),('34237',24),('33323',23);
insert into pin_codes(pin_code,city_id) values('488008',8),('488908',14),('488004',15),('889487',9),('889989',16);
insert into pin_codes(pin_code,city_id) values('412010',21),('410010',20),('41004',10),('10101',11),('101011',17);
insert into pin_codes(pin_code,city_id) values('421012',18),('420012',19),('42004',12),('2134127',13),('213133',22);


create table job_score_criteria
(
    job_score_criteria_id		int	not null	primary key	auto_increment,
    job_id				int	not null			,
    ssc_score			int	not null			,
    hsc_diploma_score		int	not null			,
    graduation			int	not null
);



insert into job_score_criteria(job_id,ssc_score,hsc_diploma_score,graduation) values(3,123781,39481093,12873817),(4,123781,39481093,12873817),(5,123781,39481093,12873817),(11,123781,39481093,12873817),(12,123781,39481093,12873817);



create table job_shift
(
    job_shift_id		int		not null	primary key	auto_increment,
    job_id			int		not null			,
    start_hour		time		not null			,
    end_hour		time		not null
);

insert into job_shift(job_id,start_hour,end_hour) values(3,'23:58:00','12:58:00'),(4,'23:58:00','12:58:00'),(5,'23:58:00','12:58:00'),(6,'23:58:00','12:58:00'),(7,'23:58:00','12:58:00'),(8,'23:58:00','12:58:00'),(9,'23:58:00','12:58:00'),(10,'23:58:00','12:58:00'),(11,'23:58:00','12:58:00'),(12,'23:58:00','12:58:00');
create table jobs_skills
(
    job_skill_id		int		not null	primary key	auto_increment,
    job_id			int		not null			,
    skill_id		int		not null
);


insert into jobs_skills(job_id,skill_id) values (3,2),(4,3),(5,4);
insert into jobs_skills(job_id,skill_id) values (6,3),(7,4),(8,5);
insert into jobs_skills(job_id,skill_id) values (9,3),(11,4);
insert into jobs_skills(job_id,skill_id) values (10,3),(12,4);
create table jobs
(
    job_id			int		not null	primary key	,
    company_id		int		not null			,
    job_title		varchar(50)	not null			,
    branch_id		int		not null			,
    department_id		int		not null			,
    designation_id		int		not null			,
    no_of_vacancies		int		not null			,
    launch_date		date		not null			,
    apply_last_date		date		not null			,
    job_description		varchar(3000)	not null			,
    experience_min		int		not null			,
    experience_max		int		not null			,
    ctc_min			int		not null			,
    ctc_max			int		not null
);

alter table jobs
    change job_id
        job_id int not null auto_increment;
create table projects
(
    project_id	int		not null	primary key	,
    title		varchar(30)	not null			,
    candidate_id	int		not null			,
    members		int		not null			,
    duration	varchar(20)	not null			,
    details		varchar(2000)	not null			,
    other_details	varchar(1000)
);

create table qualifications
(
    qualification_id	int		not null	primary key	,
    course_id		int		not null			,
    candidate_id		int		not null			,
    organization_id		int		not null			,
    passing_year		date		not null			,
    score			float		not null			,
    certificate_path	varchar(150)
);

create table skills
(
    skill_id		int		not null	primary key	auto_increment,
    skill			varchar(20)	not null	unique
);


insert into skills(skill) values ('Logo'),('Lua'),('Javascript'),('Python'),('Ruby'),('Lolcode'),('Cobol'),('Basic'),('Java'),('C#'),('Pascal'),('C'),('Objective C'),('Fortran'),('Smalltalk'),('C++'),('Haskell'),('Lisp'),('Verilog'),('Assembly');


create table states
(
    state_id		int		not null	 primary key	auto_increment,
    state			varchar(30)	not null
);


alter table states
    add column country_id int,
    add constraint fk_states_countries foreign key(country_id) references countries(country_id);

set foreign_key_checks = 0;

alter table states
    change country_id
        country_id int not null;

insert into states(state,country_id) value('mp',1);
insert into states(state,country_id) values('up',1),('ap',1),('jk',1);
insert into states(state,country_id) values('arizona',2),('texas',2);


create table status
(
    status_id		int		not null	primary key	,
    status			varchar(15)	not null
);

alter table status
    change status_id
        status_id int not null auto_increment;
create table user_addresses
(
    user_address_id		int		not null	primary key	,
    user_id			int		not null			,
    contact_type_id		int		not null			,
    pin_code_id		int		not null			,
    address			varchar(300)	not null
);

alter table user_addresses
    add constraint fk_user_addresses_users
        foreign key(user_id)
            references users(user_id);

alter table user_addresses
    add constraint fk_user_addresses_contact_types
        foreign key(contact_type_id)
            references contact_types(contact_type_id);

alter table user_addresses
    add constraint fk_user_addresses_pin_codes
        foreign key(pin_code_id)
            references pin_codes(pin_code_id);

alter table user_addresses
    change user_address_id
        user_address_id int not null auto_increment;

create table user_types
(
    user_type_id		int		not null	primary	key	,
    user_type		varchar(20)	not null
);

alter table user_types
    change user_type_id
        user_type_id int not null auto_increment;

insert into user_types(user_type)
values ('candidate'),('company');






create table users
(
    user_id		int		not null	primary key	,
    user_name	varchar(50)	not null			,
    email		varchar(50)	not null	unique		,
    password	varchar(30)	not null			,
    user_type_id	int		not null
);

alter table users add constraint fk_users_user_types
    foreign key(user_type_id)
        references user_types(user_type_id);

alter table users change user_id
    user_id int
        not null  auto_increment;

create table applicants
(
    applicant_id		int		not null	primary key	,
    job_id			int		not null			,
    candidate_id		int		not null			,
    status_id		int		not null			,
    apply_date		date		not null
);

alter table applicants add constraint fk_applicants_jobs
    foreign key(job_id)
        references jobs(job_id);

alter table applicants add constraint fk_applicants_candidates
    foreign key(candidate_id)
        references candidates(candidate_id);

alter table applicants add constraint fk_applicants_status
    foreign key(status_id)
        references status(status_id);

alter table applicants
    change applicant_id
        applicant_id int not null auto_increment;

create table branches
(
    branch_id	int		not null	primary key	,
    branch_name	varchar(50)	not null			,
    address		varchar(500)	not null			,
    pin_code_id	int		not null			,
    contact_person	varchar(50)	not null			,
    conntact_number	varchar(50)	not null			,
    email		varchar(50)	not null			,
    company_id	int		not null
);
alter table branches change email email varchar(50) not null unique;

alter table branches add constraint fk_branches_pin_codes
    foreign key(pin_code_id)
        references pin_codes(pin_code_id);

alter table branches add constraint fk_branches_companies
    foreign key(company_id)
        references companies(company_id);

alter table branches
    change branch_id
        branch_id int not null auto_increment;
create table candidate_language
(
    candidate_language_id		int	not null	primary key	,
    language_id			int	not null			,
    language_capability_id		int	not null
);

alter table candidate_language
    add constraint fk_candidate_language_laguages
        foreign key(language_id)
            references languages(language_id);

alter table candidate_language
    add constraint fk_candidate_language_language_capabilities
        foreign key(language_capability_id)
            references language_capabilities(language_capability_id);

alter table candidate_language
    change candidate_language_id
        candidate_language_id int not null auto_increment;

alter table candidate_language
    add column candidate_id int not null;

alter table candidate_language
    add constraint fk_candidate_language_candidate
        foreign key(candidate_id)
            references candidates(candidate_id);
create table candidates
(
    candidate_id		int		not null primary key	,
    date_of_birth		date		not null		,
    gender			boolean		not null		,
    hobbies			varchar(100)				,
    user_id			int		not null		,
    marital_status		boolean		not null		,
    country_id		int		not null		,
    intrests		varchar(1000)				,
    profile_pic_path	varchar(100)				,
    resume_file_path	varchar(100)				,
    resume_upload_date	date					,
    strengths		varchar(1000)				,
    weaknesses		varchar(1000)				,
    curricular_activity	varchar(1000)
);

alter table candidates add constraint fk_candidates_users foreign key(user_id)
    references users(user_id);

alter table candidates add constraint fk_candidates_countries
    foreign key(country_id)
        references countries(country_id);

alter table candidates
    change candidate_id
        candidate_id int not null auto_increment;

alter table candidates
    change date_of_birth
        date_of_birth date;

alter table candidates
    change gender
        gender boolean;

alter table candidates
    change marital_status
        marital_status boolean;

alter table candidates
    change country_id
        country_id int;

alter table candidates drop foreign key fk_candidates_users;

alter table candidates add column father_name varchar(50),add column mother_name varchar(50);
create table candidate_skills
(
    candidate_skill_id	int		not null	primary key	,
    candidate_id		int		not null			,
    skill_id		int		not null			,
    version			varchar(10)					,
    duration		varchar(20)	not null
);

alter table candidate_skills add constraint fk_candidate_skills_candidates
    foreign key(candidate_id)
        references candidates(candidate_id);

alter table candidate_skills add constraint fk_candidate_skills_skills
    foreign key(skill_id)
        references skills(skill_id);

alter table candidate_skills
    change candidate_skill_id
        candidate_skill_id int not null auto_increment;

alter table candidate_skills
    add column certificate varchar(50);
create table cities
(
    city_id			int		not null	primary key	,
    city			varchar(30)	not null			,
    state_id		int		not null
);

alter table cities add constraint fk_cities_states
    foreign key(state_id)
        references states(state_id);

alter table cities
    change city_id
        city_id int not null auto_increment;

insert into cities(city,state_id) values('indore',1),('bhopal',1),('gwalior',1);
insert into cities(city,state_id) values('kanpur',2),('allahabad',2),('luckhnow',2);
insert into cities(city,state_id) values('Visakhapatnam',3),('Vijayawada',3),('Krishna',3),('Guntur',3),('Nellore',3);
insert into cities(city,state_id) values('srinagar',4),('kashmir',4),('jammu',4);
insert into cities(city,state_id) values('tempe',5),('messa',5),('flagstaff',5);
insert into cities(city,state_id) values('Austin',6),('Dallas',6),('San Antonio',6),('Houston',6),('Lubbock',6),('El Paso',6);


create table companies
(
    company_id		int		not null	primary key	,
    user_id			int						,
    industry_type_id	int		not null				,
    logo_path		varchar(50)					,
    website			varchar(50)					,
    mission			varchar(2000)					,
    vision			varchar(2000)					,
    history			varchar(2000)					,
    awards			varchar(2000)					,
    about_us		varchar(2000)
);

alter table companies add constraint fk_companies_users
    foreign key(user_id)
        references users(user_id);

alter table companies add constraint fk_companies_industry_types
    foreign key(industry_type_id)
        references industry_types(industry_type_id);

alter table companies
    change company_id
        company_id int not null auto_increment;

alter table companies
    change industry_type_id
        industry_type_id int;

alter table organization
    add constraint fk_organization_organization_types
        foreign key(organization_type_id)
            references organization_types(organization_type_id);



alter table experiences add constraint fk_experiences_candidates
    foreign key(candidate_id)
        references candidates(candidate_id);

alter table experiences add constraint fk_experiences_industry_types
    foreign key(industry_type_id)
        references industry_types(industry_type_id);

alter table experiences add constraint fk_experiences_departments
    foreign key(department_id)
        references departments(department_id);

alter table experiences add constraint fk_experiences_designations
    foreign key(designation_id)
        references designations(designation_id);

alter table experiences
    change experience_id
        experience_id int not null auto_increment;

alter table job_qualification add constraint
    foreign key(job_id)
        references jobs(job_id);

alter table job_qualification add constraint
    foreign key(course_id)
        references courses(course_id);

alter table job_qualification
    change job_qualification_id
        job_qualification_id int not null auto_increment;



alter table pin_codes
    change pin_code
        pin_code int not null unique;

alter table pin_codes add constraint fk_pin_codes_cities
    foreign key(city_id)
        references cities(city_id);


alter table pin_codes
    change pin_code_id
        pin_code_id int not null auto_increment;


alter table job_score_criteria add constraint
    foreign key(job_id)
        references jobs(job_id);



alter table job_score_criteria add constraint fk_job_score_criteria_jobs
    foreign key(job_id)
        references jobs(job_id);

alter table job_shift add constraint fk_job_shift_jobs
    foreign key (job_id)
        references jobs(job_id);

alter table job_shift
    change job_shift_id
        job_shift_id int not null auto_increment;

alter table jobs_skills add constraint
    foreign key(job_id)
        references jobs(job_id);

alter table jobs_skills add constraint
    foreign key(skill_id)
        references skills(skill_id);

alter table jobs_skills
    change job_skill_id
        job_skill_id int not null auto_increment;

alter table jobs add constraint fk_jobs_companies
    foreign key(company_id)
        references companies(company_id);



alter table jobs add constraint fk_jobs_branches
    foreign key(branch_id)
        references branches(branch_id);

alter table jobs add constraint fk_jobs_departments
    foreign key(department_id)
        references departments(department_id);

alter table jobs add constraint fk_jobs_designations
    foreign key(designation_id)
        references designations(designation_id);


show indexes from jobs;
alter table jobs drop foreign key fk_jobs_companies;
alter table jobs drop column company_id;

alter table projects add constraint fk_projects_candidates
    foreign key(candidate_id)
        references candidates(candidate_id);

alter table projects
    change project_id
        project_id int not null auto_increment;



alter table skills
    change skill_id
        skill_id int not null auto_increment;



alter table qualifications add constraint fk_qualifications_courses
    foreign key(course_id)
        references courses(course_id);

alter table qualifications add constraint fk_qualifications_candidates
    foreign key(candidate_id)
        references candidates(candidate_id);

alter table qualifications add constraint fk_qualifications_oragnization
    foreign key(organization_id)
        references organization(organization_id);

alter table qualifications
    change qualification_id
        qualification_id int not null auto_increment;



create view job_search as
select * from jobs j
                  NATURAL JOIN branches b
                  NATURAL JOIN departments dep
                  NATURAL JOIN designations des
                  NATURAL JOIN pin_codes p
                  NATURAL JOIN cities c
                  NATURAL JOIN states s
                  NATURAL JOIN countries cnt
                  NATURAL JOIN industry_types it
                  NATURAL JOIN companies com
                  NATURAL JOIN jobs_skills jsk
                  NATURAL JOIN job_score_criteria jsc
                  NATURAL JOIN job_qualification jq
                  NATURAL JOIN job_shift jsh;


create view job_skills_qualifications
as
select * from jobs_skills
                  natural join job_qualification;