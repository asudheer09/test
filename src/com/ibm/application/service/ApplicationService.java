package com.ibm.application.service;

import java.util.List;

import com.ibm.application.model.ApplicationServerTO;
import com.ibm.application.model.ApplicationTO;
import com.ibm.application.model.ServerTO;

public interface ApplicationService {

	public void addApplicationService(ApplicationTO to);
	
	public List<ApplicationTO> getApplicationList();
	
	public ApplicationTO getApplication(int applicationId);
	
	public void saveApplication(ApplicationTO to);
	
	public void addServerService(ServerTO to);
	
	public List<ServerTO> getServerList();
	
	public ServerTO getServer(int serverId);
	
	public void saveServer(ServerTO to);
	
	public List<ApplicationServerTO> getAllServersFromApplication(Integer applicationId);
	
	public List<ApplicationServerTO> getAllList();
	
}
