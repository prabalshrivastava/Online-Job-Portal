package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import utilities.Db;


public class Skill{
	//member variables
	private Integer skillId;
	private String	skill;


	

	//constructors
	public Skill(Integer skillId,String skill){
		setSkillId(skillId);
		setSkill(skill);
	}




	//methods()
	//@override toString()
	public String toString(){
		return "{'skillId' : " + skillId + ",'skill' : '" + skill + "'}";
	}
	
	//not yet tested
	public static Skill getSkillObject(Integer skillId,String skill){
		String gso = "inside getSkilObject()--->";
		String _skillId = "",_skill = "";
		Boolean _si = false,_s = false;
		Skill skillObject = null;

		if(skillId != null){
			_skillId = " skill_id = ? ";
			_si = true;
		}
		if(skill != null){
			_skill = " skill = ? ";
			_s = true;
			if(_si)
				_skill = " and " + _skill;
		}

		if(!_si && !_s)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select skill_id, skill from skills where " + _skillId + _skill;
			PreparedStatement ps = connection.prepareStatement(query);
			int  i = 0;
			if(_si)
				ps.setObject(++i,skillId);
			if(_s)
				ps.setObject(++i,skill);

			//System.out.println(gso + "PreparedStatement : "  + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//System.out.println(gso + "skilObject : "  + (skillObject = new Skill(rs.getInt(1),rs.getString(2))));
				skillObject = new Skill(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return skillObject;
	}



	public static ArrayList collectAllSkills(){
		String cas = "inside collectAllSkills()-->";
		ArrayList skills = new ArrayList();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select skill_id,skill from skills";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cas + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				skills.add(new Skill(rs.getInt(1),rs.getString(2)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return skills;
	}




	//getter setters
	public void setSkillId(Integer skillId){
		this.skillId=skillId;
	}
	public Integer getSkillId(){
		return skillId;
	}

	public void setSkill(String skill){
		this.skill=skill;
	}
	public String getSkill(){
		return skill;
	}

}