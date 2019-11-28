package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import utilities.Db;

public class CandidateSkill{

	//member variables
	private Integer		candidateSkillId;
	private Skill		skill;
	private String		version;
	private	String		duration;
	private String		certificate;
	private	Candidate	candidate;


	//constructors
	public CandidateSkill(){
	}

	public CandidateSkill(Integer candidateSkillId){
		setCandidateSkillId(candidateSkillId);
	}

	public CandidateSkill(Integer candidateSkillId,Skill skill,String version,String duration,Candidate candidate){
		setCandidateSkillId(candidateSkillId);
		setSkill(skill);
		setVersion(version);
		setDuration(duration);
		setCandidate(candidate);
	}

	public CandidateSkill(Integer candidateSkillId,Skill skill,String version,String duration,Candidate candidate,String certificate){
		this(candidateSkillId,skill,version,duration,candidate);
		setCertificate(certificate);
	}
	
	//methods
	//@override toString()
	public String toString(){
		return "{'candidateSkillId' : '" + candidateSkillId + "' , ' skill' : " + skill + " , 'version' : '" + version + "' , 'duration' : '" + duration + "' , ' candidate' : " + candidate + "}";
	}
	
	public static CandidateSkill getCandidateSkillObject(Integer candidateSkillId,Skill skill,String version,String duration,Candidate candidate,String certificate){
		String gcso = "inside getCandidateSkillObject()--->";
		CandidateSkill candidateSkillObject = null;
		String _candidateSkillId = "" , _skill = "" , _version = "" , _duration = "" , _candidate = "",_certificate = "";
		Boolean _csi = false,_s = false , _v = false, _d = false , _c = false,_cer = false;
		
		if(candidateSkillId != null){
			_candidateSkillId = " candidate_skill_id = ? ";
			_csi = true;
		}
		if(skill != null){
			_skill = " skill_id = ? ";
			_s = true;
			if(_csi)
				_candidateSkillId = " and " + _candidateSkillId;
		}
		if(version != null){
			_version = " version = ? ";
			_v = true;
			if(_csi || _s)
				_version = " and " + _version;
		}
		if(duration != null){
			_duration = " duration = ? " ;
			_d = true;
			if(_csi || _s || _v)
				_duration = " and " + _duration;
		}
		if(candidate != null){
			_candidate = " candidate_id = ? ";
			_c = true;
			if(_csi || _s || _v || _d)
				_candidate = " and " + _candidate;
		}
		if(certificate != null){
			_certificate = " certificate = ? ";
			_cer = true;
			if(_csi || _s || _v || _d || _c)
				_certificate = " and " + _certificate;
		}

		if(!_csi && !_s && !_v && !_d && !_c && !_cer)
			return null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select candidate_skill_id,skill_id,version,duration,candidate_id,certificate from candidate_skills where " + _candidateSkillId + _skill + _version + _duration + _candidate + _certificate;
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_csi)
				ps.setObject(++i,candidateSkillId);
			if(_s)
				ps.setObject(++i,skill.getSkillId());
			if(_v)
				ps.setObject(++i,version);
			if(_d)
				ps.setObject(++i,duration);
			if(_c)
				ps.setObject(++i,candidate.getCandidateId());
			if(_cer)
				ps.setObject(++i,certificate);


			//System.out.println(gcso + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println(gcso + "candidateSkillObject : " + (candidateSkillObject = new CandidateSkill(rs.getInt(1),Skill.getSkillObject(rs.getInt(2),null),rs.getString(3),rs.getString(4),Candidate.getCandidateObject(new Candidate(rs.getInt(5)),null),certificate)));
				candidateSkillObject = new CandidateSkill(rs.getInt(1),Skill.getSkillObject(rs.getInt(2),null),rs.getString(3),rs.getString(4),Candidate.getCandidateObject(new Candidate(rs.getInt(5)),null),certificate);
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return candidateSkillObject;
	}

	//(Integer candidateSkillId,Skill skill,String version,String duration,Candidate candidate)
	public static CandidateSkill updateCandidateSkill(Skill skill,String version,String duration,Candidate candidate,String certificate){
		CandidateSkill candidateSkillObject = null;
		String ucs = "inside updateCandidateSkill()--->";
		Boolean _cer = false;
		String _certificate = "";
		if(skill == null && version == null && duration == null && certificate != null){
			_cer = true;
		}


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "";
			query = "insert into candidate_skills(skill_id,version,duration,candidate_id,certificate) values(?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			int i = 0 ;
			ps.setObject(++i,skill.getSkillId());
			ps.setObject(++i,version);
			ps.setObject(++i,duration);
			ps.setObject(++i,candidate.getCandidateId());
			ps.setObject(++i,certificate);
			System.out.println(ucs + "PreparedStatement : " + ps);
			System.out.println(ucs + "Rows Effected : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();

			if(rs.next()){
				System.out.println(ucs + "updating candidateSkill : " + (candidateSkillObject = new CandidateSkill(rs.getInt(1),skill,version,duration,candidate,certificate)));
			}
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return candidateSkillObject;
	}


	
	public static ArrayList<CandidateSkill> collectCandidateSkills(Skill skill,String version,String duration,Candidate candidate,String certificate){
		String _skill = "",_version = "",_duration = "",_candidate = "",_certificate = "";
		Boolean _s = false,_v = false,_d = false,_c = false,_cer = false;

		ArrayList<CandidateSkill> candidateSkills = null;
		String ccs = "inside collectCandidateSkills()--->";

		if(skill != null){
			_s = true;
			_skill = " where skill_id = ? ";
		}
		if(version != null){
			_v = true;
			_version = " where version = ? ";
			if(_s)
				_version = " and version = ? ";
		}
		if(duration != null){
			_d = true;
			_duration = " where duration = ? ";
			if(_s || _v)
				_duration = " and duration = ? ";
		}

		if(candidate != null){
			_c = true;
			_candidate = " where candidate_id = ? ";
			if(_s || _v || _d)
				_candidate = " and candidate_id = ? ";
		}
		if(certificate != null){
			_cer = true;
			_certificate = " where certificate = ? ";
			if(_s || _v || _d || _c)
				_certificate = " and certificate = ? ";

		}

		//System.out.println(_c);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select candidate_skill_id,skill_id,version,duration,candidate_id,certificate from candidate_skills " + _skill + _version + _duration + _candidate + _certificate;

			System.out.println(query);
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_s)
				ps.setObject(++i,skill.getSkillId());
			if(_v)
				ps.setObject(++i,version);
			if(_d)
				ps.setObject(++i,duration);
			if(_c)
				ps.setObject(++i,candidate.getCandidateId());
			if(_cer)
				ps.setObject(++i,certificate);

			System.out.println(ccs + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			candidateSkills = new ArrayList<CandidateSkill>();
			while(rs.next()){
				candidateSkills.add(new CandidateSkill(rs.getInt(1),Skill.getSkillObject(rs.getInt(2),null),rs.getString(3),rs.getString(4),Candidate.getCandidateObject(new Candidate(rs.getInt(5)),null),rs.getString(6)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(ccs + "candidateSkills : " + candidateSkills);
		return candidateSkills;
	}


	//getter setter
	public void setCandidateSkillId(Integer candidateSkillId){
		this.candidateSkillId = candidateSkillId;
	}
	public Integer getCandidateSkillId(){
		return candidateSkillId;
	}

	public void setSkill(Skill skill){
		this.skill=skill;
	}
	public Skill getSkill(){
		return skill;
	}

	public void setVersion(String version)	{
		this.version=version;
	}
	public String getVersion(){
		return version;
	}

	public void setDuration(String duration){
		this.duration=duration;
	}
	public String getDuration(){
		return duration;
	}

	public void setCandidate(Candidate candidate){
		this.candidate=candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}

	public void setCertificate(String certificate){
		this.certificate = certificate;
	}
	public String getCertificate(){
		return certificate;
	}
}