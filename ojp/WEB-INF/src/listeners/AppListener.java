package listeners;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

import utilities.Db;
import java.util.ArrayList;

//import java.util.ClassNotFoundException;
import models.City;
import models.Country;
import models.State;
import models.PinCode;
import models.IndustryType;
import models.Department;
import models.Designation;
import models.Language;
import models.OrganizationType;
import models.Organization;
import models.Course;
import models.LanguageCapability;
import models.Skill;
import models.ContactType;
import models.CourseType;
import models.Company;

public class AppListener implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent ev){
		try{
			ArrayList<City> cities;
			ArrayList<Country> countries;
			ArrayList<State> states;
			ArrayList<PinCode> pinCodes;
			ArrayList<IndustryType> industryTypes;
			ArrayList<Department> departments;
			ArrayList<Designation> designations;
			ArrayList<Language> languages;
			ArrayList<OrganizationType> organizationTypes;
			ArrayList<Organization> organizations;
			ArrayList<Course> courses;
			ArrayList<Skill> skills;
			ArrayList<ContactType> contactTypes;
			ArrayList<CourseType> courseTypes;
			ArrayList<Company> companies;

			String al ="inside AppListeners-->";
			ServletContext context = ev.getServletContext();


			System.out.println("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n\n");

			//get database password from web.xml initparam of application
			Db.dbpass = context.getInitParameter("dbpass");
			System.out.println(al + "Db.dbpass : " + Db.dbpass);
			
			//get countries from database
			countries = Country.collectAllCountries();
			context.setAttribute("countries",countries);
			System.out.println(al + "countries : " + countries);
			
			//get states from database
			states = State.collectAllStates();
			context.setAttribute("states",states);
			System.out.println(al + "states : " + states);

			//get cities from database
			cities = City.collectAllCities();
			context.setAttribute("cities",cities);
			System.out.println(al + "cities : " + cities);

			//get pinCodes from database
			pinCodes = PinCode.collectAllPinCodes();
			context.setAttribute("pincodes",pinCodes);
			System.out.println(al + "pincodes : " + pinCodes);
			
			//get IndustryType from database
			industryTypes = IndustryType.collectAllIndustryType();
			context.setAttribute("industrytypes",industryTypes);
			System.out.println(al + "industrytypes : " + industryTypes);
			
			//get Department from database
			departments = Department.collectAllDepartment();
			context.setAttribute("departments",departments);
			System.out.println(al + "departments : " + departments);

			//get Designation from database
			designations = Designation.collectAllDesignation();
			context.setAttribute("designations",designations);
			System.out.println(al + "designations : " + designations);

			//get Languages from database
			languages = Language.collectAllLanguage();
			context.setAttribute("languages",languages);
			System.out.println(al + "languages : " + languages);
			
			//get OrganizationTypes from database
			organizationTypes  = OrganizationType.collectAllOrganizationTypes();
			context.setAttribute("organizationtypes",organizationTypes);
			System.out.println(al + "organizationTypes : " + organizationTypes);
			

			//get Organizations from database
			organizations = Organization.collectAllOrganizations();
			context.setAttribute("organizations",organizations);
			System.out.println(al + "organizations : " + organizations);

			//get Courses from database
			courses = Course.collectAllCourses();
			context.setAttribute("courses",courses);
			System.out.println(al + "courses : " + courses);

			//get Skills from database
			skills = Skill.collectAllSkills();
			context.setAttribute("skills",skills);
			System.out.println(al + "skills" + skills);
			
			//get all contactTypes from database
			contactTypes = ContactType.collectAllContactTypes();
			context.setAttribute("contacttypes",contactTypes);
			System.out.println(al + "contactTypes : " + contactTypes);

			//get all CourseTypes from database
			courseTypes = CourseType.collectAllCourseType();
			context.setAttribute("coursetypes" , courseTypes);
			System.out.println(al + "courseTypes : " + courseTypes);

			//get All Companies from DataBase
			companies = Company.collectCompanies();
			context.setAttribute("companies",companies);
			System.out.println(al + "companies : " + companies);

			System.out.println("\n\n\n\n");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\tSUCCESSFUL START");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n\n");

		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	public void contextDestroyed(ServletContextEvent ev){	
		System.out.println("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n\n");
	}
}