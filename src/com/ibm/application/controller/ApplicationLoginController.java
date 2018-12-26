package com.ibm.application.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.application.model.ApplicationServerTO;
import com.ibm.application.model.ApplicationTO;
import com.ibm.application.model.ServerTO;
import com.ibm.application.service.ApplicationService;

@Controller
public class ApplicationLoginController {

	private static final Logger logger = Logger.getLogger(ApplicationLoginController.class);

	@Autowired
	private ApplicationService applicationService;
	
	public ApplicationLoginController(){
		ApplicationTO application = new ApplicationTO();
		ServerTO server=new ServerTO();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("login");
		logger.info("from welcome() of ApplicationLoginController");
		model.addObject("title", "Login Page");
		model.addObject("msg", "Please Login");
		return model;
	}

	@RequestMapping(value = "/editApplicationDetails", method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView();
		List<ApplicationTO> list = applicationService.getApplicationList();
		model.addObject("applicationList", list);
		model.setViewName("admin");
		return model;
	}

	@RequestMapping(value = "/editApplication", method = RequestMethod.GET)
	public ModelAndView editApplication(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Map<String,String> statusMapp = new LinkedHashMap<String,String>();
		statusMapp.put(" ", " ");
		statusMapp.put("Network Task Ongoing", "Network Task Ongoing");
		statusMapp.put("Network Task Completed", "Network Task Completed");
		statusMapp.put("SAN Task Ongoing", "SAN Task Ongoing");
		statusMapp.put("VMWare Task Ongoing", "VMWare Task Ongoing");
		statusMapp.put("TSM Task Ongoing", "TSM Task Ongoing");
		statusMapp.put("BCRS Task Ongoing", "BCRS Task Ongoing");
		statusMapp.put("BCRS Task Completed<", "BCRS Task Completed");
		statusMapp.put("Server UP", "Server UP");
		statusMapp.put("Task Completed", "Task Completed");
		statusMapp.put("Delivered", "Delivered");
		statusMapp.put("OS Checks In Progress", "OS Checks In Progress");
		statusMapp.put("DB Checks In Progress", "DB Checks In Progress");
		statusMapp.put("App Checks In Progress", "App Checks In Progress");
		statusMapp.put("Restoration In Progress", "Restoration In Progress");
		model.addObject("statusList", statusMapp);
		
		Map<String,String> DanoneValidation = new LinkedHashMap<String,String>();
		DanoneValidation.put(" ", " ");
		DanoneValidation.put("Awaiting Danone Checks", "Awaiting Danone Checks");
		DanoneValidation.put("Application Tested and Validated", "Application Tested and Validated");
		DanoneValidation.put("Danone Checks In Progress", "Danone Checks In Progress");
		DanoneValidation.put("Danone Validation Completed", "Danone Validation Completed");
		DanoneValidation.put("Not Applicable", "Not Applicable");
		model.addObject("DanoneValidation", DanoneValidation);
		
		int applicationId = Integer.parseInt(request.getParameter("id"));
		ApplicationTO to = applicationService.getApplication(applicationId);
		model.addObject("application", to);
		model.setViewName("edit");
		return model;
	}
	
	@RequestMapping(value = "/newApplication", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		ApplicationTO application = new ApplicationTO();
		Map<String,String> statusMapp = new LinkedHashMap<String,String>();
		statusMapp.put(" ", " ");
		statusMapp.put("Network Task Ongoing", "Network Task Ongoing");
		statusMapp.put("Network Task Completed", "Network Task Completed");
		statusMapp.put("SAN Task Ongoing", "SAN Task Ongoing");
		statusMapp.put("VMWare Task Ongoing", "VMWare Task Ongoing");
		statusMapp.put("TSM Task Ongoing", "TSM Task Ongoing");
		statusMapp.put("BCRS Task Ongoing", "BCRS Task Ongoing");
		statusMapp.put("BCRS Task Completed<", "BCRS Task Completed");
		statusMapp.put("Server UP", "Server UP");
		statusMapp.put("Task Completed", "Task Completed");
		statusMapp.put("Delivered", "Delivered");
		statusMapp.put("OS Checks In Progress", "OS Checks In Progress");
		statusMapp.put("DB Checks In Progress", "DB Checks In Progress");
		statusMapp.put("App Checks In Progress", "App Checks In Progress");
		statusMapp.put("Restoration In Progress", "Restoration In Progress");
		model.addObject("statusList", statusMapp);
		
		Map<String,String> DanoneValidation = new LinkedHashMap<String,String>();
		DanoneValidation.put(" ", " ");
		DanoneValidation.put("Awaiting Danone Checks", "Awaiting Danone Checks");
		DanoneValidation.put("Application Tested and Validated", "Application Tested and Validated");
		DanoneValidation.put("Danone Checks In Progress", "Danone Checks In Progress");
		DanoneValidation.put("Danone Validation Completed", "Danone Validation Completed");
		DanoneValidation.put("Not Applicable", "Not Applicable");
		model.addObject("DanoneValidation", DanoneValidation);
	    model.addObject("application", application);
	    model.setViewName("edit");
	    return model;
	}
	
	
	@RequestMapping(value = "/saveApplication", method = RequestMethod.POST)
	public String saveContact(@ModelAttribute ApplicationTO application) {
		applicationService.saveApplication(application);
		return "redirect:/editApplicationDetails";
	}
	
	@RequestMapping(value = "/saveServer", method = RequestMethod.POST)
	public String saveContact(@ModelAttribute ServerTO server) {
		applicationService.saveServer(server);
		return "redirect:/editServerDetails";
	}
	
	@RequestMapping(value = "/applicationView", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView model = new ModelAndView();
		List<ApplicationTO> list = applicationService.getApplicationList();
		model.addObject("applicationList", list);
		int count=0;
		for(ApplicationTO to:list){
			if((to.getStatus()).equalsIgnoreCase("Delivered")){
				count++;
			}
		}
		model.addObject("count",count);
		model.setViewName("view");
		return model;
	}

	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
	public String loginFailed() {
		ModelAndView model = new ModelAndView();
		model.addObject("loginFailed","You have entered username or password incorrect.");
		return "loginFailed";
	}
		
	@RequestMapping(value = "/newServer", method = RequestMethod.GET)
	public ModelAndView newServer(ModelAndView model) {
		ServerTO server = new ServerTO();
		List<ApplicationTO> list = applicationService.getApplicationList();
		Map<Integer,String> appMapp = new LinkedHashMap<Integer,String>();
		for(ApplicationTO to:list){
			appMapp.put(to.getApplicationId(), to.getApplicationName());
		}
		Map<String,String> statusMapp = new LinkedHashMap<String,String>();
		statusMapp.put(" ", " ");
		statusMapp.put("Network Task Ongoing", "Network Task Ongoing");
		statusMapp.put("Network Task Completed", "Network Task Completed");
		statusMapp.put("SAN Task Ongoing", "SAN Task Ongoing");
		statusMapp.put("VMWare Task Ongoing", "VMWare Task Ongoing");
		statusMapp.put("TSM Task Ongoing", "TSM Task Ongoing");
		statusMapp.put("BCRS Task Ongoing", "BCRS Task Ongoing");
		statusMapp.put("BCRS Task Completed<", "BCRS Task Completed<");
		statusMapp.put("Server UP", "Server UP");
		statusMapp.put("Task Completed", "Task Completed");
		statusMapp.put("Delivered", "Delivered");
		statusMapp.put("OS Checks In Progress", "OS Checks In Progress");
		statusMapp.put("DB Checks In Progress", "DB Checks In Progress");
		statusMapp.put("App Checks In Progress", "App Checks In Progress");
		statusMapp.put("Restoration In Progress", "Restoration In Progress");
		model.addObject("serverStatus",statusMapp);
		model.addObject("appList", appMapp);
	    model.addObject("server", server);
	    model.setViewName("newServer");
		return model;
	}
	
	@RequestMapping(value = "/serverView", method = RequestMethod.GET)
	public ModelAndView serverView() {
		ModelAndView model = new ModelAndView();
		List<ApplicationServerTO> list = applicationService.getAllList();
		model.addObject("list", list);
		model.setViewName("serverView");
		return model;
	}
	
	@RequestMapping(value = "/showServerDetails", method = RequestMethod.GET)
	public ModelAndView showServerDetails(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		int applicationId = Integer.parseInt(request.getParameter("id"));
		List<ApplicationServerTO> list = applicationService.getAllServersFromApplication(applicationId);
		model.addObject("list", list);
		model.setViewName("showServerDetails");
		return model;
	}
	
	@RequestMapping(value = "/editServerDetails", method = RequestMethod.GET)
	public ModelAndView editServerDetails(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<ApplicationServerTO> list = applicationService.getAllList();
		model.addObject("list", list);
		model.setViewName("editServerDetails");
		return model;
	}
	
	@RequestMapping(value = "/editServer", method = RequestMethod.GET)
	public ModelAndView editServer(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<ApplicationTO> list = applicationService.getApplicationList();
		int serverId = Integer.parseInt(request.getParameter("id"));
		ServerTO server = applicationService.getServer(serverId);
		Map<Integer,String> appMapp = new LinkedHashMap<Integer,String>();
		for(ApplicationTO to:list){
			appMapp.put(to.getApplicationId(), to.getApplicationName());
		}
		Map<String,String> statusMapp = new LinkedHashMap<String,String>();
		statusMapp.put("Network Task Ongoing", "Network Task Ongoing");
		statusMapp.put("Network Task Completed", "Network Task Completed");
		statusMapp.put("SAN Task Ongoing", "SAN Task Ongoing");
		statusMapp.put("VMWare Task Ongoing", "VMWare Task Ongoing");
		statusMapp.put("TSM Task Ongoing", "TSM Task Ongoing");
		statusMapp.put("BCRS Task Ongoing", "BCRS Task Ongoing");
		statusMapp.put("BCRS Task Completed<", "BCRS Task Completed");
		statusMapp.put("Server UP", "Server UP");
		statusMapp.put("Task Completed", "Task Completed");
		statusMapp.put("Delivered", "Delivered");
		statusMapp.put("OS Checks In Progress", "OS Checks In Progress");
		statusMapp.put("DB Checks In Progress", "DB Checks In Progress");
		statusMapp.put("App Checks In Progress", "App Checks In Progress");
		statusMapp.put("Restoration In Progress", "Restoration In Progress");
		model.addObject("serverStatus",statusMapp);
		model.addObject("appList", appMapp);
		model.addObject("server", server);
		model.setViewName("newServer");
		return model;
	}
	
}
