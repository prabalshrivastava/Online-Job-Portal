<% 
	String male = null,female = null;
	if(candidate.getGender() == null){
		System.out.println(candidate.getGender());
		form = show;
		report = hidden;
	}else if(candidate.getGender()){
		//candidate is a male
		form = hidden;
		report = show;

		male = show;
		female = hidden;
	}else{
		//candidate is a female
		form = hidden;
		report = show;
		
		male = hidden;
		female = show;
	}
%>
<%= candidate.getGender()%>
<td class = "td_form" <%= form %>>
	Male	:
	<input type="radio" name="_gender"	id = "_male" value="male" class ="form_data"/>

	&nbsp;&nbsp;&nbsp;&nbsp;

	Female	:
	<input type="radio" name="_gender"	id = "_female" value="female" class="form_data"/>
</td>

<td class = "td_report" <%= report%>>
	<span id = "gender" name = "gender" class = "report_data" <%= male %>>Male</span>
	<span id = "gender" name = "gender" class = "report_data" <%= female%>> Female</span>
</td>



//original geender code in personal details page

<tr>
	<td>Gender	:</td>
	<%  
		if(candidate.getGender() == null){
	%>
		<td class = "td_form">
			Male	:
			<input type="radio" name="_gender"	id = "_male" value="male" class ="form_data"/>

			&nbsp;&nbsp;&nbsp;&nbsp;

			Female	:
			<input type="radio" name="_gender"	id = "_female" value="female" class="form_data"/>
		</td>
	<%
		}else if(candidate.getGender()){
	%>
			<td class = "td_report">
				<span id = "gender" name = "gender" class = "report_data">Male</span>
			</td>
	<%
		}else{
	%>
			<td class ="td_report">
				<span id = "gender" name = "gender" class = "report_data"> Female</span>
			</td>
	<%	
		}
	%>						
	<td class = "message" id = "__gender" name = "__gender"></td>
</tr>




//original content of maritalstatus
<tr>
	<td>MaritalStatus :</td>
	<%
		if(candidate.getMaritalStatus()==null){
	%>
		<td class = "td_form">
			Married :
			<input type="radio" id = "married" name="_maritalstatus" class = "form_data" value="married" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			Unmarried :
			<input type="radio" id = "unmarried" name="_maritalstatus" class = "form_data" value="Ummarried" />
		</td>
	<%	
		}else if(candidate.getMaritalStatus()){
	%>
			<td class = "td_report">
				<span id = "married" name = "maritalstatus" class = "report_data">Married</span>
			</td>
	<%
		}else{
	%>
			<td class = "td_report">
				<span id = "unmarried" name = "maritalstatus" class = "report_data">
					Unmarried
				</span>
			</td>
	<%	
		}
	%>
	<td class = "message" id = "__maritalstatus" name = "__maritalstatus"></td>
</tr>
































//original personaldetails page
<%@ page import = "models.Candidate,models.Country,models.City,java.util.ArrayList" %>
<html>
	<head>
		<title>Personal Details</title>
		<link	type="text/css"	rel="stylesheet" href="css/personaldetails.css" />
		<script type="text/javascript" src="js/personaldetails.js"></script>
	</head>
	<body>
		<form action = "personaldetails.do" method = "post" enctype = "multipart/form-data">
			<% 
				Candidate candidate = (Candidate)session.getAttribute("user"); 
			%>
			<table>
				<!-- here underscore convention is used for editable(data from user) fields -->
				<tr>
					<td>Name :</td>
					<td class="td_data"><span id = "username" class="data">${user.userName}</span></td>
					<td><input type = "text" id="_username" class = "editdata"/></td>
				</tr>

				<tr>
					<td>Email</td>
					<td class =  "td_data"><span id = "email" class="data">${user.email}</span></td>
					<td><input type = "text" id = "_email" class = "editdata" /></td>
				</tr>

				<tr>
					<td>Password</td>
					<td class = "td_data"><span id = "password" name = "password" class="data">change your password</span></td>
					<td>
						<input type = "password" id = "_password" name = "_password" class = "editdata" /><br/>
						<input type = "password" id = "_confirmpassword" name = "_confirmpassword" class = "editdata" />
					</td>
				</tr>

				<tr>
					<td>UserType</td>
					<td class = "td_data"><span id = "usertype" class="data">${user.userType.userType}</span></td>
				</td>

				<tr>
					<td>Date Of Birth :</td>
						<%
							if(candidate.getDateOfBirth()==null){
						%>
								<td><input type="text" id="_dob" name="_dob" /></td>
						<%
							}else{
						%>
								<td class = "td_data">${user.dateOfBirth}</td>
						<%	
							}
						%>
				</tr>

				<tr>
					<td>Gender	:</td>
					<% 
						if(candidate.getGender() == null){
					%>
						<td>
							Male	:<input type="radio"	name="_gender"	id = "_male" 	value="male" />

							&nbsp;&nbsp;&nbsp;&nbsp;
							Female	:<input type="radio"	name="_gender"	id = "_female"	value="female" />
						</td>
					<%
						}else if(candidate.getGender()){
					%>
							<td class = "td_data">Male</td>
					<%
						}else{
					%>
							<td class ="td_data">Female</td>
					<%	
						}
					%>
					</td>
				</tr>

				<tr>	
					<td>Father's Name :</td>
					<% 
						if(candidate.getFatherName() == null) {
					%>
							<td><input type="text"	id="_fname"	name="_fname" /></td>
					<%	
						}else{
					%>
							<td>${user.fatherName}</td>
					<%	
						}
					%>
				</td>

				<tr>
					<td>Mother's Name :</td>
					<%
						if(candidate.getMotherName() == null){
					%>
							<td><input type="text"	id="_mname"	name="_mname" /></td>
					<%	
						}else{
					%>
						<td>${user.motherName}</td>
					<%	
						}
					%>
				</tr>
				
				<tr>
					<td>profile Pic</td>
					<%
						if(candidate.getProfilePicPath()==null){
					%>
							<td>Upload Your Photo :</td>
							<td><input type="file"	id="_profilepicpath"	name="_profilepicpath" /></td>
					<%	
						}else{
					%>
							
							<td class = "td_data"><!--<img src="" />-->${user.profilePicPath}</td>
					<%	
						}
					%>
				</tr>
				
				<tr>
					<td>MaritalStatus :</td>
					<%
						if(candidate.getMaritalStatus()==null){
					%>
						<td>
							Married :<input type="radio" name="_marital" id = "married" value="married" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							Unmarried :<input type="radio" name=_"marital" id = "unmarried" value="Ummarried" />
						</td>
					<%	
						}else if(candidate.getMaritalStatus()){
					%>
						<td class = "td_data">Married</td>
					<%
						}else{
					%>
						<td class = "td_data">Unmarried</td>
					<%	
						}
					%>
				</tr>

				<tr>
					<td>Nationality</td>
					<% 
						ArrayList countries = (ArrayList)application.getAttribute("countries");
						if(candidate.getCountry()==null){
					%>
							<td>
								<select	id="_country"	name="_country"/>
								<%	
									int i = 0,size = countries.size();
									String countryName = null;

									while(i!=size){
										countryName = (String)countries.get(i++);
								%>	
									<option>
										<%= countryName %>
									</option>
								<%
									}
								%>
							</td>
					<%	
						}else if(candidate.getCountry().getCountry()==null){
					%>
							<td>
								<select	id="_country"	name="_country"/>
							</td>
					<%	
						}else{
					%>
							<td class = "td_data" >${user.country.country}</td>
					<%	
						}
					%>
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="submit" value="update" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

//data from candidates updatePersonalDetails()
/*
		System.out.println("inside candidate\t\tupdatePersonalDetails :\t\t"+this);
		Boolean flag = false,flag1=false;
		if(email == null && password == null && userName == null){
			System.out.println("no new data to update in users table");
		}else{
			if(userName == null){
				userName = getUserName();
			}else {
				System.out.println("new userName :\t\t"+userName);
			}
			if(email == null){
				email = getEmail();
			}else{
				System.out.println("new email :\t\t"+email);
			}
			if(password == null){
				password = getPassword();
			}else{
				System.out.println("new password :\t\t"+password);
			}
			flag1 = updateUser(userId,userName,email,password,getUserType());
			//flag = super(userId,userName,email,password,getUserType());
			//because constructor was not allowed to be called from inside the method of the same class
			//(in this case User) we are calling the constructor here
			//and that too is not allowed :( 
		}
		if(fatherName == null && motherName == null && profilePicPath == null && country == null &&  dateOfBirth == null && gender == null && maritalStatus ==null){
			System.out.println("no new data to be updated in candidates table");
		}else{
			try{
				String country_id,date_of_birth,_gender,marital_status;
				Integer countryId = null;
				Class.forName("com.mysql.jdbc.Driver");
				Connection	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
				int i = 3 ;
				country_id = (countryId == null ? " country_id = null , " : " country_id = ? , ");
				date_of_birth = (dateOfBirth == null ? " date_of_birth = null , " : "date_of_birth = ?, ");
				_gender =(gender == null ? " gender = null , " : " gender = ? , ");
				marital_status =(maritalStatus == null ? " marital_status = null " : "marital_status = ? ");

				String	query = "update candidates set father_name = ? , mother_name = ? , profile_pic_path = ? , " + country_id + date_of_birth + _gender + marital_status+ " where user_id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				System.out.println("Query :\t\t"+query);
				ps.setString(1,fatherName == null ? "null" : fatherName);					
				ps.setString(2,motherName == null ? "null" : motherName);
				ps.setString(3,profilePicPath == null ? "null" : profilePicPath);
				
				countryId = Country.getCountryObject(country,null).getCountryId();
				if(countryId != null) 
					ps.setInt(,countryId);

				if(dateOfBirth != null)
					ps.setDate(,dateOfBirth);
				
				if(gender != null)
					ps.setBoolean(,gender);

				if(maritalStatus != null)
					ps.setBoolean(,maritalStatus);

				System.out.println("preparedStatement :\t\t"+ps);
				ps.setInt(,getUserId());
				
				ps.executeUpdate();	
				flag = true;
				connection.close();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return flag && flag1;
		*/