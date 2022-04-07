package Talan.DTO;

import java.util.Date;

public class InquiryDTO {
	private String inquiryNumber;
	private String peopleId;
	private String inquiryTitle;
	private String inquiryContent;
	private Character SecretStatus;
	private String inquiryPassword;
	private Date inquiryRegisterDate;
	private Date inquiryModifyDate;
	private Character responseStatus;
	
	public String getInquiryNumber() {
		return inquiryNumber;
	}
	public void setInquiryNumber(String inquiryNumber) {
		this.inquiryNumber = inquiryNumber;
	}
	public String getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	public String getInquiryTitle() {
		return inquiryTitle;
	}
	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public Character getSecretStatus() {
		return SecretStatus;
	}
	public void setSecretStatus(Character secretStatus) {
		SecretStatus = secretStatus;
	}
	public String getInquiryPassword() {
		return inquiryPassword;
	}
	public void setInquiryPassword(String inquiryPassword) {
		this.inquiryPassword = inquiryPassword;
	}
	public Date getInquiryRegisterDate() {
		return inquiryRegisterDate;
	}
	public void setInquiryRegisterDate(Date inquiryRegisterDate) {
		this.inquiryRegisterDate = inquiryRegisterDate;
	}
	public Date getInquiryModifyDate() {
		return inquiryModifyDate;
	}
	public void setInquiryModifyDate(Date inquiryModifyDate) {
		this.inquiryModifyDate = inquiryModifyDate;
	}
	public Character getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(Character responseStatus) {
		this.responseStatus = responseStatus;
	}
}

