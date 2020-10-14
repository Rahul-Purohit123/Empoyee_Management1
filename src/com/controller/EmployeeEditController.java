package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;

public class EmployeeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		
		EmployeeDao dao = new EmployeeDao();
		EmployeeBean employeeBean =dao.getDataById(employeeId);
		
		request.setAttribute("employees", employeeBean);
	
		request.getRequestDispatcher("EmployeeEdit.jsp").forward(request, response);
	
	}

}
