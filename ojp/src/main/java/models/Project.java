package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utilities.Db;
import java.util.ArrayList;


public class Project{
	//member variables
	private Integer		projectId;
	private String		title;
	private Candidate	candidate;
	private Integer		members;
	private	String		duration;
	private	String		details;
	private String		otherDetails;




	//constructors
	public Project(){
	}

	public Project(Integer projectId,String title,Candidate candidate,Integer members,String duration,String details,String otherDetails){
		setProjectId(projectId);
		setTitle(title);
		setCandidate(candidate);
		setMembers(members);
		setDuration(duration);
		setDetails(details);
		setOtherDetails(otherDetails);
	}





	//members
	//@override toString()
	public String toString(){
		return "{'projectId' : " + projectId + ",'title' : '" + title + "','candidate' : " + candidate + ",'members' : " + members + ",'duration' : '" + duration + "','details' : '" + details + "','otherDetails' : '" + otherDetails + "'}";
	}

	public static Project getProjectObject(Candidate candidate){
		String gpo = "inside getProjectObject()-->";
		Project projectObject = null;
		String _candidate = "";
		Boolean _c = false;
		if(candidate != null){
			_candidate = " where candidate_id = ? ";
			_c = true;
		}else{
			return null;
		}
		

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select project_id,title,candidate_id,members,duration,details,other_details from projects " + _candidate;
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_c)
				ps.setInt(++i,candidate.getCandidateId());

			System.out.println(gpo + "PreparedStatement : "+ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(gpo + (projectObject =  new Project((Integer)rs.getObject(1),(String)rs.getObject(2),Candidate.getCandidateObject(new Candidate(rs.getInt(3)),null),(Integer)rs.getObject(4),(String)rs.getObject(5),(String)rs.getObject(6),(String)rs.getObject(7))));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return projectObject;
	}




	public static Project updateProjectDetails(String title,Candidate candidate,Integer members,String duration,String details,String otherDetails,Boolean isAjaxRequest){
		String upd = "inside updateProjectDetails()-->";
		String query = "";
		Project project = null;
		

		if(title == null)
			title = "";
		
		if(members == null)
			members = 1;

		if(duration == null)
			duration = "";

		if(details == null)
			details = "";
			

		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			if(isAjaxRequest){
				query = "update projects set title = ?,candidate_id = ?,members = ?,duration = ?,details = ?,other_details = ? where candidate_id = ?";
			}else{
				query = "insert into projects(title,candidate_id,members,duration,details,other_details) value(?,?,?,?,?,?)";
			}


			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1,title);
			ps.setObject(2,candidate.getCandidateId());
			ps.setObject(3,members);
			ps.setObject(4,duration);
			ps.setObject(5,details);
			ps.setObject(6,otherDetails);
			
			if(isAjaxRequest){
				ps.setInt(7,candidate.getCandidateId());
			}
			
			//System.out.println(upd + "PreparedStatement : " + ps);
			//System.out.println(upd + "Rows Effected : " + ps.executeUpdate());
			ResultSet project_id = ps.getGeneratedKeys();

			if(project_id.next()){
				//System.out.println(upd + (project = new Project(project_id.getInt(1),title,candidate,members,duration,details,otherDetails)));
				project = new Project(project_id.getInt(1),title,candidate,members,duration,details,otherDetails);
			}

			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return project;
	}

	public static ArrayList<Project> collectProjects(Candidate candidate){

		String _candidate = "";
		Boolean _c = false;
		ArrayList<Project> projects = new ArrayList<Project>();
		String cp = "inside collectPorjects()--->";

		if(candidate != null){
			_candidate = " where candidate_id = ? ";
			_c = true;
		}


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select project_id,title,candidate_id,members,duration,details,other_details from projects " + _candidate;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_c)
				ps.setObject(++i,candidate.getCandidateId());

			System.out.println(cp + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				projects.add(new Project(rs.getInt(1),rs.getString(2),Candidate.getCandidateObject(new Candidate(rs.getInt(3)),null),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(cp + "Collection of Projects : " + projects);
		return projects;
	}



	//getter setters
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}
	public Integer getProjectId(){
		return projectId;
	}

	
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}


	public void setCandidate(Candidate candidate){
		this.candidate = candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}
	

	public void setMembers(Integer members){
		this.members = members;
	}
	public Integer getMembers(){
		return members;
	}


	public void setDuration(String duration){
		this.duration = duration;
	}
	public String getDuration(){
		return duration;
	}


	public void setDetails(String details){
		this.details = details;
	}
	public String getDetails(){
		return details;
	}


	public void setOtherDetails(String otherDetails){
		this.otherDetails = otherDetails;
	}
	public String getOtherDetails(){
		return otherDetails;
	}
}