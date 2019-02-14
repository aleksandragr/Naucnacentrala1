package naucnaCentrala.dto;

import java.util.ArrayList;
import java.util.List;

public class LaborESDTO {
	
	private String laborname;
	
	private Long magazineid;
	
	private Long scientificareaid;
	
	private String abstractt;
	
	private List<String> keyterms;
	
	private String pdfname;
	
	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public String getLaborname() {
		return laborname;
	}

	public void setLaborname(String laborname) {
		this.laborname = laborname;
	}

	public Long getMagazineid() {
		return magazineid;
	}

	public void setMagazineid(Long magazineid) {
		this.magazineid = magazineid;
	}

	public Long getScientificareaid() {
		return scientificareaid;
	}

	public void setScientificareaid(Long scientificareaid) {
		this.scientificareaid = scientificareaid;
	}

	public String getAbstractt() {
		return abstractt;
	}

	public void setAbstractt(String abstractt) {
		this.abstractt = abstractt;
	}

	public List<String> getKeyterms() {
		return keyterms;
	}

	public void setKeyterms(List<String> keyterms) {
		this.keyterms = keyterms;
	}

	public LaborESDTO() {
		
	}

}
