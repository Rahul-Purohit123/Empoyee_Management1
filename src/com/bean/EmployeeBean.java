package com.bean;

import java.util.ArrayList;
import java.util.Date;

import com.bean.SkillBean;

public class EmployeeBean {

	 int employeeId;
	 String name;
	 String address;
	 boolean gender;
	 long salary;
	 Date birthdate;
	
	ArrayList<SkillBean> skills = new ArrayList<SkillBean>();
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean string) {
		this.gender = string;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	public Date getBirthDate() {
		return birthdate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthdate = birthDate;
	}
	public ArrayList<SkillBean> getSkills() {
		return skills;
	}
	public void setSkills(ArrayList<SkillBean> skills) {
		this.skills = skills;
	}
	
}
