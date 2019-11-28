package taghandlers;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FirstTagHandler extends SimpleTagSupport{
	private Object field;
	private String fieldName;


	public void doTag() throws JspException{

		PageContext pageContext = (PageContext)getJspContext();
		HttpSession session = pageContext.getSession();

		String fth = "inside FirstTagHandler-->";
		String show = " style='display:block' ";
		String hide = " style='display:none' ";
	
		System.out.println("\n" +  fth + "fieldname : " + fieldName);
		System.out.println(fth + " field : " + field);


		if(field == null || field.toString().trim().equals("")){
			
			System.out.println(fth + "form : " + show);
			System.out.println(fth + "report : " + hide + "\n");

			session.setAttribute("form" , show);
			session.setAttribute("report" , hide);
		}else{

			System.out.println(fth + "form : " + hide);
			System.out.println(fth + "report : " +show + "\n");

			session.setAttribute("form" , hide);
			session.setAttribute("report" ,show);
		}
	}
	public void setField(Object field){
		this.field = field;
	}	
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}
}