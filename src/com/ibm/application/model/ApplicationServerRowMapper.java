package com.ibm.application.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

public class ApplicationServerRowMapper implements  RowMapper<ApplicationServerTO>{

	@Override
	public ApplicationServerTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ApplicationServerTO to=new ApplicationServerTO();
		if(rs.getString("serverName")!=null){
			to.setServerName(rs.getString("serverName"));
		}
		if(rs.getString("startTime")!=null){
			to.setStartDate(getDateFormat(rs.getString("startTime")));
		}
		if(rs.getString("endTime")!=null){
			to.setEndDate(getDateFormat(rs.getString("endTime")));
		}
		if(rs.getString("status_name")!=null){
			to.setServerStatus(rs.getString("status_name"));
		}
		to.setServerId(rs.getInt("serverId"));
		if(rs.getString("applicationName")!=null){
			to.setApplicationName(rs.getString("applicationName"));
		}
		to.setApplicationId(rs.getInt("applicationId"));
		
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
