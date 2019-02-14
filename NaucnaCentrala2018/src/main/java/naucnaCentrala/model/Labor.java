package naucnaCentrala.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import naucnaCentrala.model.User;

@Entity
public class Labor {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String heading;
	//private ArrayList<User> coauthors;
	private String keyTerms;
	
	
	public String getKeyTerms() {
		return keyTerms;
	}


	public void setKeyTerms(String keyTerms) {
		this.keyTerms = keyTerms;
	}


	@ManyToOne
	private ScientificArea scientificarea;
	
	private String abstracttext;
	
	
	//PDF dokument
	@OneToOne
	private DBFile dbfile;
	
	//konacna verzija rada
	
	
	@ManyToOne
	private Magazine magazine;
	
	private double amountlabor;
	
	private String state;
	//verified - rad je provaren i prihvacen
	//processing - rad se proverava
	//rejected - rad je odbijen i proces se terminira
	
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


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


	public ScientificArea getScientificarea() {
		return scientificarea;
	}


	public void setScientificarea(ScientificArea scientificarea) {
		this.scientificarea = scientificarea;
	}


	public String getAbstracttext() {
		return abstracttext;
	}


	public void setAbstracttext(String abstracttext) {
		this.abstracttext = abstracttext;
	}


	public DBFile getDbfile() {
		return dbfile;
	}


	public void setDbfile(DBFile dbfile) {
		this.dbfile = dbfile;
	}
	

}
