package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Magazine {



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String ISSNnumber;
	
	@ManyToMany
	private Set<ScientificArea> sections = new HashSet<>();
	private String paymentMethod;
	
	@OneToOne
	private EditorReviewer mainEditor;
	
	@OneToMany
	private Set<EditorReviewer> otherEditors = new HashSet<>();
	
	@ManyToMany
	private Set<EditorReviewer> reviewers = new HashSet<>();
	
	public Magazine() {
		
		
		
	}
	

	public Magazine(Long id, String name, String iSSNnumber, Set<ScientificArea> sections, String paymentMethod,
			EditorReviewer mainEditor, Set<EditorReviewer> otherEditors, Set<EditorReviewer> reviewers) {
		
		this.id = id;
		this.name = name;
		this.ISSNnumber = iSSNnumber;
		this.sections = sections;
		this.paymentMethod = paymentMethod;
		this.mainEditor = mainEditor;
		this.otherEditors = otherEditors;
		this.reviewers = reviewers;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getISSNnumber() {
		return ISSNnumber;
	}

	public void setISSNnumber(String iSSNnumber) {
		ISSNnumber = iSSNnumber;
	}

	

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public EditorReviewer getMainEditor() {
		return mainEditor;
	}



	public void setMainEditor(EditorReviewer mainEditor) {
		this.mainEditor = mainEditor;
	}



	public Set<EditorReviewer> getOtherEditors() {
		return otherEditors;
	}



	public void setOtherEditors(Set<EditorReviewer> otherEditors) {
		this.otherEditors = otherEditors;
	}



	public Set<EditorReviewer> getReviewers() {
		return reviewers;
	}



	public void setReviewers(Set<EditorReviewer> reviewers) {
		this.reviewers = reviewers;
	}
	

}
