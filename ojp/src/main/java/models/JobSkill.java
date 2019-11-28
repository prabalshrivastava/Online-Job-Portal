package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import java.util.ArrayList;
import utilities.Db;


public class JobSkill{

	//membet variables
	private	Integer jobSkillId;
	private Job		job;
	private	Skill	skill;


	public JobSkill(){
	}

	public JobSkill(Integer jobSkillId){
		setJobSkillId(jobSkillId);
	}

	public JobSkill(Integer jobSkillId,Job job,Skill skill){
		setJobSkillId(jobSkillId);
		setJob(job);
		setSkill(skill);
	}
	
	//methods
	//@override toString
	public String toString(){
		return "{'jobSkillId' : " + jobSkillId + " , 'job' : " + job + " , 'skill' : " + skill + "}";
	}

	//not yet tested
	public static JobSkill getJobSkillObject(Integer jobSkillId,Job job,Skill skill){
		String gjso = "inside getJobSkillObject()--->";
		String _jobSkillId = "",_job = "",_skill = "";
		Boolean _jsi = false,_j = false,_s = false;
		JobSkill jobSkillObject = null;

		if(jobSkillId != null){
			_jobSkillId = " job_skill_id = ? ";
			_jsi = true;
		}
		if(job != null){
			_job = " job_id = ? ";
			_j = true;
			if(_jsi)
				_job = " and " + _job;
		}
		if(skill != null){
			_skill = " skill_id = ? ";
			_s = true;
			if(_jsi || _j)
				_skill = " and " + _skill;
		}


		if(!_jsi && !_j && !_s)
			return null;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_skill_id,job_id,skill_id from jobs_skills where " + _jobSkillId +  _job + _skill;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_jsi)
				ps.setObject(++i,jobSkillId);
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_s)
				ps.setObject(++i,skill.getSkillId());

			//System.out.println(gjso + "PreparedSatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//System.out.println(gjso + "jobSkillObject : " + (jobSkillObject = new JobSkill(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Skill.getSkillObject(rs.getInt(3),null))));
				jobSkillObject = new JobSkill(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Skill.getSkillObject(rs.getInt(3),null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobSkillObject;

	}

	//(Integer jobSkillId,Job job,Skill skill){
	public static JobSkill updateJobSkill(Job job,Skill skill){
		String ujs = "inside updateJobSkill()--->";
		JobSkill jobSkillObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "insert into jobs_skills(job_id,skill_id) value(?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			ps.setObject(++i,job.getJobId());
			ps.setObject(++i,skill.getSkillId());

			System.out.println(ujs + "PreparedStatement : " + ps);
			System.out.println(ujs + "RowsEffected : " +ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println(ujs + "updating jobSkillObject : " + (jobSkillObject = JobSkill.getJobSkillObject(rs.getInt(1),null,null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobSkillObject;
	}

	


	public static ArrayList<JobSkill> collectJobSkills(Job job,Skill skill){
		String cjs = "inside collectJobSkills()--->";
		ArrayList<JobSkill> jobSkills = null;

		String _job = "" , _skill = "";
		Boolean _j = false , _s = false;

		if(job != null){
			_j = true;
			_job = " where job_id = ? ";
		}
		if(skill != null){
			_s = null;
			_skill = " where skill_id = ? ";
			if(_j)
				_skill = " and skill_id = ? ";
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_skill_id,job_id,skill_id from jobs_skills " + _job + _skill;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_s)
				ps.setObject(++i,skill.getSkillId());
			
			System.out.println(cjs + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			jobSkills = new ArrayList<JobSkill>();
			while(rs.next()){
				jobSkills.add(new JobSkill(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Skill.getSkillObject(rs.getInt(3),null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobSkills;
	}


	//getter setters
	public void setJobSkillId(Integer jobSkillId){
		this.jobSkillId=jobSkillId;
	}
	public Integer getJobSkillId(){
		return jobSkillId;
	}

	public void setJob(Job job){
		this.job=job;
	}
	public Job getJob(){
		return job;
	}

	public void setSkill(Skill skill){
		this.skill=skill;
	}
	public Skill getSkill(){
		return skill;
	}
}