<%@page import="com.dao.EmployeeDao"%>
<%@page import="com.dao.SkillDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.SkillBean"%>
<%@page import="com.bean.EmployeeBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	body{background-color: aqua;background-repeat: no-repeat;}
</style>
<body>
<%
	EmployeeBean employeeBean = (EmployeeBean)request.getAttribute("employees");
	
	ArrayList<SkillBean> skills = new SkillDao().getAllSkills();


%>
	<h2>Employee Edit Page</h2>
	<hr>
	<form action="EmployeeUpdateController">
	
		<input type="hidden" name="employeeId" value="<%=employeeBean.getEmployeeId()%>">
	
	<table>
		<tr>
			<td>Name:-</td>
			<td><input type="text" name="name" value="<%=employeeBean.getName() %>"></td>
		</tr>
		
		<tr>
			<td>Address:-</td>
			<td><textarea rows="3" cols="20" name="address"><%=employeeBean.getAddress() %></textarea>
			</td>
		</tr>
		
		<tr>
				<td>Employee Gender:-</td>
				<td><input type="radio" name="gender" value="1" <%=employeeBean.getGender()?"checked":"" %>>Male
					&nbsp; <input type="radio" name="gender" value="0" <%=employeeBean.getGender()?"":"checked" %>>Female
					</td>
		</tr>
		
		<tr>
			<td>Salary:-</td>
			<td><input type="Number" name="salary" value="<%=employeeBean.getSalary()%>"></td>
		</tr>
		
		<tr>
				<td>Select Skill:-</td>
			   <td>
			   <%for(SkillBean bean:skills){ %>
			   
			  <%=bean.getName() %> <input type="checkbox" name="skill" value="<%=bean.getSkillId()%>" <%=employeeBean.getSkills().contains(bean)?"checked":"" %>>
			   <%} %>
			   </td>		
		 </tr>
		 
		<tr>
			<td>BirthDate:-</td>
			<td><input type="date" name="birthdate" value="<%=employeeBean.getBirthDate() %>"></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="update"></td>
			
		</tr>
		
	</table>
	</form>
</body>
</html>