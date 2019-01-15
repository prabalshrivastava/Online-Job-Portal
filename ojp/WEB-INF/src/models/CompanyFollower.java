package models;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import java.util.ArrayList;
import utilities.Db;

public class CompanyFollower{
	//member variable
	private Integer		companyFollowerId;
	private	Company		company;
	private	Candidate	candidate;
	
	
	//constructors 
	public CompanyFollower(){
	}

	public CompanyFollower(Integer companyFollowerId){
		setCompanyFollowerId(companyFollowerId);
	}

	public CompanyFollower(Integer companyFollowerId,Company company,Candidate candidate){
		setCompanyFollowerId(companyFollowerId);
		setCompany(company);
		setCandidate(candidate);
	}

	//methods
	//@override toString
	public String toString(){
		return "{'companyFollowerId' : " + companyFollowerId + " , 'company' : " + company + " , 'candidate' : " + candidate + "}";
	}
	
	public static CompanyFollower getCompanyFollowerObject(Integer companyFollowerId,Company company,Candidate candidate){
		String gcfo = "inside getCompanyFollowerObject()--->";
		String _companyFollowerId = "",_company = "",_candidate = "";
		Boolean _cfi = false,_com = false,_can = false;
		CompanyFollower companyFollowerObject = null;

		if(companyFollowerId != null){
			_companyFollowerId = " company_follower_id = ? ";
			_cfi = true;
		}
		if(company != null){
			_company = " company_id = ? ";
			_com = true;
			if(_cfi)
				_companyFollowerId = " and " + _companyFollowerId;
		}
		if(candidate != null){
			_candidate = " candidate_id = ? ";
			_can = true;
			if(_cfi || _com)
				_candidate = " and " + _candidate;
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select company_follower_id,company_id,candidate_id from company_follower where " + _companyFollowerId + _company + _candidate;
			PreparedStatement ps = connection.prepareStatement(query);


			int i = 0;
			if(_cfi)
				ps.setObject(++i,companyFollowerId);
			if(_com)
				ps.setObject(++i,company.getCompanyId());
			if(_can)
				ps.setObject(++i,candidate.getCandidateId());

//			System.out.println(gcfo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println(gcfo + "companyFolowerObject : " + (companyFollowerObject = new CompanyFollower(rs.getInt(1),Company.getCompanyObject(new Company(rs.getInt(2)),null),Candidate.getCandidateObject(new Candidate(rs.getInt(3)),null))));
				companyFollowerObject = new CompanyFollower(rs.getInt(1),Company.getCompanyObject(new Company(rs.getInt(2)),null),Candidate.getCandidateObject(new Candidate(rs.getInt(3)),null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return companyFollowerObject;
	}

	//(Integer companyFollowerId,Company company,Candidate candidate){
	public static CompanyFollower updateCompanyFollower(Company company,Candidate candidate){
		String ucf = "inside updateCompanyFollower()--->";
		CompanyFollower companyFollowerObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "iinsert into company_followers(company_id,candidate_id) value(?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			ps.setObject(++i,company.getCompanyId());
			ps.setObject(++i,candidate.getCandidateId());
			
			System.out.println(ucf + "PreparedStatement : " + ps);
			System.out.println(ucf + "Rows Effected : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				System.out.println(ucf + "updating companyFollower : " + (companyFollowerObject = CompanyFollower.getCompanyFollowerObject(rs.getInt(1),null,null)));
			connection.close();			
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return companyFollowerObject;
	}

	public static ArrayList<CompanyFollower> collectCompanyFollowers(Company company,Candidate candidate){
		String ccf = "inside collectCompanyFollower()--->";
		ArrayList<CompanyFollower> companyFollowers = null;
		
		String _company = "",_candidate = "";
		Boolean _com = false,_can = false;

		if(company != null){
			_company = " where company_id = ? " ;
			_com = true;
		}
		if(candidate != null){
			_candidate = " where candidate_id = ? ";
			_can = true;
			if(_com)
				_candidate = " and candidate_id = ? ";
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("localhost:3306/ojp?user=root&passowrd=" + Db.dbpass);
			String query = "select company_follower_id,company_id,candidate_id from company_followers" + _company + _candidate;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_com)
				ps.setObject(++i,company.getCompanyId());
			if(_can)
				ps.setObject(++i,candidate.getCandidateId());
			System.out.println(ccf + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			companyFollowers = new ArrayList<CompanyFollower>();
			while(rs.next()){
				companyFollowers.add(new CompanyFollower(rs.getInt(1),Company.getCompanyObject(new Company(rs.getInt(3)),null),Candidate.getCandidateObject(new Candidate(rs.getInt(2)),null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(ccf + "companyFollowers : " + companyFollowers);
		return companyFollowers;
	}

	//getter setters
	public void setCompanyFollowerId(Integer companyFollowerId){
		this.companyFollowerId=companyFollowerId;
	}
	public Integer getCompanyFollowerId(){
		return companyFollowerId;
	}

	public void setCompany(Company company){
		this.company=company;
	}
	public Company getCompany(){
		return company;
	}

	public void setCandidate(Candidate candidate){
		this.candidate=candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}

}
