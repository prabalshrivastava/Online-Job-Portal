package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import utilities.Db;
import java.util.ArrayList;

public class CandidateLanguage{
	//member variables
	private Integer				candidateLanguageId;
	private Language			language;
	private LanguageCapability	languageCapability;
	private Candidate			candidate;
	
	//constructors
	public CandidateLanguage(Integer candidateLanguageId , Language language , LanguageCapability languageCapability , Candidate candidate){
		setCandidateLanguageId(candidateLanguageId);
		setLanguage(language);
		setLanguageCapability(languageCapability);
		setCandidate(candidate);
	}



	//methods()
	//@override toString
	public String toString(){
		return "{'candidateLanguageId' : " + candidateLanguageId + " , 'language' : " + language + " , 'languageCapability' : " + languageCapability + " , 'candidate' : " + candidate + "}";
	}




	public static CandidateLanguage getCandidateLanguageObject(Integer candidateLanguageId , Language language , LanguageCapability languageCapability , Candidate candidate){
		String gclo = "inside getCandidateLanguageObject-->";
		String _candidateLanguageId = "" , _language = "" , _languageCapability="" , _candidate = "";
		Boolean _cli , _l , _lc , _c;
		_cli = _l = _lc = _c = false;

		CandidateLanguage candidateLanguageObject = null;

		if(candidateLanguageId != null){
			_candidateLanguageId = " candidate_language_id = ? ";
			_cli = true;
		}
		
		if(language != null){
			_language = " language_id = ? ";
			_l = true;
			if(_cli)
				_language = " and " + _language;
		}

		if(languageCapability != null){
			_languageCapability = " language_capability_id = ? " ;
			_lc = true;
			if(_cli || _l)
				_languageCapability = " and " + _languageCapability;

		}

		if(candidate != null){
			_candidate = " candidate_id = ? " ;
			_c = true;
			if(_cli || _l || _lc)
				_candidate = " and " + _candidate;
		}

		if(!_cli && !_l && !_lc &&!_c)
			return null;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select  candidate_language_id  ,  language_id  ,  language_capability_id  ,  candidate_id from candidate_language where " + _candidateLanguageId + _language + _languageCapability + _candidate;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_cli)
				ps.setInt(++i , candidateLanguageId);

			if(_l)
				ps.setInt(++i , language.getLanguageId());

			if(_lc)
				ps.setInt(++i , languageCapability.getLanguageCapabilityId());

			if(_c)
				ps.setInt(++i , candidate.getCandidateId());

//			System.out.println("\n\n"+gclo + "PreparedStatement : " + ps);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
//				System.out.println(gclo + (candidateLanguageObject = new CandidateLanguage(rs.getInt(1) , Language.getLanguageObject(rs.getInt(2) , null) , LanguageCapability.getLanguageCapabilityObject(rs.getInt(3) , null) , candidate.getCandidateObject(candidate , null))));
				candidateLanguageObject = new CandidateLanguage(rs.getInt(1) , Language.getLanguageObject(rs.getInt(2) , null) , LanguageCapability.getLanguageCapabilityObject(rs.getInt(3) , null) , candidate.getCandidateObject(candidate , null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return candidateLanguageObject;
	}	


	public static ArrayList<CandidateLanguage> collectCandidateLanguages(Candidate candidate,Language language){
		//pass null to collect all CandidateLanguage Object in ArrayList
		//pass language to collect all candidate with language passed
		CandidateLanguage cl = null;
		String _candidate = "",_language = "";
		Boolean _c = false,_l = true;
		String ccloc = "inside collectCandidateLanguageOdCandidate()-->";
		ArrayList languageList =  new ArrayList();

		if(candidate != null){
			_candidate = " where  candidate_id = ? ";
			_c = true;
		}

		if(language != null){
			_language = " where language_id = ? ";
			_l = true;
			if(_c)
				_language = " and language_id = ? ";
		}


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select candidate_language_id , language_id , language_capability_id , candidate_id from candidate_language" + _candidate + _language;
			PreparedStatement ps = connection.prepareStatement(query);
			

			int i = 0;
			if(_c)
				ps.setInt(++i , candidate.getCandidateId());
			if(_l)
				ps.setObject(++i , language);
			
			System.out.println(ccloc + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				languageList.add(new CandidateLanguage(rs.getInt(1) , Language.getLanguageObject(rs.getInt(2) , null) , LanguageCapability.getLanguageCapabilityObject(rs.getInt(3) , null) , candidate.getCandidateObject(new Candidate(rs.getInt(4)) , null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return languageList;
	}




	public static CandidateLanguage updateCandidateLanguage(Integer candidateLanguageId , Language language , LanguageCapability languageCapability , Candidate candidate , Boolean isAjaxRequest , CandidateLanguage previousCandidateLanguage){
		String ucl = "inside updateCandidateLanguage-->";
		CandidateLanguage candidateLanguageObject = null;
		String _candidateLanguageId = "" , _language = "" , _languageCapability = "" , _candidate = "" , _previousCandidateLanguage = "";
		Boolean _cl , _l , _lc , _c , _pcl;
		_cl = _l = _lc  = _c = _pcl = false;
		
		if(candidateLanguageId != null){
			_candidateLanguageId = " candidate_language_id = ? ";
			_cl = true;
		}

		if(language != null){
			_language = " language_id = ? ";
			_l = true;
			if(_cl)
				_language = " , " + _language;
		}

		if(languageCapability != null){
			_languageCapability = " language_capability_id = ? ";
			_lc = true;
			if(_cl || _l)
				_languageCapability = " , " + _languageCapability;
		}

		if(candidate != null){
			_candidate = " candidate_id = ? " ;
			_c = true;
			if(_cl || _l || _lc)
				_candidate = " , " + _candidate;
		}

		if(previousCandidateLanguage != null){
			_previousCandidateLanguage = " candidate_language_id = ? ";
			_pcl = true;
			if(_c)
				_previousCandidateLanguage = " and " + _previousCandidateLanguage;
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);

			String query;

			if(isAjaxRequest)
				query = "update candidate_language set " + _candidateLanguageId + _language + _languageCapability + _candidate + "where candidate_id = ? " + _previousCandidateLanguage;
			else
				query = "insert into candidate_language(language_id , language_capability_id , candidate_id) values (? , ? , ?)";
				
			PreparedStatement ps = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			if(_cl && isAjaxRequest)
				ps.setInt(++i , candidateLanguageId);

			if(_l)
				ps.setInt(++i , language.getLanguageId());

			if(_lc)
				ps.setInt(++i , languageCapability.getLanguageCapabilityId());

			if(_c)
				ps.setInt(++i , candidate.getCandidateId());


			if(isAjaxRequest && _c){
				ps.setInt(++i , candidate.getCandidateId());

				if(_pcl)
					ps.setInt(++i , previousCandidateLanguage.getCandidateLanguageId());
			}


			System.out.println(ucl + "PreparedStatement : " + ps);
			System.out.println(ucl + "RowsEffected : " + ps.executeUpdate());	
			ResultSet rs = ps.getGeneratedKeys();
				
			
			
			if(rs.next()){
				System.out.println(ucl + (candidateLanguageObject = new CandidateLanguage(rs.getInt(1) , language , languageCapability , Candidate.getCandidateObject(candidate , null))));
				//look at candidate
				//we are doing so for storing updated candidate object
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return candidateLanguageObject;
	}











	//getter setters
	public void setCandidateLanguageId(Integer candidateLanguageId){
		this.candidateLanguageId=candidateLanguageId;
	}
	public Integer getCandidateLanguageId(){
		return candidateLanguageId;
	}

	public void setLanguage(Language language){
		this.language=language;
	}
	public Language getLanguage(){
		return language;
	}

	public void setLanguageCapability(LanguageCapability languageCapability){
		this.languageCapability=languageCapability;
	}
	public LanguageCapability getLanguageCapability(){
		return languageCapability;
	}


	public void setCandidate(Candidate candidate){
		this.candidate = candidate;
	}
	public Candidate getCandidate(){
		return candidate;
	}
}