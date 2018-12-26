package com.ibm.application.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.application.model.ApplicationRowMapper;
import com.ibm.application.model.ApplicationServerRowMapper;
import com.ibm.application.model.ApplicationServerTO;
import com.ibm.application.model.ApplicationTO;
import com.ibm.application.model.ServerRowMapper;
import com.ibm.application.model.ServerTO;
import com.ibm.application.service.ApplicationService;

@Repository
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void addApplicationService(ApplicationTO to) {
	StringBuilder sql=new StringBuilder("");
	sql.append(" INSERT INTO application ");
	sql.append("( applicationId, ");
	sql.append(" applicationName, ");
	sql.append(" startDate, ");
	sql.append(" endDate,");
	sql.append(" prjectedStartTime,");
	sql.append(" prjectedEndTime,");
	sql.append(" currentAction,");
	sql.append(" danoneValidation,");
	sql.append(" comments,");
	sql.append(" status ) ");
	sql.append(" VALUES ");
	sql.append("(?,?,?,?,?,?,?,?,?,?)");
	
	String startDate=(to.getStartDate().trim().equals(""))? null:to.getStartDate();
	String endDate= (to.getEndDate().trim().equals(""))? null:to.getEndDate();
	jdbcTemplate.update(sql.toString(),new Object[]{
				 to.getApplicationId(),
				 to.getApplicationName(), 
				 startDate,
				 endDate,
				 to.getProjectedStartDate(),
				 to.getProjectedEndDate(),
				 to.getCurrentAction(), 
				 to.getDanoneValidation(),
				 to.getComments(),
				 to.getStatus()
				 });
	}

	@Override
	public List<ApplicationTO> getApplicationList() {
		String sql="select * from application";
		List<ApplicationTO> appList =jdbcTemplate.query(sql, new ApplicationRowMapper());
		String sql2="select * from serverapplication";
		List<ServerTO> Serverlist =jdbcTemplate.query(sql2, new ServerRowMapper());
		List<String> statuList= new ArrayList<String>();
		for(ApplicationTO app:appList){
			for(ServerTO to:Serverlist){
				if(app.getApplicationId()==to.getApplicationId()){
					statuList.add(to.getServerStatus());
				}
			}
			if(checkStatus(statuList)){
				app.setStatus("Delivered");
			}else{
				app.setStatus("In Progress ");
			}
		}
		
		return appList;
	}

	private boolean checkStatus(List<String> statusList) {
		
		for(String s:statusList){
			if(!s.equalsIgnoreCase("Delivered")){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public List<ApplicationServerTO> getAllList() {
		String sql="select serverId, serverName,applicationName,startTime,endTime,status_name,serverapplication.applicationId from application inner  join serverapplication on application.applicationId=serverapplication.applicationId";
		List<ApplicationServerTO> list =jdbcTemplate.query(sql, new ApplicationServerRowMapper());
		return list;
	}
	
	@Override
	public ApplicationTO getApplication(int applicationId) {
		String sql="select * from application where applicationId=?";
		return jdbcTemplate.queryForObject(sql, new ApplicationRowMapper(), new Object[]{applicationId});
	}

	@Override
	public void saveApplication(ApplicationTO to) {
		String startDate=(to.getStartDate().trim().equals(""))? null:to.getStartDate();
		String endDate= (to.getEndDate().trim().equals(""))? null:to.getEndDate();
		
		 if (to.getApplicationId()!=null && to.getApplicationId() > 0) {
			 
		        String sql = "UPDATE application SET applicationName=?, startDate=?, endDate=?, "
		                    + "currentAction=?,danoneValidation=?,comments=?,status=? WHERE applicationId=?";
		        jdbcTemplate.update(sql, to.getApplicationName(), 
		        						 startDate,
		        						 endDate,
		        						 to.getCurrentAction(), 
		        						 to.getDanoneValidation(),
		        						 to.getComments(),
		        						 to.getStatus(),
		        						 to.getApplicationId());
		    } else {
		    	addApplicationService(to);
		    }
	}
	
	String getDateFormat(String d){
		 try {
			 String DATE_FORMAT_RESULT = "YYYY-MM-dd HH:MM:SS";
			 String DATE_FORMAT_INPUT = "YYYY-MM-dd HH:MM";
			 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_INPUT);
			 Date date = sdf.parse(d);
			 SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT_RESULT);
			 System.out.println(sdf2.format(date));
			 return sdf2.format(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		   
		return null;
		
	}

	@Override
	public void addServerService(ServerTO to) {
		StringBuilder sql=new StringBuilder("");
		sql.append(" INSERT INTO serverapplication ");
		sql.append("( serverId, ");
		sql.append(" applicationId, ");
		sql.append(" serverName, ");
		sql.append(" startTime, ");
		sql.append(" endTime,");
		sql.append(" status_name )");
		sql.append(" values(?,?,?,?,?,?)");
		
		String startDate=(to.getStartDate().trim().equals(""))? null:to.getStartDate();
		String endDate= (to.getEndDate().trim().equals(""))? null:to.getEndDate();

		
		jdbcTemplate.update(sql.toString(),new Object[]{
					 to.getServerId(),
					 to.getApplicationId(),
					 to.getServerName(), 
					 startDate, // to.getStartDate(),
					 endDate,  //to.getEndDate(),
					 to.getServerStatus()
					 });
		}

	@Override
	public List<ServerTO> getServerList() {
		String sql="select * from serverapplication";
		List<ServerTO> list =jdbcTemplate.query(sql, new ServerRowMapper());
		System.out.println(list.size());
		return list;
	}

	@Override
	public ServerTO getServer(int serverId) {
		String sql="select * from serverapplication WHERE serverId=?";
		return jdbcTemplate.queryForObject(sql, new ServerRowMapper(), new Object[]{serverId});
	}

	@Override
	public void saveServer(ServerTO to) {

		String startDate=(to.getStartDate().trim().equals(""))? null:to.getStartDate();
		String endDate= (to.getEndDate().trim().equals(""))? null:to.getEndDate();
		if (to.getServerId()!=null && to.getServerId() > 0) {
	        String sql = "UPDATE serverapplication SET startTime=?, endTime=?, status_name=?,applicationId=?,serverName=? WHERE serverId=?";
	        jdbcTemplate.update(sql,startDate,endDate,to.getServerStatus(),to.getApplicationId(),to.getServerName(),to.getServerId());
	    } else {
	    	addServerService(to);
	    }
		
	}
	
	public List<ApplicationServerTO> getAllServersFromApplication(Integer applicationId){
		String sql="select serverapplication.serverId, serverapplication.serverName,application.applicationName,serverapplication.startTime,serverapplication.endTime,serverapplication.status_name from application inner  join serverapplication on application.applicationId=serverapplication.applicationId where application.applicationId=?";
		List<ApplicationServerTO> list=jdbcTemplate.query(sql, new ApplicationServerRowMapper(),new Object[]{applicationId});
		return list;
	}

}
