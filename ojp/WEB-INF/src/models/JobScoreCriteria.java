package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.Db;

public class JobScoreCriteria{
	//member variables
	private Integer	jobScoreCriteriaId;
	private Job		job;
	private Integer	sscScore;
	private	Integer	hscDiplomaScore;
	private	Integer	graduation;

	//constructors
	public JobScoreCriteria(){
	}

	public JobScoreCriteria(Integer jobScoreCriteriaId){
		setJobScoreCriteriaId(jobScoreCriteriaId);
	}

	public JobScoreCriteria(Integer jobScoreCriteriaId,Job job,Integer sscScore,Integer hscDiplomaScore,Integer graduation){
		setJobScoreCriteriaId(jobScoreCriteriaId);
		setJob(job);
		setSscScore(sscScore);
		setHscDiplomaScore(hscDiplomaScore);
		setGraduation(graduation);
	}

	//methods
	//@override toString()
	public String toString(){
		return "{'jobScoreCriteriaId' : " + jobScoreCriteriaId + " , 'job' : " + job + " , 'sscScore' : " + sscScore  + " , 'hscDiploma' : " + " , 'graduation' : " + graduation + "}";
	}

	//not yet tested
	public static JobScoreCriteria getJobScoreCriteriaObject(Integer jobScoreCriteriaId,Job job,Integer sscScore,Integer hscDiplomaScore,Integer graduation){
		String gjsco = "inside getJobScoreCriteriaObject()--->";
		String _jobScoreCriteriaId = "", _job = "", _sscScore = "" ,_hscDiplomaScore = "",_graduation = "";
		Boolean _jsci = false,_j = false,_ss = false,_hds = false,_g =false;
		JobScoreCriteria jobScoreCriteriaObject = null;
		
		if(jobScoreCriteriaId != null){
			_jobScoreCriteriaId = " job_score_criteria_id = ? " ;
			_jsci = true;
		}
		if(job != null){
			_job = " job_id = ? ";
			_j = true;
			if(_jsci)
				_job = " and " + _job;
		}
		if(sscScore != null){
			_sscScore = " ssc_score = ? ";
			_ss = true;
			if(_jsci || _j)
				_sscScore = " and " + _sscScore;
		}
		if(hscDiplomaScore != null){
			_hscDiplomaScore = " hsc_diploma_score = ? ";
			_hds = true;
			if(_jsci || _j || _ss)
				_hscDiplomaScore =  " and " + _hscDiplomaScore;
		}
		if(graduation != null){
			_graduation = " graduation = ? ";
			_g = true;
			if(_jsci || _j || _ss ||_hds)
				_graduation = " and " + _graduation;
		}

		if(!_jsci && !_j && !_ss && !_hds && !_g)
			return null;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select job_score_criteria_id,job_id,ssc_score,hsc_diploma_score,graduation from job_score_criteria where " + _jobScoreCriteriaId + _job + _sscScore + _hscDiplomaScore + _graduation;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_jsci)
				ps.setObject(++i,jobScoreCriteriaId);
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_ss)
				ps.setObject(++i,sscScore);
			if(_hds)
				ps.setObject(++i,hscDiplomaScore);
			if(_g)
				ps.setObject(++i,graduation);

			//System.out.println(gjsco + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				//System.out.println(gjsco + "jobScoreCriteriaObject : " + (jobScoreCriteriaObject = new JobScoreCriteria(rs.getInt(1),Job.getJobObject(rs.getInt(2)),rs.getInt(3),rs.getInt(4),rs.getInt(5))));
				jobScoreCriteriaObject = new JobScoreCriteria(rs.getInt(1),Job.getJobObject(rs.getInt(2)),rs.getInt(3),rs.getInt(4),rs.getInt(5));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobScoreCriteriaObject;
	}
	
	
	//(Integer jobScoreCriteriaId,Job job,Integer sscScore,Integer hscDiplomaScore,Integer graduation)
	public static JobScoreCriteria updateJobScoreCriteria(Job job,Integer sscScore,Integer hscDiplomaScore,Integer graduation){
		String ujsc = "inside updateJobScoreCriteria()--->";
		JobScoreCriteria jobScoreCriteriaObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "insert into job_score_criteria(job_id,ssc_score,hsc_diploma_score,graduation) value(?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			ps.setObject(++i,job.getJobId());
			ps.setObject(++i,sscScore);
			ps.setObject(++i,hscDiplomaScore);
			ps.setObject(++i,graduation);
			
			System.out.println(ujsc + "PreparedStatement : " + ps);
			System.out.println(ujsc + "Rows Effected : " + ps.executeUpdate());
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println(ujsc + "updating jobScoreCriteriaObject : " + (jobScoreCriteriaObject = JobScoreCriteria.getJobScoreCriteriaObject(rs.getInt(1),null,null,null,null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobScoreCriteriaObject;
	}



	//getter setters
	public void setJobScoreCriteriaId(Integer jobScoreCriteriaId){
		this.jobScoreCriteriaId=jobScoreCriteriaId;
	}
	public Integer getJobScoreCriteriaId(){
		return jobScoreCriteriaId;
	}

	public void setJob(Job job){
		this.job=job;
	}
	public Job getJob(){
		return job;
	}

	public void setSscScore(Integer sscScore){
		this.sscScore=sscScore;
	}
	public Integer getSscScore(){
		return sscScore;
	}

	public void setHscDiplomaScore(Integer hscDiplomaScore){
		this.hscDiplomaScore=hscDiplomaScore;
	}
	public Integer getHscDiplomaScore(){
		return hscDiplomaScore;
	}

	public void setGraduation(Integer graduation){
		this.graduation=graduation;
	}
	public Integer getGraduation(){
		return graduation;
	}
}