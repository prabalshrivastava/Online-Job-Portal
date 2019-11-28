package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import utilities.Db;

public class JobQualification{
	//member variables
	private Integer	jobQualificationId;
	private Job		job;
	private	Course	course;
	
	//Constructors
	public JobQualification(){
	}

	public JobQualification(Integer jobQualificationId){
		setJobQualificationId(jobQualificationId);
	}

	public JobQualification(Integer jobQualificationId,Job job,Course course){
		setJobQualificationId(jobQualificationId);
		setJob(job);
		setCourse(course);
	}

	//methods
	//@override toString()
	public String toString(){
		return "{'jobQualificationId' : " + jobQualificationId + " , 'job' : " + job + " , 'course' : " + course + "}";
	}
	
	
	public static JobQualification getJobQualificationObject(Integer jobQualificationId,Job job,Course course){
		String gjqo = "inside getJobQualificationObject()--->";
		String _jobQualificationId = "" , _job = "" , _course = "";
		Boolean _jqi = false,_j = false,_c = false;
		JobQualification jobQualificationObject = null;

		/*
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(gjqo + "jobQualficationId : " + jobQualificationId);
		System.out.println(gjqo + "job : " + job);
		System.out.println(gjqo + "course : " + course);
		*/

		if(jobQualificationId != null){
			_jobQualificationId = " job_qualification_id = ? ";
			_jqi = true;
		}

		if(job != null){
			_job = " job_id = ? ";
			_j = true;
			if(_jqi)
				_job = " and " + _job;
		}
		if(course != null){
			_course = " course_id = ? " ;
			_c = true;
			if(_jqi || _j)
				_course = " and " + _course;
		}
		
		if(!_jqi && !_j && !_c){
			System.out.println(gjqo + "returning");
			return null;
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_qualification_id,job_id,course_id from job_qualification where " + _jobQualificationId + _job + _course;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_jqi)
				ps.setObject(++i,jobQualificationId);
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_c)
				ps.setObject(++i,course.getCourseId());

			//System.out.println(gjqo + "PreparedStatment : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				//System.out.println(gjqo + "jobQualificationObject : " + (jobQualificationObject = new JobQualification(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Course.getCourseObject(rs.getInt(3),null))));
				jobQualificationObject = new JobQualification(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Course.getCourseObject(rs.getInt(3),null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobQualificationObject;
	}

	//(Integer jobQualificationId,Job job,Course course){
	public static JobQualification updateJobQualification(Job job,Course course){
		String ujq = "inside updateJobQualification()--->";
		JobQualification jobQualificationObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "insert into job_qualification(job_id,course_id) value(?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			ps.setObject(++i,job.getJobId());
			ps.setObject(++i,course.getCourseId());

			System.out.println(ujq + "PreparedStatement : " + ps);
			System.out.println(ujq + "RowsEffected : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println(ujq + " updating jobQualificationObject : " + (jobQualificationObject = JobQualification.getJobQualificationObject(rs.getInt(1),null,null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jobQualificationObject;
	}



	public static ArrayList<JobQualification> collectJobQualifications(Job job,Course course){
		String cjq = "inside collectJobQualifications()--->";
		ArrayList<JobQualification> jobQualifications = null;

		String _job = "",_course = "";
		Boolean _j = false,_c = false;

		if(job != null){
			_j = true;
			_job = " where job_id = ? ";
		}

		if(course != null){
			_c = true;
			_course = " where course_id = ? ";
			if(_j)
				_course = " and course_id = ? ";
		}


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select job_qualification_id,job_id,course_id from job_qualification " + _job + _course;
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_j)
				ps.setObject(++i,job.getJobId());
			if(_c)
				ps.setObject(++i,course.getCourseId());

			System.out.println(cjq + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			jobQualifications = new ArrayList<JobQualification>();
			while(rs.next()){
				jobQualifications.add(new JobQualification(rs.getInt(1),Job.getJobObject(rs.getInt(2)),Course.getCourseObject(rs.getInt(3),null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(cjq + "jobQualifications : " +jobQualifications);
		return jobQualifications;
	}

	//getter setters
	public void setJobQualificationId(Integer jobQualificationId){
		this.jobQualificationId=jobQualificationId;
	}
	public Integer getJobQualificationId(){
		return jobQualificationId;
	}

	public void setJob(Job job){
		this.job=job;
	}
	public Job getJob(){
		return job;
	}

	public void setCourse(Course course){
		this.course=course;
	}
	public Course getCourse(){
		return course;
	}
}