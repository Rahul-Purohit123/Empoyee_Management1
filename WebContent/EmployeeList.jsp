<%@page import="com.bean.SkillBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	*{box-sizing: border-box;}
	body{background-color: aqua;background-repeat: no-repeat;}
</style>
<body>
<% 
			EmployeeBean employeeBean =(EmployeeBean)request.getAttribute("employees");
			List<EmployeeBean> listOfEmployees =(ArrayList)request.getAttribute("listOfEmployees");	
			if(listOfEmployees!=null)
			{
%>
     <h2>EmployeeList Page</h2>
     <a href="EmployeeCreate.jsp">ADD Employee</a>
     <hr>                                                                                                                                                     
	<form action="">
		<table border="2px">
			<tr>
				<th>Employee Id</th>
				<th>Employee Name</th>
				<th>Employee Address</th>
				<th>Employee Gender</th>
				<th>Employee Salary</th>
				<th>Employee Skills</th>
				<th>Employee BirthDate</th>
				<th>ACTION</th>
				<th>ACTION</th>
			</tr>
			
			
				<%
					for(int i=0;i<listOfEmployees.size();i++)
					{
						employeeBean = listOfEmployees.get(i);
						
				%>
				<tr>
				<td><%=employeeBean.getEmployeeId() %></td>
				<td><%=employeeBean.getName() %></td>
				<td><%=employeeBean.getAddress() %></td>
				<td><%=employeeBean.getGender() %></td>
				<td><%=employeeBean.getSalary() %></td>
				<td>
					<%for(SkillBean bean:employeeBean.getSkills()){%>
					<%=bean.getName() %>,
					<% }%>
				</td>
				<td><%=employeeBean.getBirthDate() %></td>
				<td><a href="EmployeeEditController?employeeId=<%=employeeBean.getEmployeeId()%>">EDIT</a></td>
				<td><a href="EmployeeDeleteController?employeeId=<%=employeeBean.getEmployeeId()%>" class="btn btn-danger" onclick="return confirm('Are you sure, you want to delete it?')">Delete</a></td>
				<%
					}
				}else{
					out.print("pls check..");
				}
			
				%>	
		</tr>		
		</table>
</form>
</body>
</html>