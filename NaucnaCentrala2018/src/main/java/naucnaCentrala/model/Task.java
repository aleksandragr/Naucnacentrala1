package naucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private EditorReviewer editorreviewer;
	
	@OneToOne
	private Labor labor;
	
	
	public Task() {
		
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EditorReviewer getEditorreviewer() {
		return editorreviewer;
	}

	public void setEditorreviewer(EditorReviewer editorreviewer) {
		this.editorreviewer = editorreviewer;
	}

	public Labor getLabor() {
		return labor;
	}

	public void setLabor(Labor labor) {
		this.labor = labor;
	}

	
	
	
	

}
