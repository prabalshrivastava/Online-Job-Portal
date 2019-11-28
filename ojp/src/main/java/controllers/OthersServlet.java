package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Candidate;
import models.Language;
import models.LanguageCapability;
import models.CandidateLanguage;
import java.util.Arrays;


public class OthersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String os = "inside OtherServlet--->";
		String nextPage = "showlogin.do";
		
		String strengths = null,weaknesses = null,hobbies = null,curricularActivities = null,intrest = null;
		
		//read write speak
		String _rws[] = null;		
		Boolean r = false,w = false,s = false;
		Integer rws[] = null;

		String _language[] = null; 
		Integer language[] = null;

		String _langCount = "0";
		Integer langCount = null;
		
		String code =  null;
		Boolean isAjaxRequest = false;


		String _previousCandidateLanguage[] = null;
		Integer previousCandidateLanguageId[] = null;
		CandidateLanguage previousCandidateLanguageObject[] = null;


		if(candidate != null){
			nextPage = "showexperiencedetails.do";
			System.out.println(os + "strengths : " + (strengths = request.getParameter("strengths")));
			System.out.println(os + "weaknesses : " + (weaknesses = request.getParameter("weaknesses")));
			System.out.println(os + "hobbies : " + (hobbies = request.getParameter("hobbies")));
			System.out.println(os + "intrest : " + (intrest = request.getParameter("intrest")));
			System.out.println(os + "curricularActivities : " + (curricularActivities = request.getParameter("curricularactivities")));
			System.out.println(os + "_language : " + Arrays.toString(_language = request.getParameterValues("language")));
			System.out.println(os + "code : " + (code = request.getParameter("code")));
			System.out.println(os + "_langcount : "  + (_langCount = request.getParameter("langcount")));
			System.out.println(os + "_previousCandidateLanguage : " + Arrays.deepToString(_previousCandidateLanguage = request.getParameterValues("candidatelanguageid")));
			System.out.println(os + "_previousCandidateLanguage : " + _previousCandidateLanguage);

			System.out.println(_language.length);
			System.out.println(_previousCandidateLanguage.length);
			

			try{

				if(_langCount != null)
					langCount = Integer.parseInt(_langCount);
				else
					langCount = 0;

				_rws = new String[langCount+1];
				rws = new Integer[langCount+1];
				language = new Integer[langCount+1];

				previousCandidateLanguageId = new Integer[langCount + 1];
				previousCandidateLanguageObject = new CandidateLanguage[langCount + 1];
					
				//for(String _pcl : _previousCandidateLanguage){
				
				//}
				

				

				//langcount+1 because by default we start with 0 languageson othes page
				for(int i = 0;i<langCount+1;i++){
					if(_previousCandidateLanguage[i] != null && _previousCandidateLanguage[i] != ""){
						System.out.println(os + "previousCandidateLanguageId : " + (previousCandidateLanguageId[i] = Integer.parseInt(_previousCandidateLanguage[i])));
						System.out.println(os + "previousCandidateLanguageObject : " + (previousCandidateLanguageObject[i] = CandidateLanguage.getCandidateLanguageObject(previousCandidateLanguageId[i],null,null,candidate)));
					}else{
						System.out.println(os + "previousCandidateLanguage[" + i + "] is NULL");
					}



					System.out.println(os + "getting rws_" + i + " : " + Arrays.toString(_rws = request.getParameterValues("rws_"+i)));
					if(_rws != null){
						for(String lc : _rws){
							System.out.println(os + lc);	

							if(lc.toLowerCase().equals("read"))
								r = true;

							if(lc.toLowerCase().equals("write"))
								w = true;

							if(lc.toLowerCase().equals("speak"))
								s = true;
						}
					}

					if(r && w && s){
						rws[i] = LanguageCapability.READ_WRITE_SPEAK;
						System.out.println("\n" + os + "candidate can read write and speak ");
					}else{
						
						System.out.print("\n" + os + "candidate can : ");
						if(r){
							System.out.println(os + (rws[i] = LanguageCapability.READ));
							System.out.print("READ\t");
						}

						if(w){
							System.out.println(os + (rws[i] = LanguageCapability.WRITE));
							System.out.print("WRITE\t");
						}

						if(s){
							System.out.println(os + (rws[i] = LanguageCapability.SPEAK));
							System.out.print("SPEAK\n");
						}
						if(r && w){
							System.out.println(os + (rws[i] = LanguageCapability.READ_WRITE));
						
						}

						if(r && s){
							System.out.println(os + (rws[i] = LanguageCapability.READ_SPEAK));
						}

						if(w && s){
							System.out.println(os + (rws[i] = LanguageCapability.WRITE_SPEAK));
						}
					}
					//System.out.println(os + "value of rws[" + i + "] : " + rws[i]);
					System.out.println(os + "_language[" + i + "] : " + _language[i]);
					if(_language[i] != null){
						System.out.println(os + "language parsed : " + Language.getLanguageObject((language[i] = Integer.parseInt(_language[i])),null).getLanguage());
						//System.out.println(os + (language[i] = Integer.parseInt(_language[i])));
					}
				}

				System.out.println(os + "previousCandidateLanguageObjectArray : " + Arrays.deepToString(previousCandidateLanguageObject));

				if(code != null)
					isAjaxRequest = code.equals("1");
				else
					isAjaxRequest = CandidateLanguage.getCandidateLanguageObject(null,null,null,candidate)!=null;
				
				
				
				//updating candidate's other details
				session.setAttribute("user" , candidate.updateOtherDetails(strengths,weaknesses,hobbies,curricularActivities,intrest,null));
				
				//updating candidateLanguage
				for(int i=0;i<langCount+1;i++){
					System.out.println(os + "value of rws[" + i + "] : " + rws[i]);
					session.setAttribute("candidatelanguage"+i , CandidateLanguage.updateCandidateLanguage(null,Language.getLanguageObject(language[i],null),LanguageCapability.getLanguageCapabilityObject(rws[i],null),candidate,isAjaxRequest,previousCandidateLanguageObject[i]));
				}
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
		}else{
			System.out.println(os + "session Expired");
		}
		if(isAjaxRequest){
			System.out.println(os + "AJAX REQUEST WAS RECIEVED------------------>NOW SENDING RESPONSE");
			//response.getWriter().write("successfully updated other details");
			request.getRequestDispatcher(nextPage).forward(request,response);
		}else
			request.getRequestDispatcher(nextPage).forward(request,response);
	}
}