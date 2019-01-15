package models;

import java.sql.Date;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import utilities.Db;
import utilities.ConnectionPool;
import java.util.ArrayList;


public class Qualification{

	//member variables
	private Integer			qualificationId;
	private Course			course;
	private Candidate		candidate;
	private Organization	organization;
	private Date			passingYear;
	private Float			score;
	private String			certificatePath;

	


	//constructors
	public Qualification(){
	}

	public Qualification(Integer qualificationId,Course course,Candidate candidate,Organization organization,Date passingYear,Float score,String certificatePath){
		setQualificationId(qualificationId);
		setCourse(course);
		setCandidate(candidate);
		setOrganization(organization);
		setPassingYear(passingYear);
		setScore(score);
		setCertificatePath(certificatePath);
	}




	//methods()
	//@override toString()
	public String toString(){
		return "{'qualificationId' : " + qualificationId + ",'course' : " + course + ",'candidate' : '" + candidate + "'organization : '" + organization + ",'passingYear' : '" + passingYear + "','score' : " + score + ",'certificatePath' : '" + certificatePath + "'}";
	}

	public static Qualification updateQualificationDetails(Integer qualificationId,Course course,Candidate candidate,Organization organization,Date passingYear,Float score,String certificatePath){
		String uqd = "inside updateQualificationDetails()--->";
		System.out.println(uqd);
		Qualification qualificationObject = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "insert into qualifications(course_id,candidate_id,organization_id,passing_year,score,certificate_path) value (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			int i = 0;
			ps.setInt(++i,course.getCourseId());
			ps.setInt(++i,candidate.getCandidateId());
			ps.setInt(++i,organization.getOrganizationId());
			ps.setObject(++i,passingYear);
			ps.setObject(++i,score);
			ps.setObject(++i,certificatePath);
		

			System.out.println(uqd + "Rows Effected : " + ps.executeUpdate());
			ResultSet qualification_id = ps.getGeneratedKeys();

			if(qualification_id.next()){
				System.out.println(qualificationObject = new Qualification(qualification_id.getInt(1),course,candidate,organization,passingYear,score,certificatePath));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return qualificationObject;
	}

	public static Qualification getQualificationObject(Integer qualificationId,Course course,Candidate candidate,Organization organization,Date passingYear,Float score,String certificatePath){
		String gqo = "inside getQualificationObject()--->";
		Qualification qualificationObject = null;
		
		String _qualificationId = "",_course = "",_candidate = "" ,_organization = "",_passingYear = "",_score = "",_certificatePath = "";
		Boolean _qi,_cou,_can,_o,_py,_s,_cp;
		_qi = _cou = _can = _o = _py = _s = _cp = false;

		if(qualificationId != null){
			_qi = true;
			_qualificationId = " qualification_id = ? ";
		}
		if(course != null){
			_cou = true;
			_course = " course_id = ? ";
			if(_qi)
				_course = " and " + _course;
		}
		if(candidate != null){
			_can = true;
			_candidate = " candidate_id = ? ";
			if(_qi || _cou)
				_candidate = " and " + _candidate;
		}
		if(organization != null){
			_o = true;
			_organization = " organization_id = ? ";
			if(_qi || _cou || _can)
				_organization = " and " + _organization;
		}
		if(passingYear != null){
			_py = true;
			_passingYear = " passing_year = ? ";
			if(_qi || _cou || _can || _o)
				_passingYear = " and " + _passingYear;
		}
		if(certificatePath != null){
			_cp = true;
			_certificatePath = " certificate_path = ? ";
			if(_qi || _cou || _can || _o || _py)
				_certificatePath = " and " + _certificatePath;
		}
		if(!_qi && !_cou && !_can && !_o && !_py && !_cp)
			return null;
		

		try{
			Connection connection = ConnectionPool.getConnection();
			String query = "select qualification_id,course_id,candidate_id,organization_id,passing_year,certificate_path from qualifications where " + _qualificationId + _course + _candidate + _organization + _passingYear + _certificatePath;
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_qi)
				ps.setObject(++i,qualificationId);
			if(_cou)
				ps.setObject(++i,course.getCourseId());
			if(_can)
				ps.setObject(++i,candidate.getCandidateId());
			if(_o)
				ps.setObject(++i,organization.getOrganizationId());
			if(_py)
				ps.setObject(++i,passingYear);
			if(_cp)
				ps.setObject(++i,certificatePath);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return qualificationObject;
	}

	//getter setters
	public void setQualificationId(Integer qualificationId){
		this.qualificationId = qualificationId;
	}
	public Integer getQualificationId(){
		return qualificationId;
	}

	public void setCourse(Course course){
		this.course = course;
	}
	public Course getCourse(){
		return course;
	}
	
	public void setCandidate(Candidate candidate){
		this.candidate = candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}

	public void setOrganization(Organization organizaion){
		this.organization = organization;
	}
	public Organization getOrganization(){
		return organization;
	}
	
	public void setPassingYear(Date passingYear){
		this.passingYear  =  passingYear;
	}
	public Date getPassingYear(){
		return passingYear;
	}

	public void setScore(Float score){
		this.score  =  score;
	}
	public Float getScore(){
		return score;
	}
	
	public void setCertificatePath(String certificatePath){
		this.certificatePath  =  certificatePath;
	}
	public String getCertificatePath(){
		return certificatePath;
	}
}