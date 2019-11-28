<%@ taglib prefix="a" uri="getDisplay" %>
<html>
	<head>
		<title>others</title>
		<link rel="stylesheet" type="text/css" href="css/others.css" />
		<script type="text/javascript" src="js/others.js"></script>
		<%@ include file="page.jsp" %>
	</head>
	<body>
		<form action="others.do">
			
			<table>
				<tbody id="languagesknown" >
					<tr style="display:block">
						<%--<a:formreport fieldName="language" field="${candidatelanguage[0].language.language}" />--%>
						<td class="td_label lang" id="lang_label">Languages Known : </td>
						<td class="td_report lang" ${report}	value="${candidatelanguage[0].language.languageId}" id="_language_0" >
							${candidatelanguage[0].language.language}
						</td>
						<td class=" td_form lang" ${form}>
							<select id="language_0" name="language" class="form_data lang">
								${languages}
							</select>
						</td>
						
						<td class="td_report lang" id="_candidatelanguageid_0" name="candidatelanguageid" style="display:none">
							${candidatelanguage[0].candidateLanguageId}
						</td>
						<td class="td_form lang" name="candidatelanguageid" style="display:none">
							<input type="hidden" class="lang form_data" id="candidatelanguageid_0" name="candidatelanguageid" />
						</td>
					</tr>
						
						
					<tr style="display:block">
						<%--
						<a:formreport fieldName="language" field="${candidatelanguage.languageCapability.languageCapability}" />
						--%>
						<td class="td_report lang rws"  id="_read_0">
							<%--${candidatelanguage.languageCapability.languageCapability}--%>
							Read
						</td>
						<td class=" td_form lang rws" ${form} >
							Read :<input type="checkbox" name="rws_0" id="read_0"  class="form_data lang rws" value="read">
						</td>
					</tr>
					<tr style="display:block">
						<%--<a:formreport field="${candidatelanguage.languageCapability.languageCapability}" />--%>
						<td class="td_report lang rws"  id="_write_0">
							<%--${candidatelanguage.languageCapability.languageCapability}--%>
							Write
						</td>
						<td class=" td_form lang rws" ${form}>
							Write :<input type="checkbox" name="rws_0" id="write_0" class="form_data lang rws" value="write">
						</td>
					</tr>

					<tr style="display:block">
						<td class="td_report lang rws" id="_speak_0">
							<%--${candidatelanguage.languageCapability.languageCapability}--%>
							Speak
						</td>
						<td class=" td_form lang rws" ${form}>
							Speak :<input type="checkbox" name="rws_0" id="speak_0"  class="form_data lang rws" value="speak">
						</td>
					</tr>
					<tr style="visibility:hidden"><td><br /></td></tr>
				</tbody>




				<tbody id="addrws">
				</tbody>

				<tr id="tr_addmorelang">
					<td id="td_addmorelang" colspan="2">ADD MORE +
						<input type="hidden" id="langcount" name="langcount" value="0" />
						<input type="hidden" id="langvalues" name="langvalues" value="						${candidatelanguage}" />
					</td>
				</tr>

				<tr style="visibility:hidden"><td></td></tr>


				<tr>
					<a:formreport fieldName="strengths" field="${user.strengths}" />
					<td class="td_label">Strengths : </td>
					<td class="td_report" ${report} value="${user.strengths}" id="_strengths">
						${user.strengths}
					</td>
					<td class="td_form" ${form}>
						<input type="text" name="strengths" id="strengths" class="form_data" />
					</td>
				</tr>


				<tr>
					<a:formreport fieldName="weaknesses" field="${user.weaknesses}" />
					<td class="td_label">Weaknesses : </td>
					<td class="td_report" ${report} value="${user.weaknesses}" id="_weaknesses">
						${user.weaknesses}
					</td>
					<td class="td_form" ${form}>
						<input type="text" name="weaknesses" id="weaknesses" class="form_data" />
					</td>
				</tr>


				<tr>
					<a:formreport fieldName="hobbies" field="${user.hobbies}" />
					<td class="td_label">Hobbies : </td>
					<td class="td_report" ${report} value="${user.hobbies}" id="_hobbies">
						${user.hobbies}
					</td>
					<td class="td_form" ${form}>
						<input type="text" name="hobbies" id="hobbies" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport fieldName="intrest" field="${user.intrest}" />
					<td class="td_label">Intrests : </td>
					<td class="td_report" ${report} value="${user.intrest}" id="_intrest">
						${user.intrest}
					</td>
					<td class="td_form" ${form}>
						<input type="text" id="intrest" name="intrest" class="form_data" 9/21/2015/>
					</td>
				</tr>


				<tr>
					<a:formreport fieldName="curricularActivities" field="${user.curricularActivity}" />
					<td class="td_label">Curricular Activities : </td>
					<td class="td_report" ${report} value="${user.curricularActivity}" id="_curricularactivities">
						${user.curricularActivity}
					</td>
					<td class="td_form" ${form}>
						<input type="text" name="curricularactivities" id="curricularactivities" class="form_data" />
					</td>
				</tr>


				<tr>
					<td class="td_form">
						<input type="submit" value="update" name="update" id="update" class="form_data" />
					</td>
					<td class="td_skip">
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>