package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;

public class EmployeeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
     	ArrayList<EmployeeBean> listOfEmployees = new ArrayList<EmployeeBean>();
		
		listOfEmployees = new EmployeeDao().getAllEmployees();
		request.setAttribute("listOfEmployees", listOfEmployees);
		request.getRequestDispatcher("EmployeeList.jsp").forward(request, response);

	}

}
