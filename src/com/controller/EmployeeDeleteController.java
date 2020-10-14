package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmployeeDao;

public class EmployeeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String eId = request.getParameter("employeeId");
	int employeeId=0;
	EmployeeDao dao =new EmployeeDao();
	
	if(eId!=null)
	{
		employeeId=Integer.parseInt(eId);
	}
	
		dao.deleteSkillToEmp(employeeId);
	
	 dao.deleteEmployee(employeeId);
	
	request.getRequestDispatcher("EmployeeListController").forward(request, response);
	
}
}