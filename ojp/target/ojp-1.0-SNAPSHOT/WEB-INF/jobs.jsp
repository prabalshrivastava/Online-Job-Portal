<html>
	<head>
		<title>jobs</title>
		<%@include file="page.jsp" %>
	</head>
	<body>
		${jobdetails}
		<div class="div_table">
			<div class="div_tr">
				<div class="div_td">JOB DETAILS	</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Job Title :</div>
				<div class="div_td">${jobdetails.jobTitle}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">department :</div>
				<div class="div_td">${jobdetails.department.department}</div>
			</div>


			<div class="div_tr">
				<div class="div_td">designation :</div>
				<div class="div_td">${jobdetails.designation.designation}</div>
			</div>
			
			<div class="div_tr">
				<div class="div_td">noOfVacancy :</div>
				<div class="div_td">${jobdetails.noOfVacancy}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">launchDate : </div>
				<div class="div_td">${jobdetails.launchDate}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">applyLastDate : </div>
				<div class="div_td">${jobdetails.applyLastDate}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">job description : </div>
				<div class="div_td">${jobdetails.jobDescription}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Experience Required : </div>
				<div class="div_td">${jobdetails.experienceMin} to ${jobdetails.experienceMax} years</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Salary Between :</div>
				<div class="div_td">${jobdetails.ctcMin} to ${jobdetails.ctcMax}</div>
			</div>
		</div>


		<div class="div_table" style="float:right">

			<div class="div_tr">
				<div class="div_td">COMPANY DETAILS</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Company Name</div>
				<div class="div_td">${jobdetails.branch.company.userName}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Email</div>
				<div class="div_td">${jobdetails.branch.company.email}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Logo</div>
				<div class="div_td">
					<img style="width:20%;height:20%" src="downloadcompanyfile.do?resource=logopath&company=${jobdetails.branch.company.companyId}">
				</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Website</div>
				<div class="div_td">${jobdetails.branch.company.website}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Industry</div>
				<div class="div_td">${jobdetails.branch.company.industryType.industryType}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Mission</div>
				<div class="div_td">${jobdetails.branch.company.mission}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Vision</div>
				<div class="div_td">${jobdetails.branch.company.vision}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">About Us</div>
				<div class="div_td">${jobdetails.branch.company.aboutUs}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">History</div>
				<div class="div_td">${jobdetails.branch.company.history}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Awards</div>
				<div class="div_td">${jobdetails.branch.company.awards}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Branch Name :</div>
				<div class="div_td">
					${jobdetails.branch.branchName} 
					<br />
					email : ${jobdetails.branch.email}
					<br />
					${jobdetails.branch.contactPerson} : ${jobdetails.branch.contactNumber}
				</div>
			</div>

			<div class="div_tr">
				<div class="div_td">Address : </div>
				<div class="div_td">${jobdetails.branch.address}</div>
			</div>

			<div class="div_tr">
				<div class="div_td">City : </div>
				<div class="div_td">${jobdetails.branch.pinCode.city.city}(${jobdetails.branch.pinCode.city.state.state}) pincode : ${jobdetails.branch.pinCode.pinCode}</div>
			</div>

			<div class="div_tr">
				<div class="div_td"></div>
				<div class="div_td"></div>
			</div>
		</div>

	</body>
</html>