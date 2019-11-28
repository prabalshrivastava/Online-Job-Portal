package models.userinfo;

import java.sql.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import utilities.Db;

import models.*;

public class CompanyJobs{
	Job job;
	Branch branch;
	Department department;
	Designation designation;
	PinCode pinCode;
	City city;
	State state;
	Country country;
	IndustryType industryType;
	Company company;
	ArrayList<JobSkill> jobSkills;
	JobScoreCriteria jobScoreCriteria;
	ArrayList<JobQualification> jobQualifications;
	JobShift jobShift;

	public CompanyJobs(){
	}

	public CompanyJobs(Job job){
		setJob(job);
		setJobSkills(JobSkill.collectJobSkills(job,null));
		setJobQualifications(JobQualification.collectJobQualifications(job,null));
	}

	public CompanyJobs(Branch branch,Department department,Designation designation,PinCode pinCode,City city,State state,Country country,IndustryType industryType,Company company,ArrayList<JobSkill> jobSkills,JobScoreCriteria jobScoreCriteria,ArrayList<JobQualification> jobQualifications,JobShift jobShift){
		setBranch(branch);
		setDepartment(department);
		setDesignation(designation);
		setPinCode(pinCode);
		setCity(city);
		setState(state);
		setCountry(country);
		setIndustryType(industryType);
		setCompany(company);
		setJobSkills(jobSkills);
		setJobScoreCriteria(jobScoreCriteria);
		setJobQualifications(jobQualifications);
		setJobShift(jobShift);
	}
	public CompanyJobs(Integer job,Integer branch,Integer department,Integer designation,Integer pinCode,Integer city,Integer state,Integer country,Integer industryType,Integer company,Integer jobSkills,Integer jobScoreCriteria,Integer jobQualifications,Integer jobShift){
		this(Job.getJobObject(job));
		//this is redundant no need
		/*
		setBranch(Branch.getBranchObject(branch));
		setDepartment(Department.getDepartmentObject(department,null));
		setDesignation(Designation.getDesignationObject(designation,null));
		setPinCode(PinCode.getPinCodeObject(pinCode,null,null));
		setCity(City.getCityObject(city,null,null));
		setState(State.getStateObject(state,null,null));
		setCountry(Country.getCountryObject(country,null));
		setIndustryType(IndustryType.getIndustryTypeObject(industryType,null));
		setCompany(Company.getCompanyObject(new Company(company),null));
		*/
		setJobShift(JobShift.getJobShiftObject(jobShift,null,null,null));
		setJobScoreCriteria(JobScoreCriteria.getJobScoreCriteriaObject(jobScoreCriteria,null,null,null,null));
		/*
		setJobSkills(JobSkill.getJobSkillObject(jobSkills,null,null));
		setJobQualifications(JobQualification.getJobQualificationObject(jobQualifications,null,null));
		*/
	}




	public String toString(){
		return "\n{\n\t'job' : " + job + ",\n\t'branch' : " + branch + ",\n\t'department' : " + department + ",\n\t'designation' : " + designation + ",\n\t'pinCode' : " + pinCode + ",\n\t'city' : " + city + ",\n\t'state' : " + state + ",\n\t'country' : " + country + ",\n\t'industryType' : " + industryType + ",\n\t'company' : " + company + ",\n\t'jobSkills' : " + jobSkills + ",\n\t'jobScoreCriteria' : " + jobScoreCriteria + ",\n\t'jobQualifications' : " + jobQualifications + ",\n\t'jobShift' : " + jobShift + "\n}";
	}


	
/*
	+------+----------+----------------+----------+--------+-------+-----------+--------------+-------------+---------+---------+---------------+-----------+---------------+---------------+--------------+--------------+-------+-------+-----------+-------+--------------+--------------+-----+-------------+---------------+--------+------+-----+-------+-------------+-------+---------+-------+-------+------+-------+------+--------+------------+--------+---------------------+---------+-----------------+----------+--------------------+---------+------------+----------+--------+
	|job_id|company_id|industry_type_id|country_id|state_id|city_id|pin_code_id|designation_id|department_id|branch_id|job_title|no_of_vacancies|launch_date|apply_last_date|job_description|experience_min|experience_max|ctc_min|ctc_max|branch_name|address|contact_person|contact_number|email|department   |designation    |pin_code|city  |state|country|industry_type|user_id|logo_path|website|mission|vision|history|awards|about_us|job_skill_id|skill_id|job_score_criteria_id|ssc_score|hsc_diploma_score|graduation|job_qualification_id|course_id|job_shift_id|start_hour|end_hour|
	+------+----------+----------------+----------+--------+-------+-----------+--------------+-------------+---------+---------+---------------+-----------+---------------+---------------+--------------+--------------+-------+-------+-----------+-------+--------------+--------------+-----+-------------+---------------+--------+------+-----+-------+-------------+-------+---------+-------+-------+------+-------+------+--------+------------+--------+---------------------+---------+-----------------+----------+--------------------+---------+------------+----------+--------+
*/

	public static ArrayList<Job> searchJobs(Company company,IndustryType industryType,City city,Designation designation,Department department,Branch branch,Integer noOfVacancies,Date launchDate,Date applyLastDate,Integer ExperienceMin,Integer ExperienceMax,Integer ctcMin,Integer ctcMax,Skill skill,Integer sscScore,Integer hscDiplomaScore,Integer graduation){
		return null;			
	}

	public static ArrayList<CompanyJobs> searchJobs(Branch branch,Department department,Designation designation,PinCode pinCode,City city,State state,Country country,IndustryType industryType,Company company,JobSkill jobSkills,JobScoreCriteria jobScoreCriteria,JobQualification jobQualifications,JobShift jobShift){
		String sj = "inside searchJobs()--->";
		ArrayList<CompanyJobs> companyJobs = new ArrayList<CompanyJobs>();
		String _branch = "",_department = "",_designation = "",_pinCode = "",_city = "",_state = "",_country = "",_industryType = "",_company = "",_jobSkills = "",_jobScoreCriteria = "",_jobQualifications = "",_jobShift = "";
		Boolean _b = false,_dep = false,_des = false,_pc = false,_cit = false,_s = false,_cou = false,_it = false,_c = false,_js = false,_jsc = false,_jq = false,_jshi = false;
		Boolean and = false;
		
		System.out.println(sj + "----------------------SEARCHJOBS-------------------------\n\n");
		if(branch != null){
			_b = true;
			_branch = " branch_id = ? ";
			and = true;
		}
		if(department != null){
			_dep = true;
			_department = " department_id = ? ";
			if(and)
				_department = " and " + _department;
			and = true;
		}
		if(designation != null){
			_des = true;
			_designation = " designation_id = ? ";
			if(and)
				_designation = " and " + _designation;
			and = true;			
		}
		if(pinCode != null){
			_pc = true;
			_pinCode = " pin_code_id = ? ";
			if(and)
				_pinCode = " and " + _pinCode;
			and = true;
		}
		if(city != null){
			_cit = true;
			_city = " city_id = ? ";
			if(and)
				_city =" and " + _city;
			and = true;
		}
		if(state != null){
			_s = true;
			_state = " state_id = ? ";
			if(and)
				_state = " and " + _state;
			and = true;
		}
		if(country != null){
			_cou = true;
			_country = " country_id = ? ";
			if(and)
				_country = " and " + _country;
			and = true;
		}
		if(industryType != null){
			_it = true;
			_industryType = " industry_type_id = ? " ;
			if(and)
				_industryType = " and " + _industryType;
			and = true;
		}
		if(company != null){
			_c = true;
			_company = " company_id = ? ";
			if(and)
				_company = " and "  + _company;
			and = true;
		}
		if(jobSkills != null){
			_js = true;
			_jobSkills = " job_skill_id = ? ";
			if(and)
				_jobSkills = " and " + _jobSkills;
			and = true;
		}
		if(jobScoreCriteria != null){
			_jsc = true;
			_jobScoreCriteria = " job_score_criteria_id = ? " ;
			if(and)
				_jobScoreCriteria = " and " + _jobScoreCriteria;
			and = true;
		}
		if(jobQualifications != null){
			_jq = true;
			_jobQualifications = " job_qualification_id = ? " ;
			if(and)
				_jobQualifications = " and " + _jobQualifications;
			and = true;
		}
		if(jobShift != null){
			_jshi = true;
			_jobShift = " job_shift_id = ? ";
			if(and)
				_jobShift = " and "  + _jobShift;
			and = true;
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_id,branch_id,department_id,designation_id,pin_code_id,city_id,state_id,country_id,industry_type_id,company_id,job_skill_id,job_score_criteria_id,job_qualification_id,job_shift_id from job_search where " + _branch + _department + _designation + _pinCode + _city + _state + _country + _industryType + _company + _jobSkills + _jobScoreCriteria + _jobQualifications + _jobShift + "group by job_id";
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_b)
				ps.setObject(++i,branch.getBranchId());
			if(_dep)
				ps.setObject(++i,department.getDepartmentId());
			if(_des)
				ps.setObject(++i,designation.getDesignationId());
			if(_pc)
				ps.setObject(++i,pinCode.getPinCodeId());
			if(_cit)
				ps.setObject(++i,city.getCityId());
			if(_s)
				ps.setObject(++i,state.getStateId());
			if(_cou)
				ps.setObject(++i,country.getCountryId());
			if(_it)
				ps.setObject(++i,industryType.getIndustryTypeId());
			if(_c)
				ps.setObject(++i,company.getCompanyId());
			if(_js)
				ps.setObject(++i,jobSkills.getJobSkillId());
			if(_jsc)
				ps.setObject(++i,jobScoreCriteria.getJobScoreCriteriaId());
			if(_jq)
				ps.setObject(++i,jobQualifications.getJobQualificationId());
			if(_jshi)
				ps.setObject(++i,jobShift.getJobShiftId());
			
			System.out.println(sj + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				i = 0;
				companyJobs.add(new CompanyJobs(rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i),rs.getInt(++i)));
				//companyJobs.add(new CompanyJobs(Job.getJobObject(rs.getInt(++i))));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(sj + "companyJobs : " + companyJobs);
		return companyJobs;
	}
	

	//getter setters
	public void setJob(Job job){
		this.job = job;
	}
	public Job getJob(){
		return job;
	}
	

	public void setBranch(Branch branch){
		this.branch = branch;
	}
	public Branch getBranch(){
		return branch;
	}


	public Department getDepartment(){
		return department;
	}
	public void setDepartment(Department department){
		this.department = department;
	}


	public Designation getDesignation(){
		return designation;
	}
	public void setDesignation(Designation designation){
		this.designation = designation;
	}


	public PinCode getPinCode(){
		return pinCode;
	}
	public void setPinCode(PinCode pinCode){
		this.pinCode = pinCode;
	}


	public City getCity(){
		return city;
	}
	public void setCity(City city){
		this.city = city;
	}


	public State getState(){
		return state;
	}
	public void setState(State state){
		this.state = state;
	}


	public Country getCountry(){
		return country;
	}
	public void setCountry(Country country){
		this.country = country;
	}


	public IndustryType getIndustryType(){
		return industryType;
	}
	public void setIndustryType(IndustryType industryType){
		this.industryType = industryType;
	}


	public Company company(){
		return company;
	}
	public void setCompany(Company company){
		this.company = company;
	}


	public ArrayList<JobSkill> getJobSkills(){
		return jobSkills;
	}
	public void setJobSkills(ArrayList<JobSkill> jobSkills){
		this.jobSkills = jobSkills;
	}


	public JobScoreCriteria getJobScoreCriteria(){
		return jobScoreCriteria;
	}
	public void setJobScoreCriteria(JobScoreCriteria jobScoreCriteria){
		this.jobScoreCriteria = jobScoreCriteria;
	}


	public ArrayList<JobQualification> getJobQualifications(){
		return jobQualifications;
	}
	public void setJobQualifications(ArrayList<JobQualification> jobQualifications){
		this.jobQualifications = jobQualifications;
	}

	public JobShift getJobShift(){
		return jobShift;
	}
	public void setJobShift(JobShift jobShift){
		this.jobShift = jobShift;
	}



}