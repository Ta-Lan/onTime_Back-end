package Talan.DTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedCommentsDTO {
	private String feedCommentsNumber;
	private String feedNumber;
	private String peopleId;
	private String feedCommentsContent;
	private Date feedCommentsRegisterDate;
	
	public String getFeedCommentsNumber() {
		return feedCommentsNumber;
	}
	public void setFeedCommentsNumber(String feedCommentsNumber) {
		this.feedCommentsNumber = feedCommentsNumber;
	}
	public String getFeedNumber() {
		return feedNumber;
	}
	public void setFeedNumber(String feedNumber) {
		this.feedNumber = feedNumber;
	}
	public String getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	public String getFeedCommentsContent() {
		return feedCommentsContent;
	}
	public void setFeedCommentsContent(String feedCommentsContent) {
		this.feedCommentsContent = feedCommentsContent;
	}
	public String getFeedCommentsRegisterDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
		String strFeedCommentsRegisterDate = dateFormat.format(feedCommentsRegisterDate);
		return strFeedCommentsRegisterDate;
	}
	public void setFeedCommentsRegisterDate(Date feedCommentsRegisterDate) {
		this.feedCommentsRegisterDate = feedCommentsRegisterDate;
	}
	
}
