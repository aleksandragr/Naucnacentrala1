package naucnaCentrala.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import naucnaCentrala.model.User;

@Entity
public class Labor {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String heading;
	private ArrayList<User> coauthors;
	private ArrayList<String> keyTerms;
	private boolean isAbstract;
	private String section;
	//PDF dokument
	//konacna verzija rada
	
	public Labor() {
		
		
		
	}

	public Labor(Long id, String heading, ArrayList<User> coauthors, ArrayList<String> keyTerms, boolean isAbstract,
			String section) {
		
		this.id = id;
		this.heading = heading;
		this.coauthors = coauthors;
		this.keyTerms = keyTerms;
		this.isAbstract = isAbstract;
		this.section = section;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public ArrayList<User> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(ArrayList<User> coauthors) {
		this.coauthors = coauthors;
	}

	public ArrayList<String> getKeyTerms() {
		return keyTerms;
	}

	public void setKeyTerms(ArrayList<String> keyTerms) {
		this.keyTerms = keyTerms;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
