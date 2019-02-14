package naucnaCentrala.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "magazine", type = "labor")
public class LaborElasticSearch {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	private String magazinename;
	
	private String laborname;
	
	private String author;
	
	private String scientificarea;
	
	@Field(type = FieldType.Nested, includeInParent = true)
	private List<KeyTerm> keyTerms;
	
	@Field(type = FieldType.Text)
    private byte[] pdf;
	
	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public LaborElasticSearch() {
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMagazinename() {
		return magazinename;
	}

	public void setMagazinename(String magazinename) {
		this.magazinename = magazinename;
	}

	public String getLaborname() {
		return laborname;
	}

	public void setLaborname(String laborname) {
		this.laborname = laborname;
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

	public List<KeyTerm> getKeyTerms() {
		return keyTerms;
	}

	public void setKeyTerms(List<KeyTerm> keyTerms) {
		this.keyTerms = keyTerms;
	}

	
}
