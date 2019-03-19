package naucnaCentrala.dto;

import java.util.ArrayList;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDTO {
	
	private String taskId;
	
	private String processId;
	
	private ArrayList<FormField> formFields;
	
	
	
	public FormFieldsDTO() {
		
		
	}
	
	
	public ArrayList<FormField> getFormFields() {
		return formFields;
	}


	public void setFormFields(ArrayList<FormField> formFields) {
		this.formFields = formFields;
	}


	public FormFieldsDTO(String taskId, String processId, ArrayList<FormField> formFields) {
		
		this.taskId = taskId;
		this.processId = processId;
		this.formFields = formFields;
	}






	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}
