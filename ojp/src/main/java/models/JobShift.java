package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import utilities.Db;

public class JobShift{

	//member variables
	private Integer	jobShiftId;
	private	Job		job;
	private	Time	startHour;
	private	Time	endHour;

	//constuctors
	public JobShift(){
	}

	public JobShift(Integer jobShiftId){
		setJobShiftId(jobShiftId);
	}

	public JobShift(Integer jobShiftId,Job job,Time startHour,Time endHour){
		setJobShiftId(jobShiftId);
		setJob(job);
		setStartHour(startHour);
		setEndHour(endHour);
	}
	
	//methods 
	//@override toString
	public String toString(){
		return "{'jobShiftId' : " + jobShiftId + " , 'job' : " + job + " , 'startHour' : '" + startHour + "' , 'endHour' : '" + endHour + "'}";
	}
	
	//not yet tested
	public static JobShift getJobShiftObject(Integer jobShiftId,Job job,Time startHour,Time endHour){
		String _jobShiftId = "",_job = "" , _startHour = "" ,_endHour = "";
		Boolean _jsi = false , _j = false, _sh = false, _eh = false;
		String gjso = "inside getJobShiftObject()--->";
		JobShift jobShiftObject = null;

		if(jobShiftId != null){
			_jobShiftId = " job_shift_id = ? " ;
			_jsi = true;
		}
		if(job != null){
			_job = " job_id = ? ";
			_j = true;
			if(_jsi)
				_job = " and " + _job;
		}
		if(startHour != null){
			_startHour = " start_hour = ? ";
			_sh = true;
			if(_jsi || _j)
				_startHour = " and " + _startHour;
		}
		if(endHour != null){
			_endHour = " end_hour ";
			_eh = true;
			if(_jsi || _j || _sh)
				_endHour = " and " + _endHour;
		}
		
		if(!_jsi && !_j && !_sh && !_eh)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_shift_id,job_id,start_hour,end_hour from job_shift where " + _jobShiftId + _job + _startHour + _endHour;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_jsi)
				ps.setObject(++i,jobShiftId);
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_sh)
				ps.setTime(++i,startHour);
			if(_eh)
				ps.setTime(++i,endHour);

			//System.out.println(gjso + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//System.out.println(gjso + "jobShiftObject : " + (jobShiftObject = new JobShift(rs.getInt(1),Job.getJobObject(rs.getInt(2)),rs.getTime(3),rs.getTime(4))));
				jobShiftObject = new JobShift(rs.getInt(1),Job.getJobObject(rs.getInt(2)),rs.getTime(3),rs.getTime(4));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobShiftObject;
	}

	//(Integer jobShiftId,Job job,Time startHour,Time endHour)
	public static JobShift updateJobShift(Job job,Time startHour,Time endHour){
		JobShift jobShiftObject = null;
		String ujs = "inside updateJobShift()--->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "insert into job_shift(job_id,start_hour,end_hour) value(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			ps.setObject(++i,job.getJobId());
			ps.setObject(++i,startHour);
			ps.setObject(++i,endHour);

			System.out.println(ujs + "PreparedStatement : " + ps);
			System.out.println(ujs + "RowsEffected : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println(ujs + "Updating jobShiftObject : " + (jobShiftObject = JobShift.getJobShiftObject(rs.getInt(1),null,null,null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobShiftObject;
	}


	//getter setters
	public void setJobShiftId(Integer jobShiftId){
		this.jobShiftId=jobShiftId;
	}
	public Integer getJobShiftId(){
		return jobShiftId;
	}

	public void setJob(Job job){
		this.job=job;
	}
	public Job getJob(){
		return job;
	}

	public void setStartHour(Time startHour){
		this.startHour=startHour;
	}
	public Time getStartHour(){
		return startHour;
	}

	public void setEndHour(Time endHour){
		this.endHour=endHour;
	}
	public Time getEndHour(){
		return endHour;
	}
}