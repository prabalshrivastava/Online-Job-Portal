package utilities;
import javax.servlet.http.HttpServlet;

public class Db{
	public static String dbpass;

	public static Boolean isNull(Object o){
		if(o == null){
			return true;
		}else{
			return false;
		}
	}



	public static String show = "style = 'display:block'",hide = "style = 'display:none'",form ="",report="";
	
	public static void assignDisplay(Object property){
		String ad = "assignDisplay()--->";
		if(property == null || property.toString().equals("")){	
			System.out.println(ad + "form : " + (form = show));
			System.out.println(ad + "report : " + (report = hide));
		}else{
			System.out.println(ad + "form : " + (form = hide));
			System.out.println(ad + "report : " + (report = show));
		}
	}
}
