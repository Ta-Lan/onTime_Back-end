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
}
