<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Search Page</title>
		<%@include file="page.jsp" %>
		<script type="text/javascript" src="js/search.js"></script>
		<link rel="stylesheet" type="text/css" href="css/search.css" />
	</head>
	<body>
		<form action="search.do">
			<table>
				<tr>
<%--				<a:formreport field="${"	--%>
					<td class="td_label">City : </td>
					<td class="td_report" id="_city"></td>
					<td class="td_form">
						<select class="form_data" id="city" name="city">
							${cities}
						</select>
					</td>


					<td class="td_label">Industry Type : </td>
					<td class="td_report" id="_industrytype"></td>
					<td class="td_form">
						<select class="form_data" id="industrytype" name="industrytype">
							${industrytypes}
						</select>
					</td>

				</tr>

				<tr>
					<td class="td_label">Work Experience : </td>
					<td class="td_report" id="_workexperience"></td>
					<td class="td_form">
						<select class="form_data" id="workexperience" name="workexperience">
						</select>
					</td>


					<td class="td_label">Skills : </td>
					<td class="td_report" id="_skill"></td>
					<td class="td_form">
						<select class="form_data" id="skill" name="skill">
							${skills}
						</select>
					</td>
				</tr>
<!--
				<tr>
					<td class="td_label">Package : </td>
					<td class="td_report" id="_ctc"></td>
					<td class="td_form">
						<input type="number" class="form_data" id="ctc" name="ctc" />
					</td>
				</tr>
-->
				<tr>
					<td class="td_label">Company : </td>
					<td class="td_report" id="_company"></td>
					<td class="td_form">
						<select class="form_data" id="company" name="company">
							${companies}
						</select>
					</td>
				</tr>
				


				<tr>
					<td>
						<input type="submit" value="search" id="update" name="update" class="form_data" />
					</td>
					<td class="td_skip">
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</table>
		</form>
				
		

		<div class="jobcontainer">
			<input type="hidden" value="${user.candidateId}" id="candidateid" /> 
			<c:forEach var="cj" items="${companyjobs}" varStatus="counter">
				<c:set var="job" value="${cj.job}" />
				<c:set var="branch" value="${job.branch}" />
				<c:set var="company" value="${branch.company}" />

				<c:set var="jobskills" value="${cj.jobSkills}" />
				<c:set var="jobqualifications" value="${cj.jobQualifications}" />
				

				<form id="form_.${job.jobId}">
					<div class="jobwrapper" id="jobwrapper_.${job.jobId}">
						<span class="cross" id="cross_.${job.jobId}">
							X
						</span>
						<br />
						<br />



						<div class="jobcontent" id="jobcontent_.${job.jobId}">

							<div class="designation_experience" id="designation_experience_.${job.jobId}" onclick="location.href='search.do?designation=${job.designation.designationId}&experiencemin=${job.experienceMin}&experiencemax=${job.experienceMax}'">
								<a href="search.do?designation=${job.designation.designationId}" class="designation" id="designation_.{job.jobId}">${job.designation.designation}</a>
								<a href="search.do?experiencemin=${job.experienceMin}&experiencemax=${job.experienceMax}" class="experience" id="experience_.${job.jobId}">(<c:if test="${empty job.experienceMin}">0</c:if>${job.experienceMin}-${job.experienceMax})</a>
							</div>

							<div class="companylogo" id="companylogo_.${job.jobId}">
								<input type="hidden" value="${company.companyId}" id="companyid_.${job.jobId}" />
								<a href="search.do?company=${company.companyId}">
									<figure>
										<img src="downloadcompanyfile.do?resource=logopath&company=${company.companyId}">
										<figcaption>${company.userName}</figcaption>
									</figure>
								</a>
							</div>

							<br /><br /><br /><br />

							<a class="salary" id="salary_.${job.jobId}" href="search.do?ctcmin=${job.ctcMin}&ctcmax=${ctcMax}">
								Salary:${job.ctcMin}-${job.ctcMax}
							</a>

							<br /><br /><br />

							<a class="city" id="city_.${job.jobId}" href="search.do?pincode=${branch.pinCode.pinCodeId}">${branch.pinCode.city.city}(${branch.pinCode.city.state.country.country})</a>

							<br /><br />

							<div id="jobskills_.${job.jobId}">
								<c:forEach var="js" items="${jobskills}">
									<a class="jobskills" id="skill_.${job.jobId}_.${js.skill.skillId}" href="search.do?skill=${js.skill.skillId}">${js.skill.skill}</a>&nbsp&nbsp
								</c:forEach>
							</div>

							<br /><br />

							<a class="button viewdetails" id="viewdetails_.${job.jobId}" href="showjobdetails.do?jobid=${job.jobId}">
								view Datails
							</a>

							&nbsp&nbsp&nbsp

							<a class="button apply" id="apply_.${job.jobId}" href="#">
								Apply
							</a>
						</div>
					</div>
			</c:forEach>
		</div>
	</body>
</html>


<!--
<%--
			${jobqualifications}
			${jobskills}				
			${companyjobs[0]}<br /><br /><br />
			<br/><br/><br/><br/>
			${job}
			<br/><br/><br/><br/>
			${branch}
			<br/><br/><br/><br/>
			${company}
			${counter.count}
--%>
-->
<!--
<%--

			<div class="div_table">
				<c:if test="${counter.count == 1}">				
					<div class="div_tr">
						<div class="div_td">Company</div>
						<div class="div_td">Job Title</div>
						<div class="div_td">Department</div>
						<div class="div_td">Designation</div>
						<div class="div_td">Branch</div>
						<div class="div_td">No Of vancancies</div>
						<div class="div_td">Launch Date</div>
						<div class="div_td">Last Date To Apply</div>
						<div class="div_td">Job Description</div>
						<div class="div_td">Minimum Experience</div>
						<div class="div_td">Maximum Experience</div>
						<div class="div_td">Minimum Package</div>
						<div class="div_td">Maximum Package</div>
						<div class="div_td">Branch Specific Details</div>
						<div class="div_td">Skills Required For the Job</div>
						<div class="div_td">Eligibility</div>
						<div class="div_td">Job Shift</div>
						<div class="div_td">Score Criteria for Job</div>
					</div>
				</c:if>

				<div class="div_tr">
					<div class="div_td">
						<div class="div_table">
							<div class="div_tr">
								<div class="div_td">Company Name</div>
								<div class="div_td">Email</div>
								<div class="div_td">Logo</div>
								<div class="div_td">Website</div>
								<div class="div_td">Industry</div>
								<div class="div_td">Mission</div>
								<div class="div_td">Vision</div>
								<div class="div_td">About Us</div>
								<div class="div_td">History</div>
								<div class="div_td">Awards</div>
							</div>
							<div class="div_tr">
								<div class="div_td">${company.userName}</div>
								<div class="div_td">${company.email}</div>
								<div class="div_td">${company.logoPath}</div>
								<div class="div_td">${company.website}</div>
								<div class="div_td">${company.industryType.industryType}</div>
								<div class="div_td">${company.mission}</div>
								<div class="div_td">${company.vision}</div>
								<div class="div_td">${company.aboutUs}</div>
								<div class="div_td">${company.history}</div>
								<div class="div_td">${company.awards}</div>
							</div>
						</div>
					</div>
					<div class="div_td">${job.jobTitle}</div>
					<div class="div_td">${job.department.department}</div>
					<div class="div_td">${job.designation.designation}</div>
					<div class="div_td">${job.branch}</div>
					<div class="div_td">${job.noOfVacancy}</div>
					<div class="div_td">${job.launchDate}</div>
					<div class="div_td">${job.applyLastDate}</div>
					<div class="div_td">${job.jobDescription}</div>
					<div class="div_td">${job.experienceMin}</div>
					<div class="div_td">${job.experienceMax}</div>
					<div class="div_td">${job.ctcMin}</div>
					<div class="div_td">${job.ctcMax}</div>
					<div class="div_td">
						<div class="div_table">
							<div class="div_tr">
								<div class="div_td">Branch Name</div>
								<div class="div_td">Address</div>
								<div class="div_td">Country</div>
								<div class="div_td">State</div>
								<div class="div_td">City</div>
								<div class="div_td">Pin Code</div>
								<div class="div_td">Contact Person</div>
								<div class="div_td">Contact Number</div>
								<div class="div_td">Email</div>
							</div>
							<div class="div_tr">
								<div class="div_td">${branch.branchName}</div>
								<div class="div_td">${branch.address}</div>
								<div class="div_td">${branch.pinCode.city.state.country.country}</div>
								<div class="div_td">${branch.pinCode.city.state.state}</div>
								<div class="div_td">${branch.pinCode.city.city}</div>
								<div class="div_td">${branch.pinCode.pinCode}</div>
								<div class="div_td">${branch.contactNumber}</div>
								<div class="div_td">${branch.contactPerson}</div>
								<div class="div_td">${branch.email}</div>
							</div>
						</div>
					</div>
					<div class="div_td">
						<c:forEach var="js" items="${jobskills}">
							${js.skill.skill}&nbsp
						</c:forEach>
					</div>
					<div class="div_td">
						<c:forEach var="jq" items="${jobqualifications}">
							${jq.course.course} &nbsp
						</c:forEach>
					</div>
					<div class="div_td">
						<div class="div_table">
							<div class="div_tr">
								<div class="div_td">Start Hour</div>
								<div class="div_td">End Hour</div>
							</div>
							<div class="div_tr">
								<div class="div_td">${cj.jobShift.startHour}</div>
								<div class="div_td">${cj.jobShift.endHour}</div>
							</div>
						</div>
					</div>
					<div class="div_td">
						<div class="div_table">
							<div class="div_tr">
								<div class="div_td">Senior Secondary Score</div>
								<div class="div_td">Higher Secondary/Diploma</div>
								<div class="div_td">Graduation</div>
							</div>
							<div class="div_tr">
								<div class="div_td">${cj.jobScoreCriteria.sscScore}</div>
								<div class="div_td">${cj.jobScoreCriteria.hscDiplomaScore}</div>
								<div class="div_td">${cj.jobScoreCriteria.graduation}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br /><br /><br />
--%>
-->