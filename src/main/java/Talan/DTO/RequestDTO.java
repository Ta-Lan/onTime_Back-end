package Talan.DTO;

import java.util.Date;

public class RequestDTO {
	private String requestNumber;
	private String peopleId;
	private String category;
	private Date requestDate;
	private String requestTitle;
	private String requestContent;
	private String preference;
	private Date requestRegisterDate;
	private Character requestStatus;
	private String town;
	private String district;
	private Integer taskLevel;
	
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestTitle() {
		return requestTitle;
	}
	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}
	public String getRequestContent() {
		return requestContent;
	}
	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public Date getRequestRegisterDate() {
		return requestRegisterDate;
	}
	public void setRequestRegisterDate(Date requestRegisterDate) {
		this.requestRegisterDate = requestRegisterDate;
	}
	public Character getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Character requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getTaskLevel() {
		return taskLevel;
	}
	public void setTaskLevel(Integer taskLevel) {
		this.taskLevel = taskLevel;
	}
}
