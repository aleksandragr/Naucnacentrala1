package naucnaCentrala.dto;

public class MarkDTO {
	
	private String comment;
	
	private String mark;
	
	public MarkDTO() {
		
		
	}

	public MarkDTO(String comment, String mark) {
		
		this.comment = comment;
		this.mark = mark;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
	

}
