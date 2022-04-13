package Talan.DTO;

public class ProDTO {
	
	private String proId;
	private Integer kindScore;
	private String category;
	private String license;
	
	public String toString() {
		return 	"proId="+proId
			+	"\n kindScore="+kindScore
			+	"\n category="+category
			+	"\n license="+license;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Integer getKindScore() {
		return kindScore;
	}

	public void setKindScore(Integer kindScore) {
		this.kindScore = kindScore;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
	
	
}
