package com.ibm.application.model;


public class ApplicationTO {

	private String applicationName;
	private String startDate;
	private String endDate;
	private String currentAction;
	private String danoneValidation;
	private String comments;
	private String projectedStartDate;
	private String projectedEndDate;
	private Integer applicationId;
	private String status;
	
	public String getCurrentAction() {
		return currentAction;
	}
	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}
	public String getDanoneValidation() {
		return danoneValidation;
	}
	public void setDanoneValidation(String danoneValidation) {
		this.danoneValidation = danoneValidation;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getProjectedStartDate() {
		return projectedStartDate;
	}
	public void setProjectedStartDate(String projectedStartDate) {
		this.projectedStartDate = projectedStartDate;
	}
	public String getProjectedEndDate() {
		return projectedEndDate;
	}
	public void setProjectedEndDate(String projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
