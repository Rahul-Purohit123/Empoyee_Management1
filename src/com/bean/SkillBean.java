package com.bean;

public class SkillBean {

	 int skillId;
	 String name;
	
	
	 public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean  equals(Object obj)
	{
		System.out.println("----equals----");
		SkillBean skill = (SkillBean) obj;
		return this.skillId == skill.skillId;
	}
}
