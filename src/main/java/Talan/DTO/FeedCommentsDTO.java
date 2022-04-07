package Talan.DTO;

public class FeedCommentsDTO {
	private String feedCommentsNumber;
	private String feedNumber;
	private String peopleId;
	private String feedCommentsContent;
	private String feedCommentsRegisterDate;
	
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
		return feedCommentsRegisterDate;
	}
	public void setFeedCommentsRegisterDate(String feedCommentsRegisterDate) {
		this.feedCommentsRegisterDate = feedCommentsRegisterDate;
	}
	
}
