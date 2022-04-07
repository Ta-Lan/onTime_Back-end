package Talan.DTO;

public class ReviewDTO {
	private String reviewNumber;
	private String paymentNumber;
	private String peopleId;
	private String reviewTitle;
	private String reviewContent;
	private Integer starPoint;
	private String proId;
	private String reviewResponse;
	
	public String getReviewNumber() {
		return reviewNumber;
	}
	public void setReviewNumber(String reviewNumber) {
		this.reviewNumber = reviewNumber;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Integer getStarPoint() {
		return starPoint;
	}
	public void setStarPoint(Integer starPoint) {
		this.starPoint = starPoint;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getReviewResponse() {
		return reviewResponse;
	}
	public void setReviewResponse(String reviewResponse) {
		this.reviewResponse = reviewResponse;
	}
	
}
