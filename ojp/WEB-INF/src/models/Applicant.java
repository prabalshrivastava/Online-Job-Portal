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


public class Applicant{
	//member variables
	private	Integer		applicantId;
	private Job			job;
	private Candidate	candidate;
	private	Status		status;
	private	Date		applyDate;
	
	//constructors
	public Applicant(){

	}

	public Applicant(Integer applicantId){
		setApplicantId(applicantId);
	}

	public Applicant(Integer applicantId,Job job,Candidate candidate,Status status,Date applyDate){
		setApplicantId(applicantId);
		setJob(job);
		setCandidate(candidate);
		setStatus(status);
		setApplyDate(applyDate);
	}
	
	//methods
	public String toString(){
		return "{'applicantId' : " + applicantId + ",'job' : " + job + "',candidate' : " + candidate + ",'status' : " + status + ",'applyDate' : '" + applyDate + "'}";
	}
	

	public static Applicant updateApplicant(Job job,Candidate candidate,Status status,Date applyDate){
		String ua = "inside updateApplicant()--->";
		Applicant applicantObject = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("com.mysql.jdbc.Driver");
			String query = "insert into applicants(job_id,candidate_id,status_id,apply_date) value(?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			ps.setObject(++i,job.getJobId());
			ps.setObject(++i,candidate.getCandidateId());
			ps.setObject(++i,status.getStatusId());
			ps.setObject(++i,applyDate);
			System.out.println(ua + "Rows Effected :  " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				System.out.println(ua + "Updating applicantObject : " + (applicantObject = Applicant.getApplicantObject(rs.getInt(1),null,null,null,null)));
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return applicantObject;
	}


	//not tested yet
	public static Applicant getApplicantObject(Integer applicantId,Job job,Candidate candidate,Status status,Date applyDate){
		String gao = "inside getApplicantObject()--->";
		String _applicantId = "",_job = "",_candidate = "",_status = "" , _applyDate = "";
		Boolean _ai = false , _j = false,_c = false, _s= false , _ad = false;
		
		Applicant applicantObject = null;

		if(applicantId != null){
			_applicantId = " applicant_id = ? ";
			_ai = true;
		}
		if(job != null){
			_job = " job_id = ? ";
			_j = true;
			if(_ai)
				_job = " and " + _job;
		}
		if(candidate != null){
			_candidate = " candidate_id = ? ";
			_c = true;
			if(_ai || _j)
				_candidate = " and " + _candidate;
		}
		if(status != null){
			_status = " status_id = ? ";
			_s = true;
			if(_ai || _j || _c)
				_status = " and " + _status;
		}
		
		if(applyDate != null){
			_applyDate = " apply_date = ? ";
			_ad = true;
			if(_ai || _j || _c || _s)
				_applyDate = " and " + _applyDate;
		}

		if(!_ai && !_j && !_c && !_s)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select applicant_id,job_id,candidate_id,status_id,apply_date from applicants where " + _applicantId + _job + _candidate + _status + _applyDate;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_ai)
				ps.setObject(++i,applicantId);
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_c)
				ps.setObject(++i,candidate.getCandidateId());
			if(_s)
				ps.setObject(++i,status.getStatusId());
			if(_ad)
				ps.setObject(++i,applyDate);

			System.out.println(gao + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				System.out.println(gao + "applicantObject : " + (applicantObject = new Applicant(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Candidate.getCandidateObject(new Candidate(rs.getInt(3)),null),Status.getStatusObject(rs.getInt(4),null),rs.getDate(5))));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return applicantObject;
	}


	public static ArrayList<Applicant> collectApplicants(Candidate candidate,Job job){
		ArrayList<Applicant> applicants = null;
		String ca = "inside collectApplicants()--->";
		String _candidate = "",_job = "";
		Boolean _c = false,_j = false;

		
		if(candidate != null){
			_c = true;
			_candidate = " where candidate_id = ? ";
		}
		if(job != null){
			_j = true;
			_job = " where job_id = ?";
			if(_c)
				_job = " and job_id = ?";

		}	

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("com.mysql.jdbc.Driver");
			String query = "select applicant_id,job_id,status_id,apply_date from applicants" + _candidate + _job;
			PreparedStatement ps = connection.prepareStatement("localhost:3306/ojp?user=root&password=" + Db.dbpass);

			int i = 0;
			if(_c)
				ps.setObject(++i,candidate.getCandidateId());
			if(_j)
				ps.setObject(++i,job.getJobId());

			System.out.println(ca + "PreparedStatement : " + ps);


			ResultSet rs = ps.executeQuery();
			applicants =  new ArrayList<Applicant>();
			while(rs.next()){
				applicants.add(new Applicant(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Candidate.getCandidateObject(new Candidate(rs.getInt(3)),null),Status.getStatusObject(rs.getInt(4),null),rs.getDate(5)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return applicants;
	}


	//getter setters
	public void setApplicantId(Integer aplicantId){
		this.applicantId=applicantId;
	}
	public Integer getApplicantId(){
		return applicantId;
	}

	public void setJob(Job job){
		this.job=job;
	}
	public Job getJob(){
		return job;
	}

	public void setCandidate(Candidate candidate){
		this.candidate=candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}

	public void setStatus(Status status){
		this.status=status;
	}
	public Status getstatus(){
		return status;
	}

	public void setApplyDate(Date applyDate){
		this.applyDate=applyDate;
	}
	public Date getApplyDate(){
		return applyDate;
	}

	
}