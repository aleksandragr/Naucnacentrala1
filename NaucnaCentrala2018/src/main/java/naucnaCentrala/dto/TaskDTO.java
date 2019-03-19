package naucnaCentrala.dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;

public class TaskDTO {
	
	private String taskId;
	
	private String taskNaziv;
	
	private String putanja;


	public String getPutanja() {
		return putanja;
	}


	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}


	public TaskDTO() {
		
		
	}
	

	public TaskDTO(String taskId, String taskNaziv, String putanja) {
		
		this.taskId = taskId;
		this.taskNaziv = taskNaziv;
		this.putanja=putanja;
	}




	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getTaskNaziv() {
		return taskNaziv;
	}


	public void setTaskNaziv(String taskNaziv) {
		this.taskNaziv = taskNaziv;
	}
	
	
}
