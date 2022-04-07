package Talan.DTO;

public class FeedFileDTO {
	private String FeedNumber;
	private String StoreFileName;
	private String OriginFileName;
	private String FilePath;
	public String getFeedNumber() {
		return FeedNumber;
	}
	public void setFeedNumber(String feedNumber) {
		FeedNumber = feedNumber;
	}
	public String getStoreFileName() {
		return StoreFileName;
	}
	public void setStoreFileName(String storeFileName) {
		StoreFileName = storeFileName;
	}
	public String getOriginFileName() {
		return OriginFileName;
	}
	public void setOriginFileName(String originFileName) {
		OriginFileName = originFileName;
	}
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	
}
