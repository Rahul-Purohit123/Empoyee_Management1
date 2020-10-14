package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;




public class EmployeeCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String name =request.getParameter("name");
	    String address =request.getParameter("address");
		String gender =request.getParameter("gender");
		String skills[] = request.getParameterValues("skill");
		String birthdateString = request.getParameter("birthdate");
		String salary = request.getParameter("salary");
		long sal = Long.parseLong(salary);
	
		
		EmployeeBean employeeBean = new EmployeeBean();
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
			
		
	
		EmployeeDao dao = new EmployeeDao();
		
		try {
			int employeeId = dao.addEmployee(employeeBean);
			for(String s:skills) {
				dao.addEmployeeSkill(employeeId, Integer.parseInt(s));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("EmployeeListController").forward(request, response);
		
	}

}
