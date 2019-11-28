<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="a" uri="getDisplay" %>
<html>
	<head>
		<title>Post New Job</title>
		<%@ include file="page.jsp" %>
		<script type="text/javascript" src="js/postnewjob.js"></script>
		<script type="text/javascript" src="js/table_report.js"></script>
	</head>
	<body>
		<form action="postnewjob.do">
			<table style="float:left">
				<tr>
					<td class="td_label">JOB DETAILS : </td>
				</tr>
				<tr>
					<a:formreport field="${jobs[0].jobTitle}" fieldName="jobtitle" />
					<td class="td_label">Job Title : </td>
					<td class="td_report" ${report} id="_jobtitle">${jobs[0].jobTitle}</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="jobtitle" name="jobtitle" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].branch.branchName}" fieldName="branch" />
					<td class="td_label">Branch : </td>
					<td class="td_report" ${report} id="_branch">${jobs[0].branch.branchName}</td>
					<td class="td_form" ${form}>
						<select id="branch" name="branch" class="form_data">
							${branches}
						</select>
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].department.department}" fieldName="department" />
					<td class="td_label">Departments : </td>
					<td class="td_report" ${report} id="_department">${jobs[0].department.department}</td>
					<td class="td_form" ${form}>
						<select id="department" name="department" class="form_data">
							${departments}
						</select>
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].designation.designation}" fieldName="designation" />
					<td class="td_label">Designations : </td>
					<td class="td_report" ${report} id="_designation">${jobs[0].designation.designation}</td>
					<td class="td_form" ${form}>
						<select id="designation" name="designation" class="form_data">
							${designations}
						</select>
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].noOfVacancy}" fieldName="noofvacancies" />
					<td class="td_label">No Of Vacancies : </td>
					<td class="td_report" ${report} id="_noofvacancies">${jobs[0].noOfVacancy}</td>
					<td class="td_form" ${form}>
						<input type="number" id="noofvacancies" name="noofvacancies" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].launchDate}" fieldName="launchdate" />
					<td class="td_label">Launch Date : </td>
					<td class="td_report" ${report} id="_lanuchdate">${jobs[0].launchDate}</td>
					<td class="td_form" ${form}>
						<input type="date" id="launchdate" name="launchdate" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].applyLastDate}" fieldName="applylastdate" />
					<td class="td_label">Apply Last Date : </td>
					<td class="td_report" ${report} id="_applylastdate">${jobs[0].applyLastDate}</td>
					<td class="td_form" ${form}>
						<input type="date" id="applylastdate" name="applylastdate" class="form_Data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].jobDescription}" fieldName="jobdescription" />
					<td class="td_label">Job Description : </td>
					<td class="td_report" ${report} id="_jobdescription">${jobs[0].jobDescription}</td>
					<td class="td_form" ${form}>
						<input type="text" id="jobdescription" name="jobdescription" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].experienceMin}" fieldName="experiencemin" />
					<td class="td_label">Experience Min : </td>
					<td class="td_report" ${report} id="_experiencemin">${jobs[0].experienceMin}</td>
					<td class="td_form" ${form}>
						<input type="number" id="experiencemin" name="experiencemin" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].experienceMax}" fieldName="experinecemax" />
					<td class="td_label">Experience Max : </td>
					<td class="td_report" ${report} id="_experiencemax">${jobs[0].experienceMax}</td>
					<td class="td_form" ${form}>
						<input type="number" id="experiencemax" name="experiencemax" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].ctcMin}" fieldName="ctcmin" />
					<td class="td_label">CTC Min : </td>
					<td class="td_report" ${report} id="_ctcmin">${jobs[0].ctcMin}</td>
					<td class="td_form" ${form}>
						<input type="number" id="ctcmin" name="ctcmin" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobs[0].ctcMax}" fieldName="ctcmax" />
					<td class="td_label">CTC Max : </td>
					<td class="td_report" ${report} id="_ctcmax">${jobs[0].ctcMax}</td>
					<td class="td_form" ${form}>
						<input type="number" id="ctcmax" name="ctcmax" class="form_data" />
					</td>
				</tr>

				<tr> 
					<td style="visibility:hidden"></td>
				</tr>






				<tr>
					<td class="td_label">Job Score Criteria : </td>
				</tr>
				<tr>
					<a:formreport field="${jobscorecriterias[0].sscScore}" fieldName="sscscore" />
					<td class="td_label">Ssc Score : </td>
					<td class="td_report" ${report} id="_sscscore">${jobscorecriterias[0].sscScore}</td>
					<td class="td_form" ${form}>
						<input type="number" step="0.01" class="form_data" id="sscscore" name="sscscore" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobscorecriterias[0].hscDiplomaScore}" fieldName="hscdiplomascore" />
					<td class="td_label">Hsc/Diploma Score : </td>
					<td class="td_report" ${report} id="_hscdiplomascore">${jobscorecriterias[0].hscDiplomaScore}</td>
					<td class="td_form" ${form}>
						<input type="number" step="0.01" class="form_data" id="hscdiplomascore" name="hscdiplomascore" />
					</td>
				</tr>


					<a:formreport field="${jobscorecriterias[0].graduation}" fieldName="graduation" />
					<td class="td_label">Graduation : </td>
					<td class="td_report" ${report} id="_graduation">${jobscorecriterias[0].graduation}</td>
					<td class="td_form" ${form}>
						<input type="number" step="0.01" class="form_data" id="graduation" name="graduation" />
					</td>
				</tr>

				<tr>
					<td style="visibility:hidden"></td>
				</tr>


				<tr>
					<a:formreport field="${jobskills[0].skill.skill}" fieldName="skills" />
					<td class="td_label">Skills Required : </td>
					<td class="td_report" ${report} id="_skills">${jobskills[0].skill.skill}</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="skills" name="skills" />
					</td>
				</tr>

				<tr>
					<td style="visibility:hidden"></td>
				</tr>


				<tr>
					<td class="td_label">Job Shifts : </td>
				</tr>
				<tr>
					<a:formreport field="${jobshifts[0].startHour}" fieldName="starthour" />
					<td class="td_label">Start Hour : </td>
					<td class="td_report" ${report} id="_starthour">${jobshifts[0].startHour}</td>
					<td class="td_form" ${form}>
						<input type="time" class="form_data" id="starthour" name="starthour" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${jobshifts[0].endHour}" fieldName="endhour" />
					<td class="td_label">End Hour : </td>
					<td class="td_report" ${report} id="_endhour">${jobshifts[0].endHour}</td>
					<td class="td_form" ${form}>
						<input type="time" class="form_data" id="endhour" name="endhour" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${jobqualifications[0].course.course}" fieldName="course" />
					<td class="td_label">Job Courses</td>
					<td class="td_report" ${report} id="_course">${jobqualifications[0].course.course}</td>
					<td class="td_form" ${form}>
						<select id="course" name="course" class="form_data">${courses}</select>
					</td>
				</tr>


				<tr>
					<td class="td_form">
						<input type="submit" class="form_data button" id="update" name="update" value="update" />
					</td>
					<td class="td_skip">
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</table>
		</form>

		<table style="float:center" id="table_report">
			<tr id="report_tr">
				<td>JobId</td>
				<td>Job Title</td>
				<td>Branch</td>
				<td>Department</td>
				<td>Designation</td>
				<td>No Of Vacancy</td>
				<td>Launch Date</td>
				<td>Apply Last Date</td>
				<td>Job Description</td>
				<td>Maximum Experience</td>
				<td>Minimum Experience</td>
				<td>Maximum CTC</td>
				<td>Minimum CTC</td>
			</tr>
			<c:forEach var="job" items="${jobs}" varStatus="abc">
				<c:set var="j" value="${job.jobId}" />
				${abc.count}
				<tr class="report_tr">
					<td id="jobid_.${j}">${j}</td>
					<td id="jobtitle_.${j}">${job.jobTitle}</td>
					<td id="branch_.${j}" value="${job.branch.branchId}">${job.branch.branchName}</td>
					<td id="department_.${j}" value="${job.department.departmentId}">${job.department.department}</td>
					<td id="designation_.${j}" value="${job.designation.designationId}">${job.designation.designation}</td>
					<td id="noofvacancies_.${j}">${job.noOfVacancy}</td>
					<td id="launchdate_.${j}">${job.launchDate}</td>
					<td id="applylastdate_${j}">${job.applyLastDate}</td>
					<td id="jobdescription_.${j}">${job.jobDescription}</td>
					<td id="experiencemin_.${j}">${job.experienceMin}</td>
					<td id="experiencemax_.${j}">${job.experienceMin}</td>
					<td id="ctcmax_.${j}">${job.ctcMax}</td>
					<td id="ctcmin_.${j}">${job.ctcMin}</td>
				</tr>	
			</c:forEach>
			<tr id="tr_report_message">
				<td colspan="1000" id="td_report_message">Click To Edit and View Data</td>
			</tr>
			<tr>
				<td colspan="1000" id="addmoredata" class="button">Add More Data</td>
			</tr>
		</table>
	
		<table id="table_report">
			<tr>
				<td>Job Score Criteria Id</td>
				<td>SSC Score</td>
				<td>HSC Score</td>
				<td>Graduation</td>
			</tr>
			<c:forEach var="jsc" items="${jobscorecriterias}">
				<c:set var="j" value="${jsc.jobScoreCriteriaId}" />
				<tr class="report_tr">
					<td id="jobscorecriteriaid_.${j}">${j}</td>
					<td id="sscscore_.${j}">${jsc.sscScore}</td>
					<td id="hscdiplomascore_.${j}">${jsc.hscDiplomaScore}</td>
					<td id="graduation_.${j}">${jsc.graduation}</td>
				</tr>
			</c:forEach>
			<tr id="tr_report_message">
				<td id="td_report_message" colspan="1000">Click to Edit and View Data</td>
			</tr>
			<tr>
				<td id="addmoredata" class="button" colspan="1000">Add More Data</td>
			</tr>
		</table>

		<table id="table_report">
			<tr>
				<td>Job Skill Id</td>
				<td>Skills Required</td>
			</tr>
			<c:forEach var="js" items="${jobskills}">
				<c:set var="j" value="${js.jobSkillId}" />
				<tr class="report_tr">
					<td jobskillid_.${j}>${j}</td>
					<td skills_.${j}>${js.skill.skill}</td>
				</tr>
			</c:forEach>
			<tr id="tr_report_message">
				<td colspan="1000" id="td_report_message">Click To Edit Data</td>
			</tr>
			<tr>
				<td colspan="1000" id="addmoredata" class="button">Add More Data</td>
			</tr>
		</table>


		<table id="table_report">
			<tr>
				<td>Job Shift ID</td>
				<td>Start Hour</td>
				<td>End Hour</td>
			</tr>
			<c:forEach var="js" items="${jobshifts}">
				<c:set var="j" value="${js.jobShiftId}" />
				<tr class="report_tr">
					<td id="jobshiftid_.${j}">${j}</td>
					<td id="starthour_.${j}">${js.startHour}</td>
					<td id="endhour_.${j}">${js.endHour}</td>
				</tr>
			</c:forEach>

			<tr id="tr_report_message">
				<td id="td_report_message" colspan="100">Click to Edit Data</td>
			</tr>
			<tr>
				<td id="addmoredata" colspan="100" class="button">Add More Data</td>
			</tr>
		</table>

		<table id="report_tr">
			<tr>
				<td>Job Qualfication ID</td>
				<td>Job Courses</td>
			</tr>
			<c:forEach var="jq" items="${jobqualifications}">
				<c:set var="j" value="${jq.jobQualificationId}" />
				<tr class="report_tr">
					<td id="jobqualificationid_.${j}">${j}</td>
					<td id="course_.${j}">${jq.course.course}</td>
				</tr>
			</c:forEach>
			<tr id="tr_report_message">
				<td id="td_report_message" colspan="100">Click To Edit Data</td>
			</tr>
			<tr>
				<td id="addmoredata" class="button" colspan="100">Add More Data</td>
			</tr>
		</table>
	</body>
</html>