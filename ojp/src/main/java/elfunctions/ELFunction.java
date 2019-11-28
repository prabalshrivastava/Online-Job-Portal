package elfunctions;

import javax.servlet.jsp.PageContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import models.Project;

public class ELFunction{
	public static void assignDisplay(Object key,PageContext pageContext){

		ServletContext context = pageContext.getServletContext();
		HttpSession session = pageContext.getSession();

		String ad = "inside assignDisplay()-->";
		//Project project = (Project)session.getAttribute(key);
		
		/*
		System.out.println(ad + "property : " + property);
		System.out.println(ad + "prop : "+ prop);
		System.out.println(ad + "pageContext : " + pageContext);
		*/
			
		System.out.println(ad + "key : " + key);

		if(key == null){
			context.setAttribute("form" ," style='display:block'");
			context.setAttribute("report" , " style='display:none' ");

		}else{
			context.setAttribute("form" , " style='display:none' ");
			context.setAttribute("report" , "style='display:block'");

		}
	}
}