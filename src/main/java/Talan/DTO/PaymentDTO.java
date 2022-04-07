package Talan.DTO;

import java.util.Date;

public class PaymentDTO {
	private String paymentNumber;
	private String estimateNumber;
	private Integer paymentPrice;
	private Character paymentStatus;
	private Date paymentDate;
	private Date paymentCancleDate;
	private String paymentType;
	private String progressiveStatus;
	
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getEstimateNumber() {
		return estimateNumber;
	}
	public void setEstimateNumber(String estimateNumber) {
		this.estimateNumber = estimateNumber;
	}
	public Integer getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(Integer paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public Character getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Character paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getPaymentCancleDate() {
		return paymentCancleDate;
	}
	public void setPaymentCancleDate(Date paymentCancleDate) {
		this.paymentCancleDate = paymentCancleDate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getProgressiveStatus() {
		return progressiveStatus;
	}
	public void setProgressiveStatus(String progressiveStatus) {
		this.progressiveStatus = progressiveStatus;
	}
	
}
