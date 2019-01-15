package models;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import utilities.Db;


public class Experience{
	//member variables
	private Integer			experienceId;
	private Candidate		candidate;
	private String			organizationName;
	private	IndustryType	industryType;
	private	Department		department;
	private Designation		designation;
	private Date			joiningDate;
	private	Date			leavingDate;
	private	Integer			ctc;
	
	public Experience(Integer experienceId,Candidate candidate,String organizationName,IndustryType industryType,Department department,Designation designation,Date joiningDate,Date leavingDate,Integer ctc){
		setExperienceId(experienceId);
		setCandidate(candidate);
		setOrganizationName(organizationName);
		setIndustryType(industryType);
		setDepartment(department);
		setDesignation(designation);
		setJoiningDate(joiningDate);
		setLeavingDate(leavingDate);
		setCtc(ctc);
	}

	//methods
	//@override toString()
	public String toString(){
		return " {'candidateId' : " + candidate.getCandidateId() + ",'organizationName' : '" + organizationName + "','IndustryTypeId' : " + industryType.getIndustryTypeId() + ",'departmentId' : " + department.getDepartmentId() + ",'designationId' : " + designation.getDesignationId() + ",'joiningDate' : '" + joiningDate + "','leavingDate' : '" + leavingDate + "','ctc' : " + ctc + "} ";
	}

	public static Experience getExperienceObject(Candidate candidate){
		
		Experience experience = null;
		String geio = "inside getExperienceObject()-->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);

			String query = "select experience_id,candidate_id,organization_name,industry_type_id,department_id,designation_id,joining_date,relieving_date,ctc from experiences where candidate_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,candidate.getCandidateId());
			//System.out.println(geio+"PreparedStatement : " + ps);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				/*
				System.out.println(geio+(experience = new Experience(rs.getInt(1),candidate,rs.getString(3),IndustryType.getIndustryTypeObject(rs.getInt(4),null),Department.getDepartmentObject(rs.getInt(5),null),Designation.getDesignationObject(rs.getInt(6),null),rs.getDate(7),rs.getDate(8),rs.getInt(9))));
				System.out.println(geio + "candidate.getCandidateId() : " + candidate.getCandidateId() + " == rs.getInt(1) : "+rs.getInt(2));
				*/
				experience = new Experience(rs.getInt(1),candidate,rs.getString(3),IndustryType.getIndustryTypeObject(rs.getInt(4),null),Department.getDepartmentObject(rs.getInt(5),null),Designation.getDesignationObject(rs.getInt(6),null),rs.getDate(7),rs.getDate(8),rs.getInt(9));
			}
			connection.close();	

		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return experience;
	}


	public static Experience insertExperience(Candidate candidate,String organizationName,IndustryType industryType,Department department,Designation designation,Date joiningDate,Date leavingDate,Integer ctc,Boolean isAjaxRequest){
		Experience experience = null;
		ResultSet experienceId;
		String ie = "inside insertExperience()-->";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = null;
			if(isAjaxRequest){
				query = "update experiences set candidate_id = ?,organization_name = ?,industry_type_id = ?,department_id = ?,designation_id = ?,joining_date = ?,relieving_date = ?,ctc =? where candidate_id =?";
			}else{
				query = "insert into experiences(candidate_id,organization_name,industry_type_id,department_id,designation_id,joining_date,relieving_date,ctc) values(?,?,?,?,?,?,?,?)";
			}
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1,candidate.getCandidateId());
			ps.setString(2,organizationName);
			ps.setInt(3,industryType.getIndustryTypeId());
			ps.setInt(4,department.getDepartmentId());
			ps.setInt(5,designation.getDesignationId());
			ps.setDate(6,joiningDate);
			ps.setDate(7,leavingDate);
			ps.setInt(8,ctc);

			if(isAjaxRequest){
				ps.setInt(9,candidate.getCandidateId());
				System.out.println(ie+"updating experience for candidateId : "+candidate.getCandidateId());
			}else{
				System.out.println(ie + "First Time inserting experience");
			}

			System.out.println(ie+"preparedStatement : "+ps);
			System.out.println(ie+ ((ps.executeUpdate() == 1) ? "experience inserted successfully" : " unable to insert into experience"));

			experienceId = ps.getGeneratedKeys();
			if(experienceId.next()){
				experience = new Experience(experienceId.getInt(1),candidate,organizationName,industryType,department,designation,joiningDate,leavingDate,ctc);
			}
			connection.close();
			System.out.println(ie + "Experience is successfully updated");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return experience;
	}
	

	public static ArrayList<Experience> collectExperiences(Candidate candidate){

		ArrayList<Experience> experiences = new ArrayList<Experience>();
		String ce = "inside collectExperiences()--->";

		try{
			String _candidate = "";
			Boolean _c = false;
			if(candidate != null){
				_candidate = "where candidate_id = ?";
				_c = true;
			}

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			/*
				+---------------+--------------+-------------------+------------------+---------------+----------------+--------------+----------------+--------+
				| experience_id | candidate_id | organization_name | industry_type_id | department_id | designation_id | joining_date | relieving_date | ctc    |
				+---------------+--------------+-------------------+------------------+---------------+----------------+--------------+----------------+--------+				
			*/
			String query = "select experience_id,candidate_id,organization_name,industry_type_id,department_id,designation_id,joining_date,relieving_date,ctc from experiences " + _candidate;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_c)
				ps.setObject(++i,candidate.getCandidateId());

			System.out.println(ce + "PreparedStatement : " + ps);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){	
				//Integer experienceId,Candidate candidate,String organizationName,IndustryType industryType,Department department,Designation designation,Date joiningDate,Date leavingDate,Integer ctc
				experiences.add(new Experience(rs.getInt(1),Candidate.getCandidateObject(new Candidate(rs.getInt(2)),null),rs.getString(3),IndustryType.getIndustryTypeObject(rs.getInt(4),null),Department.getDepartmentObject(rs.getInt(5),null),Designation.getDesignationObject(rs.getInt(6),null),rs.getDate(7),rs.getDate(8),rs.getInt(9)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(ce + "experiences : " + experiences);
		return experiences;
	}




	
	//getter setters
	public void setExperienceId(Integer experienceId){
		this.experienceId=experienceId;
	}
	public Integer getExperienceId(){
		return experienceId;
	}

	public void setCandidate(Candidate candidate){
		this.candidate=candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}

	public void setOrganizationName(String organizationName){
		this.organizationName = organizationName;
	}
	public String getOrganizationName(){
		return organizationName;
	}
	
	public void setIndustryType(IndustryType industryType){
		this.industryType = industryType;
	}
	public IndustryType getIndustryType(){
		return industryType;
	}

	public void setDepartment(Department department){
		this.department = department;
	}
	public Department getDepartment(){
		return department;
	}

	public void setDesignation(Designation designation){
		this.designation = designation;
	}
	public Designation getDesignation(){
		return designation;
	}
	
	public void setJoiningDate(Date joiningDate){
		this.joiningDate = joiningDate;
	}
	public Date getJoiningDate(){
		return joiningDate;
	}

	public void setLeavingDate(Date leavingDate){
		this.leavingDate = leavingDate;
	}
	public Date getLeavingDate(){
		return leavingDate;
	}

	public void setCtc(Integer ctc){
		this.ctc=ctc;
	}
	public Integer getCtc(){
		return ctc;
	}
}