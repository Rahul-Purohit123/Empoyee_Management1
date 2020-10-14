<%@page import="com.dao.EmployeeDao"%>
<%@page import="com.dao.SkillDao"%>
<%@page import="com.bean.SkillBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
* {
	box-sizing: border-box;
}
body {
	background-color: aqua;
	background-repeat: no-repeat;
}
</style>
<script>  
	function validateform(){  
	var name=document.myform.name.value;  
	var address=document.myform.address.value;
	var gender=document.myform.gender.value; 
	var salary=document.myform.salary.value;
	var skill=document.myform.skill.value;
	var birthdate=document.myform.birthdate.value;

	if (name==null || name==""){  
  		alert("Name can't be blank");  
  	return false;
	}
	if(address==null || address==""){  
  		alert("address can't be blank");  
  	return false;  
	}
	if(gender==null || gender==""){
		alert("gender can't be blank");
	return false;
	}
	if(salary==null || salary==""){
		alert("salary can't be blank");
	return false;
	}
	if(skill==null){
		alert("skill can't be blank");
	return false;
	}
	if(birthdate==null){
		alert("birthdate can't be blank");
	return false;
	}
}
</script>  
<%
	ArrayList<SkillBean> skills = new SkillDao().getAllSkills();
	%>
	<body>
	<h2>Employee Create Page</h2>
	<hr>
	<form action="EmployeeCreateController" method="post" name="myform" onsubmit="return validateform()">
		<table>
			<tr>
				<td>Employee Name:-</td>
				<td><input type="text" name="name">
				</td>
			</tr>

			<tr>
				<td>Employee Address:-</td>
				<td><textarea rows="3" cols="20" name="address"></textarea>
				</td>
			</tr>

			<tr>
				<td>Employee Gender:-</td>
				<td><input type="radio" name="gender" value="1">Male
					&nbsp; <input type="radio" name="gender" value="0">Female
					</td>
			</tr>

			<tr>
				<td>Employee Salary:-</td>
				<td><input type="text" name="salary">
				</td>
			</tr>

			<tr>
				<td>Select Skill:-</td>
			   <td>
			     <%for(SkillBean bean:skills){ %>
		   
			  <%=bean.getName() %> <input type="checkbox" name="skill" value="<%=bean.getSkillId()%>">
			   <%} %>
			
			   </td>
			</tr> 
			  
			<tr>
				<td>Employee BirthDate:-</td>
				<td><input type="date" name="birthdate">
				</td>
			</tr>

			<tr>

				<td><input type="SUBMIT" value="Submit"></td>
			</tr>
		
		</table>
	</form>

</body>
</html>