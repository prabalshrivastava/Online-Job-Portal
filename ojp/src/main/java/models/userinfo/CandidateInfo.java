package models.userinfo;

import models.*;
import java.util.ArrayList;


public class CandidateInfo{
	//MemberVariables

	//ContactType contactType;
	//Country country;
	//Course course;
	//Department department;
	//Designation designation;
	//IndustryType industryType;
	//Language language;
	//LanguageCapability languageCapability;
	//Organization organization;
	//OrganizationType organizationType;
	//PinCode pinCode;
	//private Skill skill;
	//private State state;


	private ArrayList<Applicant> applicants;
	private ArrayList<Branch> branches;
	private ArrayList<CandidateLanguage> candidateLanguages;
	private ArrayList<CandidateSkill> candidateSkills;
	private Company company;
	private ArrayList<CompanyFollower> companyFollowers;
	private ArrayList<Contact> contacts;
	private ArrayList<Experience> experiences;
	private ArrayList<Job> jobs;
	private ArrayList<JobQualification> jobQualifications;
	private JobScoreCriteria jobScoreCriteria;
	private JobShift jobShift;
	private ArrayList<JobSkill> jobSkills;
	private ArrayList<Project> projects;
	private Qualification qualification;
	private Status status;
	private User user;
	private ArrayList<UserAddress> userAddresses;
	private UserType userType;


	public void setApplicants(ArrayList<Applicant> applicants){
		this.applicants = applicants;
	}
	public ArrayList<Applicant> getApplicants(){
		return applicants;
	}


	public void setBranches(ArrayList<Branch> branches){
		this.branches = branches;
	}
	public ArrayList<Branch> getBranches(){
		return branches;
	}


	public void setCandidateLanguages(ArrayList<CandidateLanguage> candidateLanguages){
		this.candidateLanguages = candidateLanguages;
	}
	public ArrayList<CandidateLanguage> getCandidateLanguages(){
		return candidateLanguages;
	}


	public void setCandidateSkills(ArrayList<CandidateSkill> candidateSkills){
		this.candidateSkills = candidateSkills;
		
	}
	public ArrayList<CandidateSkill> getCandidateSkill(){
		return candidateSkills;
	}


	public void setCompany(Company company){
		this.company = company;
	}
	public Company getCompany(){
		return company;
	}


	public void setCompanyFollowers(ArrayList<CompanyFollower> companyFollowers){
		this.companyFollowers = companyFollowers;
	}
	public ArrayList<CompanyFollower> getCompanyFollowers(){
		return companyFollowers;
	}


	public void setContacts(ArrayList<Contact> contacts){
		this.contacts = contacts;
	}
	public ArrayList<Contact> getContacts(){
		return contacts;
	}


	public void setExperiences(ArrayList<Experience> experiences){
		this.experiences = experiences;
	}
	public ArrayList<Experience> getExperiences(){
		return experiences;
	}

	public void setJobs(ArrayList<Job> jobs){
		this.jobs = jobs;
	}
	public ArrayList<Job> getJobs(){
		return jobs;
	}

	public void setJobQualifications(ArrayList<JobQualification> jobQualifications){
		this.jobQualifications = jobQualifications;
	}
	public ArrayList<JobQualification> getJobQualications(){
		return jobQualifications;
	}


	public void setJobScoreCriteria(JobScoreCriteria jobScoreCriteria){
		this.jobScoreCriteria = jobScoreCriteria;
	}
	public JobScoreCriteria getJobScoreCriteria(){
		return jobScoreCriteria;
	}


	public void setJobShift(JobShift jobShift){
		this.jobShift = jobShift;
	}
	public JobShift getJobShift(){
		return jobShift;
	}


	public void setJobSkills(ArrayList<JobSkill> jobSkills){
		this.jobSkills = jobSkills;
	}
	public ArrayList<JobSkill> getJobSkills(){
		return jobSkills;
	}


	public void setProjects(ArrayList<Project> projects){
		this.projects = projects;
	}
	public ArrayList<Project> getProjects(){
		return projects;
	}

	public void setQualification(Qualification qualification){
		this.qualification = qualification;
	}
	public Qualification getQualification(){
		return qualification;
	}

	public void setStatus(Status status){
		this.status = status;
	}
	public Status getStatus(){
		return status;
	}
	

	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return user;
	}

	public void setUserAddresses(ArrayList<UserAddress> userAddresses){
		this.userAddresses = userAddresses;
	}
	public ArrayList<UserAddress> getUserAddress(){
		return userAddresses;
	}

	public void setUserType(UserType userType){
		this.userType = userType;
	}
	public UserType getUserType(){
		return userType;
	}
}