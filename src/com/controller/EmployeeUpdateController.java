package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;

public class EmployeeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String name =request.getParameter("name");
			String address =request.getParameter("address");
			String gender =request.getParameter("gender");
			String skills[] = request.getParameterValues("skill");
			String birthdateString = request.getParameter("birthdate");
			String salary = request.getParameter("salary");
			long sal = Long.parseLong(salary);
		
			EmployeeBean employeeBean = new EmployeeBean();
			
			employeeBean.setEmployeeId(employeeId);
			employeeBean.setName(name);
			employeeBean.setAddress(address);
			employeeBean.setGender(gender.equals("1"));
			employeeBean.setSalary(sal);
	
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdateUtil;
	
			try {
				birthdateUtil =sd.parse(birthdateString);
				System.out.println(birthdateUtil);
				java.sql.Date  birthdateSql =new java.sql.Date(birthdateUtil.getTime()); 
				employeeBean.setBirthDate(birthdateSql);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			EmployeeDao employeeDao = new EmployeeDao();
			
			employeeDao.updateEmployee(employeeBean);
			 employeeDao.deleteSkillForEmployee(employeeId);
			 
			for(String s:skills)
			{
				employeeDao.addSkillForEmployee(employeeId, Integer.parseInt(s));	
			}
		
			response.sendRedirect("EmployeeListController");
		}	
}

