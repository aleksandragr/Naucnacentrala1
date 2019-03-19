package naucnaCentrala.dto;

public class DataReviewDTO {
	
	private String naziv;
	
	private String apstrakt;
	
	private String kljucnereci;
	
	public DataReviewDTO() {
		
	}

	public DataReviewDTO(String naziv, String apstrakt, String kljucnereci) {
		
		this.naziv = naziv;
		this.apstrakt = apstrakt;
		this.kljucnereci = kljucnereci;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getApstrakt() {
		return apstrakt;
	}

	public void setApstrakt(String apstrakt) {
		this.apstrakt = apstrakt;
	}

	public String getKljucnereci() {
		return kljucnereci;
	}

	public void setKljucnereci(String kljucnereci) {
		this.kljucnereci = kljucnereci;
	}
	
	
	
	

}
