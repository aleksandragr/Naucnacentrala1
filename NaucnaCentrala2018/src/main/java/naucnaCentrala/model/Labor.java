package naucnaCentrala.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import naucnaCentrala.model.User;

@Entity
public class Labor {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String heading;
	//private ArrayList<User> coauthors;
	//private ArrayList<String> keyTerms;
	private boolean isAbstract;
	private String section;
	//PDF dokument
	//konacna verzija rada
	@ManyToOne
	private Magazine magazine;
	
	private double amountlabor;
	
	public Labor() {
		
		
		
	}


	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}
/*
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
*/
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


	public Magazine getMagazine() {
		return magazine;
	}


	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}


	public double getAmountlabor() {
		return amountlabor;
	}


	public void setAmountlabor(double amountlabor) {
		this.amountlabor = amountlabor;
	}
	

}
