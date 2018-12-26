package com.ibm.application.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

public class ApplicationRowMapper implements RowMapper<ApplicationTO>{

	@Override
	public ApplicationTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ApplicationTO to=new ApplicationTO();
		if(rs.getString("applicationName")!=null){
			to.setApplicationName(rs.getString("applicationName"));
		}
		if(rs.getString("startDate")!=null){
			to.setStartDate(getDateFormat(rs.getString("startDate")));
		}
		if(rs.getString("endDate")!=null){
			to.setEndDate(getDateFormat(rs.getString("endDate")));
		}
		if(rs.getString("prjectedStartTime")!=null){
			to.setProjectedStartDate(getDateFormat(rs.getString("prjectedStartTime")));
		}
		if(rs.getString("prjectedEndTime")!=null){
			to.setProjectedEndDate(getDateFormat(rs.getString("prjectedEndTime")));
		}
		if(rs.getString("currentAction")!=null){
			to.setCurrentAction(rs.getString("currentAction"));
		}
		if(rs.getString("comments")!=null){
			to.setComments(rs.getString("comments"));
		}
		to.setApplicationId(rs.getInt("applicationId"));
		if(rs.getString("danoneValidation")!=null){
			to.setDanoneValidation(rs.getString("danoneValidation"));
		}
		if(rs.getString("status")!=null){
			to.setStatus(rs.getString("status"));
		}
		
		return to;
	}
	
	
	String getDateFormat(String d){
		 
		 try {
			 String DATE_FORMAT_INPUT = "yyyy-MM-dd HH:mm:ss.S";
			 String DATE_FORMAT_RESULT = "yyyy-MM-dd HH:mm:ss";
			 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_INPUT);
			 SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT_RESULT);
			 return sdf2.format(sdf.parse(d));
			 
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		   
		return null;
		
	}

}
