<!--
		<table id="parent_table">

			<c:forEach var="cj" items="${companyjobs}" varStatus="counter">
				<c:set var="job" value="${cj.job}" />
				<c:set var="branch" value="${job.branch}" />
				<c:set var="company" value="${branch.company}" />

				<c:set var="jobskills" value="${cj.jobSkills}" />
				<c:set var="jobqualifications" value="${cj.jobQualifications}" />
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
				<table>
					<c:if test="${counter.count == 1}">				
						<tr>
							<td>Company</td>
							<td>Job Title</td>
							<td>Department</td>
							<td>Designation</td>
						<!--<td>Branch</td>-->
<!--
							<td>No Of vancancies</td>
							<td>Launch Date</td>
							<td>Last Date To Apply</td>
							<td>Job Description</td>
							<td>Minimum Experience</td>
							<td>Maximum Experience</td>
							<td>Minimum Package</td>
							<td>Maximum Package</td>
							<td>Branch Specific Details</td>
							<td>Skills Required For the Job</td>
							<td>Eligibility</td>
							<td>Job Shift</td>
							<td>Score Criteria for Job</td>
						</tr>
					</c:if>

					<tr>
						<td>
							<table>
								<tr>
									<td>Company Name</td>
									<td>Email</td>
									<td>Logo</td>
									<td>Website</td>
									<td>Industry</td>
									<td>Mission</td>
									<td>Vision</td>
									<td>About Us</td>
									<td>History</td>
									<td>Awards</td>
								</tr>
								<tr>
									<td>${company.userName}</td>
									<td>${company.email}</td>
									<td>${company.logoPath}</td>
									<td>${company.website}</td>
									<td>${company.industryType.industryType}</td>
									<td>${company.mission}</td>
									<td>${company.vision}</td>
									<td>${company.aboutUs}</td>
									<td>${company.history}</td>
									<td>${company.awards}</td>
								</tr>
							</table>
						</td>
						<td>${job.jobTitle}</td>
						<td>${job.department.department}</td>
						<td>${job.designation.designation}</td>
					<!--<td>${job.branch}</td>-->
<!--
						<td>${job.noOfVacancy}</td>
						<td>${job.launchDate}</td>
						<td>${job.applyLastDate}</td>
						<td>${job.jobDescription}</td>
						<td>${job.experienceMin}</td>
						<td>${job.experienceMax}</td>
						<td>${job.ctcMin}</td>
						<td>${job.ctcMax}</td>
						<td>
							<table>
								<tr>
									<td>Branch Name</td>
									<td>Address</td>
									<td>Country</td>
									<td>State</td>
									<td>City</td>
									<td>Pin Code</td>
									<td>Contact Person</td>
									<td>Contact Number</td>
									<td>Email</td>
								</tr>
								<tr>
									<td>${branch.branchName}</td>
									<td>${branch.address}</td>
									<td>${branch.pinCode.city.state.country.country}</td>
									<td>${branch.pinCode.city.state.state}</td>
									<td>${branch.pinCode.city.city}</td>
									<td>${branch.pinCode.pinCode}</td>
									<td>${branch.contactNumber}</td>
									<td>${branch.contactPerson}</td>
									<td>${branch.email}</td>
								</tr>
							</table>
						</td>
						<td>
							<c:forEach var="js" items="${jobskills}">
								${js.skill.skill}&nbsp
							</c:forEach>
						</td>
						<td>
							<c:forEach var="jq" items="${jobqualifications}">
								${jq.course.course} &nbsp
							</c:forEach>
						</td>
						<td>
							<table>
								<tr>
									<td>Start Hour</td>
									<td>End Hour</td>
								</tr>
								<tr>
									<td>${cj.jobShift.startHour}</td>
									<td>${cj.jobShift.endHour}</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>Senior Secondary Score</td>
									<td>Higher Secondary/Diploma</td>
									<td>Graduation</td>
								</tr>
								<tr>
									<td>${cj.jobScoreCriteria.sscScore}</td>
									<td>${cj.jobScoreCriteria.hscDiplomaScore}</td>
									<td>${cj.jobScoreCriteria.graduation}</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br /><br /><br />
			</c:forEach>
		</table>
-->