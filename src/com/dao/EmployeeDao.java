package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.EmployeeBean;
import com.bean.SkillBean;
import com.util.DBConnection;

public class EmployeeDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public int addEmployee(EmployeeBean employeeBean) throws SQLException {
		int empid = 0;
		int record = 0;
		try {
			conn = DBConnection.getDBConnection();
			String insert = "insert into employee(name,address,gender,salary,birthdate)values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, employeeBean.getName());
			pstmt.setString(2, employeeBean.getAddress());
			pstmt.setBoolean(3, employeeBean.getGender());
			pstmt.setLong(4, employeeBean.getSalary());
			pstmt.setDate(5, (Date) employeeBean.getBirthDate());

		    record = pstmt.executeUpdate();

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					empid = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
			System.out.println(record + "  record insert successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empid;
	}
	public void addEmployeeSkill(int employeeId, int skillId) {
		try {
			 conn = DBConnection.getDBConnection();
			 pstmt = conn.prepareStatement("insert into employeeskill(employeeId,skillId) values (?,?)");
	
			pstmt.setInt(1, employeeId);
			pstmt.setInt(2, skillId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		public ArrayList<EmployeeBean> getAllEmployees() {
			conn = DBConnection.getDBConnection();
		ArrayList<EmployeeBean> listOfEmployees = new ArrayList<EmployeeBean>();
		
			if(conn!=null) {
				
				String selectsql ="select * from employee";
				
				try {
					pstmt = conn.prepareStatement(selectsql);
					rs = pstmt.executeQuery();

				while (rs.next()) {
					EmployeeBean employeeBean = new EmployeeBean();
					
					employeeBean.setEmployeeId(rs.getInt("employeeId"));
					employeeBean.setName(rs.getString("name"));
					employeeBean.setAddress(rs.getString("address"));
					employeeBean.setGender(rs.getBoolean("gender"));
					employeeBean.setSalary(rs.getLong("salary"));
					employeeBean.setBirthDate(rs.getDate("birthdate"));
					//employeeBean.setSkills(addSkillToEmp(employeeBean.getEmployeeId()));
					employeeBean.setSkills(getSkillsOfEmp(employeeBean.getEmployeeId()));
					listOfEmployees.add(employeeBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		return (ArrayList<EmployeeBean>) listOfEmployees;
	}


	public ArrayList<SkillBean> getSkillsOfEmp(int employeeId) {
		ArrayList<SkillBean> skills = new ArrayList<SkillBean>();
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement pstmt = conn.prepareStatement("select s.skillId , s.name from skillMaster s ,employeeskill es where es.employeeId = ? and es.skillId= s.skillId");
			pstmt.setInt(1, employeeId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SkillBean bean = new SkillBean();

				bean.setSkillId(rs.getInt("skillId"));
				bean.setName(rs.getString("name"));
				
				skills.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return skills;
	}

	public boolean deleteEmployee(int employeeId)
	{
		conn = DBConnection.getDBConnection();
		boolean flag=false;
		if(conn!=null)
		{
				String deletesql ="delete from employee where employeeId=?";
				try {
					pstmt = conn.prepareStatement(deletesql);
					pstmt.setInt(1, employeeId);
					int res = pstmt.executeUpdate();
					if(res>0)
					{
						flag=true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return flag;
	}
	public void deleteSkillToEmp(int employeeId) {
		try(Connection con = DBConnection.getDBConnection();
				PreparedStatement pstmt = con.prepareStatement("delete from employeeskill where employeeId=?");
				){
			pstmt.setInt(1, employeeId);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
}
	public void updateEmployee(EmployeeBean employeeBean) {
		
		try {
			conn = DBConnection.getDBConnection();
			pstmt =conn.prepareStatement("update employee set name=?,address=?,gender=?,salary=?,birthDate=? where employeeId=?");
			
			pstmt.setString(1, employeeBean.getName());
			pstmt.setString(2, employeeBean.getAddress());
			pstmt.setBoolean(3, employeeBean.getGender());
			pstmt.setLong(4, employeeBean.getSalary());
			pstmt.setDate(5, (Date) employeeBean.getBirthDate());
			pstmt.setInt(6, employeeBean.getEmployeeId());
			
			pstmt.executeUpdate();
			
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
		


	public EmployeeBean getDataById(int employeeId)
	{
		conn = DBConnection.getDBConnection();
		EmployeeBean employeeBean = null;
		if(conn!=null)
		{
			String selectsql ="select * from employee where employeeId=?";
			try {
				pstmt=conn.prepareStatement(selectsql);
				pstmt.setInt(1, employeeId);
				rs =pstmt.executeQuery();
				
				while(rs.next())
				{
					employeeBean = new EmployeeBean();
					employeeBean.setEmployeeId(rs.getInt("employeeId"));
					employeeBean.setName(rs.getString("name"));
					employeeBean.setAddress(rs.getString("address"));
					employeeBean.setGender(rs.getBoolean("gender"));
					//employeeBean.setSkills(rs.getString("skill"));
					employeeBean.setSalary(rs.getLong("salary"));
					employeeBean.setBirthDate(rs.getDate("birthdate"));

					employeeBean.setSkills(getSkillsOfEmp(employeeBean.getEmployeeId()));
				
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return employeeBean;
	}
	public void addSkillForEmployee(int employeeId, int skillId) 
	{
		try {
			conn = DBConnection.getDBConnection();
			pstmt =conn.prepareStatement("insert into employeeSkill(employeeId,skillId) values(?,?)");
			
			pstmt.setInt(1, employeeId);
			pstmt.setInt(2, skillId);
			
			pstmt.executeUpdate();
			}catch(Exception e)
			{
				e.printStackTrace();
			}	
	}

	public void deleteSkillForEmployee(int employeeId) 
	{	
		try {
			conn = DBConnection.getDBConnection();
			pstmt =conn.prepareStatement("delete from employeeSkill where employeeId=?");
	
			pstmt.setInt(1, employeeId);
			pstmt.executeUpdate();
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}
