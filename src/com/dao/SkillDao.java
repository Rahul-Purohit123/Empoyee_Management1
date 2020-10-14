package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.SkillBean;
import com.util.DBConnection;


public class SkillDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public ArrayList<SkillBean> getAllSkills() {
		ArrayList<SkillBean> skills = new ArrayList<SkillBean>();

		try {
				conn = DBConnection.getDBConnection();
				System.out.println("conn:=="+conn);
				pstmt = conn.prepareStatement("select * from skillMaster");
				rs = pstmt.executeQuery();
		
		while(rs.next()) {
			SkillBean skillBean = new SkillBean();
			skillBean.setSkillId(rs.getInt("skillId"));
			skillBean.setName(rs.getString("name"));
			skills.add(skillBean);
		}
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}		
		return skills;
	}
}
