package naucnaCentrala.elasticsearch;

import java.util.ArrayList;

public class SearchDTO {
	
	private String laborname;
	
	private String magazinename;
	
	private String keyterms;
	
	private String author;
	
	private String andor;
	
	
	public String getAndor() {
		return andor;
	}

	public void setAndor(String andor) {
		this.andor = andor;
	}

	private String scientificarea;
	
	public SearchDTO() {
		
	}

	public String getLaborname() {
		return laborname;
	}

	public void setLaborname(String laborname) {
		this.laborname = laborname;
	}

	public String getMagazinename() {
		return magazinename;
	}

	public void setMagazinename(String magazinename) {
		this.magazinename = magazinename;
	}

	

	

	public String getKeyterms() {
		return keyterms;
	}

	public void setKeyterms(String keyterms) {
		this.keyterms = keyterms;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getScientificarea() {
		return scientificarea;
	}

	public void setScientificarea(String scientificarea) {
		this.scientificarea = scientificarea;
	}

}
