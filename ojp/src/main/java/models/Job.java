package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import utilities.Db;



public class Job{
	//member variables
	private	Integer		jobId;
	private	String		jobTitle;
	private	Branch		branch;
	private	Department	department;
	private	Designation	designation;
	private	Integer		noOfVacancy;
	private	Date		launchDate;
	private	Date		applyLastDate;
	private	String		jobDescription;
	private	Integer		experienceMin;
	private	Integer		experienceMax;
	private	Integer		ctcMin;
	private	Integer		ctcMax;


	//constructors
	public Job(){
	}
	public Job(Integer jobId){
		setJobId(jobId);
	}
	public Job(Integer jobId,String jobTitle,Branch branch,Department department,Designation designation,Integer noOfVacancy,Date launchDate,Date applyLastDate,String jobDescription,Integer experienceMin,Integer experienceMax,Integer ctcMin,Integer ctcMax){
		setJobId(jobId);
		setJobTitle(jobTitle);
		setBranch(branch);
		setDepartment(department);
		setDesignation(designation);
		setNoOfVacancy(noOfVacancy);
		setLaunchDate(launchDate);
		setApplyLastDate(applyLastDate);
		setJobDescription(jobDescription);
		setExperienceMin(experienceMin);
		setExperienceMax(experienceMax);
		setCtcMin(ctcMin);
		setCtcMax(ctcMax);
	}


	//methods 	
	public static Job updateJobDetails(String jobTitle,Branch branch,Department department,Designation designation,Integer noOfVacancy,Date launchDate,Date applyLastDate,String jobDescription,Integer experienceMin,Integer experienceMax,Integer ctcMin,Integer ctcMax){
		Job jobObject = null;
		String ujd = "inside updateJobDetails()--->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "insert into jobs(job_title,branch_id,department_id,designation_id,no_of_vacancies,launch_date,apply_last_date,job_description,experience_min,experience_max,ctc_min,ctc_max) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			int i=0;
			ps.setObject(++i,jobTitle);
			ps.setObject(++i,branch.getBranchId());
			ps.setObject(++i,department.getDepartmentId());
			ps.setObject(++i,designation.getDesignationId());
			ps.setObject(++i,noOfVacancy);
			ps.setObject(++i,launchDate);
			ps.setObject(++i,applyLastDate);
			ps.setObject(++i,jobDescription);
			ps.setObject(++i,experienceMin);
			ps.setObject(++i,experienceMax);
			ps.setObject(++i,ctcMin);
			ps.setObject(++i,ctcMax);

			System.out.println(ujd + "preparedStatement : " + ps);
			System.out.println(ujd + "Rows Effected : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			System.out.println(ujd + "jobObject : " + (jobObject = new Job(null,jobTitle,branch,department,designation,noOfVacancy,launchDate,applyLastDate,jobDescription,experienceMin,experienceMax,ctcMin,ctcMax)));
			if(rs.next()){
				jobObject.setJobId(rs.getInt(1));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobObject;
	}

	public String toString(){
		return "{'jobId' : " + jobId + " , 'jobTitle' : '" + jobTitle + "' , 'branch' : " + branch + " , 'department' : " + department + " , 'designation' : " + designation + " , 'noOfVacancy' : " + noOfVacancy + " , 'launchDate' : '" + launchDate + "' , 'applyLastDate' : '" + applyLastDate + "' , 'jobDescription' : '" + jobDescription + "' , 'experienceMin' : " + experienceMin + " , 'experienceMax' : " + experienceMax  + " , 'ctcMin' : " + ctcMin + " , 'ctcMax' : " + ctcMax + " }";
	}

	public static Job getJobObject(Integer jobId){
		return getJobObject(jobId,null,null,null,null,null,null,null,null,null,null,null,null);
	}

	public static Job getJobObject(Integer jobId,String jobTitle,Branch branch,Department department,Designation designation,Integer noOfVacancy,Date launchDate,Date applyLastDate,String jobDescription,Integer experienceMin,Integer experienceMax,Integer ctcMin,Integer ctcMax){
		Job jobObject = null;
		String gjo = "inside getJobObject()--->";
		
		String _jobId = "", _jobTitle = "" , _branch = "", _department = "",_designation = "",_noOfVacancy = "",_launchDate = "",_applyLastDate = "",_jobDescription = "",_experienceMin = "",_experienceMax = "",_ctcMin = "",_ctcMax = "";
		Boolean _ji = false , _jt = false , _b = false , _dep = false , _des = false , _nov = false , _ld = false , _ald = false , _jd = false , _emin = false , _emax = false , _cmin = false ,_cmax = false;
		Boolean isAllParamNotNull = true;
		
		if(jobId != null){
			_jobId = " job_id = ? " ;
			_ji = true;
		}

		if(jobTitle != null){
			_jobTitle = " job_title = ? " ;
			_jt = true;
			if(_ji)
				_jobTitle = " and " + _jobTitle;
		}

		if(branch != null){
			_branch = " branch_id = ? ";
			_b = true;
			if(_ji || _jt)
				_branch = " and " + _branch;
		}

		if(department != null){
			_department = " department_id = ? ";
			_dep = true;
			if(_ji || _jt || _b)
				_department = " and " + _department;
		}

		if(designation != null){
			_designation = " designation_id = ? ";
			_des = true;
			if(_ji || _jt || _b || _dep)
				_designation = " and " + _designation;
		}

		if(_nov){
			_noOfVacancy = "  no_of_vacancies  = ? ";
			_nov = true;
			if(_ji || _jt | _b || _dep || _des)
				_noOfVacancy = " and " + _noOfVacancy;
		}

		if(launchDate != null){
			_launchDate = " launch_date = ? ";
			_ld = true;
			if(_ji || _jt || _b || _dep || _des || _nov)
				_launchDate = " and " + _launchDate;
		}

		if(applyLastDate != null){
			_applyLastDate = " apply_last_date = ? " ;
			_ald = true;
			if(_ji || _jt || _b || _dep || _des || _nov || _ld)
				_applyLastDate = " and " + _applyLastDate;
		}

		if(jobDescription != null){
			_jobDescription = " job_description = ? ";
			_jd = true;
			if(_ji || _jt || _b || _dep || _des || _nov || _ld || _ald)
				_jobDescription = " and " + _jobDescription;
		}

		if(experienceMin != null){
			_experienceMin = " experience_min = ? ";
			_emin = true;
			if(_ji || _jt || _b || _dep || _des || _nov || _ld || _ald || _jd)
				_experienceMin = " and " + _experienceMin;
		}

		if(experienceMax != null){
			_experienceMax = " experience_max = ? ";
			_emax = true;
			if(_ji || _jt || _b ||_dep || _des || _nov || _ld || _ald || _jd || _emin)
				_experienceMax = " and " + _experienceMax;
		}

		if(ctcMin != null){
			_ctcMin = " ctc_min = ? ";
			_cmin = true;
			if(_ji || _jt || _b || _dep || _des || _nov || _ld || _ald || _jd || _emin || _emax)
			_ctcMin = " and " + _ctcMin;
		}



		isAllParamNotNull = _ji || _jt || _b || _dep || _des || _nov || _ld || _ald || _jd || _emin || _emax || _cmin;
		if(ctcMax != null){
			_ctcMax = " ctc_max = ? ";
			_cmax = true;
			if(isAllParamNotNull)
				_ctcMax = " and " + _ctcMax;
		}
		
		if(!isAllParamNotNull)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_id,job_title,branch_id,department_id,designation_id, no_of_vacancies ,launch_date,apply_last_date,job_description,experience_min,experience_max,ctc_min,ctc_max from jobs where " + _jobId + _jobTitle + _branch + _department + _designation + _noOfVacancy + _launchDate + _applyLastDate + _jobDescription + _experienceMin + _experienceMax + _ctcMin + _ctcMax;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_ji)
				ps.setObject(++i,jobId);
			if(_jt)
				ps.setObject(++i,jobTitle);
			if(_b)
				ps.setObject(++i,branch.getBranchId());
			if(_dep)
				ps.setObject(++i,department.getDepartmentId());
			if(_des)
				ps.setObject(++i,designation.getDesignationId());
			if(_nov)
				ps.setObject(++i,noOfVacancy);
			if(_ld)
				ps.setObject(++i,launchDate);
			if(_ald)
				ps.setObject(++i,applyLastDate);
			if(_jd)
				ps.setObject(++i,jobDescription);
			if(_emin)
				ps.setObject(++i,experienceMin);
			if(_emax)
				ps.setObject(++i,experienceMax);
			if(_cmin)
				ps.setObject(++i,ctcMin);
			if(_cmax)
				ps.setObject(++i,ctcMax);
		
			
			//System.out.println(gjo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				//System.out.println(gjo + "jobObject : " + (jobObject = new Job(rs.getInt(1),rs.getString(2),Branch.getBranchObject(rs.getInt(3),null,null,null,null,null,null,null),Department.getDepartmentObject(rs.getInt(4),null),Designation.getDesignationObject(rs.getInt(5),null),rs.getInt(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13))));
				jobObject = new Job(rs.getInt(1),rs.getString(2),Branch.getBranchObject(rs.getInt(3),null,null,null,null,null,null,null),Department.getDepartmentObject(rs.getInt(4),null),Designation.getDesignationObject(rs.getInt(5),null),rs.getInt(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13));
			}
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobObject;
	}








	public static ArrayList<Job> collectJobs(String jobTitle,Branch branch,Department department,Designation designation,Integer noOfVacancy,Date launchDate,Date applyLastDate,String jobDescription,Integer experienceMin,Integer experienceMax,Integer ctcMin,Integer ctcMax){
		ArrayList<Job> jobs = null;
		String cj = "inside collectJobs()--->";
		String _jobTitle = "",_branch = "",_department = "",_designation = "",_noOfVacancy = "",_launchDate = "",_applyLastDate = "",_jobDescription = "",_experienceMin = "",_experienceMax = "",_ctcMin = "",_ctcMax = "";
		Boolean _jt,_b,_dep,_des,_nov,_ld,_ald,_jd,_emin,_emax,_cmin,_cmax; 
		_jt = _b = _dep = _des = _nov = _ld = _ald = _jd = _emin = _emax = _cmin = _cmax = false;

		
		if(jobTitle != null){
			_jt = true;
			_jobTitle = " where job_title = ? ";
		}
		if(branch != null){
			_b = true;
			_branch = " where branch_id = ? ";
			if(_jt)
				_branch = " and branch_id = ? ";
		}
		if(department != null){
			_dep = true;
			_department = " where department_id = ? ";
			if(_b || _jt){
				_department = " and department_id = ? ";
			}
		}
		if(designation != null){
			_des = true;
			_designation = " where designation_id = ? ";
			if(_b || _jt || _dep)
				_designation = " and designation_id = ? ";
		}
		if(noOfVacancy != null){
			_nov = true;
			_noOfVacancy = " where no_of_vacancies = ? ";
			if(_b || _jt || _dep || _des)
				_noOfVacancy = " and no_of_vacancies = ? ";
		}
		if(launchDate != null){
			_ld = true;
			_launchDate = " where launch_date = ? ";
			if(_b || _jt || _dep || _des || _nov)
				_launchDate = " and launch_date = ? ";
		}
		if(applyLastDate != null){
			_ald = true;
			_applyLastDate = " where apply_last_date = ? ";
			if(_b || _jt || _dep || _des || _nov || _ld)
				_applyLastDate = " and apply_last_date = ? ";
		}
		if(jobDescription != null){
			_jd = true;
			_jobDescription = " where job_description = ? ";
			if(_b || _jt || _dep || _des || _nov || _ld || _ald)
				_jobDescription = " and job_description = ? ";
		}
		if(experienceMin != null){
			_emin = true;
			_experienceMin = " where experience_min = ? ";
			if(_b || _jt || _dep || _des || _nov || _ld || _ald || _jd)
				_experienceMin = " and experience_min = ? ";
		}
		if(experienceMax != null){
			_emax = true;
			_experienceMax = " where experience_max = ? ";
			if(_b || _jt || _dep || _des || _nov || _ld || _ald || _jd || _emin)
				_experienceMax = " and experience_max = ? ";
		}
		if(ctcMin != null){
			_cmin = true;
			_ctcMin = " where ctc_min = ? ";
			if(_b || _jt || _dep || _des || _nov || _ld || _ald || _jd || _emin || _emax)
				_ctcMin = " and ctc_min = ? ";
		}
		if(ctcMax != null){
			_cmax = true;
			_ctcMax = " where ctc_max = ? ";
			if(_b || _jt || _dep || _des || _nov || _ld || _ald || _jd || _emin || _emax || _cmin)
				_ctcMax = " and ctc_max = ? ";
		}
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_id,job_title,branch_id,department_id,designation_id, no_of_vacancies ,launch_date,apply_last_date,job_description,experience_min,experience_max,ctc_min,ctc_max from jobs " + _jobTitle + _branch + _department + _designation + _noOfVacancy + _launchDate + _applyLastDate + _jobDescription + _experienceMin + _experienceMax + _ctcMin + _ctcMax;
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_jt)
				ps.setObject(++i,jobTitle);
			if(_b)
				ps.setObject(++i,branch.getBranchId());
			if(_dep)
				ps.setObject(++i,department.getDepartmentId());
			if(_des)
				ps.setObject(++i,designation.getDesignationId());
			if(_nov)
				ps.setObject(++i,noOfVacancy);
			if(_ld)
				ps.setObject(++i,launchDate);
			if(_ald)
				ps.setObject(++i,applyLastDate);
			if(_jd)
				ps.setObject(++i,jobDescription);
			if(_emin)
				ps.setObject(++i,experienceMin);
			if(_emax)
				ps.setObject(++i,experienceMax);
			if(_cmin)
				ps.setObject(++i,ctcMin);
			if(_cmax)
				ps.setObject(++i,ctcMax);
			
			System.out.println(cj + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			jobs = new ArrayList();
			while(rs.next()){
				jobs.add(new Job(rs.getInt(1),rs.getString(2),Branch.getBranchObject(rs.getInt(3)),Department.getDepartmentObject(rs.getInt(4),null),Designation.getDesignationObject(rs.getInt(5),null),rs.getInt(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getInt(10),rs.getInt(12),rs.getInt(11),rs.getInt(13)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
		System.out.println(cj + "jobs : " + jobs);
		return jobs;
	}


	//getter setters
	public void setJobId(Integer jobId){
		this.jobId=jobId;
	}
	public Integer getJobId(){
		return jobId;
	}

	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
	public String getJobTitle(){
		return this.jobTitle;
	}

	public void setBranch(Branch branch){
		this.branch=branch;
	}
	public Branch getBranch(){
		return branch;
	}

	public void setDepartment(Department department){
		this.department=department;
	}
	public Department getDepartment(){
		return department;
	}

	public void setDesignation(Designation designation){
		this.designation=designation;
	}
	public Designation getDesignation(){
		return designation;
	}

	public void setNoOfVacancy(Integer noOfVacancy){
		this.noOfVacancy=noOfVacancy;
	}
	public Integer getNoOfVacancy(){
		return noOfVacancy;
	}

	public void setLaunchDate(Date launchDate){
		this.launchDate=launchDate;
	}
	public Date getLaunchDate(){
		return launchDate;
	}

	public void setApplyLastDate(Date applyLastDate){
		this.applyLastDate=applyLastDate;
	}
	public Date getApplyLastDate(){
		return applyLastDate;
	}

	public void setJobDescription(String jobDescription){
		this.jobDescription=jobDescription;
	}
	public String getJobDescription(){
		return jobDescription;
	}

	public void setExperienceMin(Integer experienceMin){
		this.experienceMin=experienceMin;
	}
	public Integer getExperienceMin(){
		return experienceMin;
	}

	public void setExperienceMax(Integer experienceMax){
		this.experienceMax=experienceMax;
	}
	public Integer getExperienceMax(){
		return experienceMax;
	}

	public void setCtcMin(Integer ctcMin){
		this.ctcMin=ctcMin;
	}
	public Integer getCtcMin(){
		return ctcMin;
	}

	public void setCtcMax(Integer ctcMax){
		this.ctcMax=ctcMax;
	}
	public Integer getCtcMax(){
		return ctcMax;
	}
}