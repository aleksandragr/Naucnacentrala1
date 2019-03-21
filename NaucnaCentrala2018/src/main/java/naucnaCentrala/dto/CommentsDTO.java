package naucnaCentrala.dto;

import java.util.ArrayList;

public class CommentsDTO {
	
	private String komodautora;
	
	private ArrayList<MarkDTO> odautorazarez;
	
	public CommentsDTO() {
		
	}

	public CommentsDTO(String komodautora, ArrayList<MarkDTO> odautorazarez) {
		
		this.komodautora = komodautora;
		this.odautorazarez = odautorazarez;
	}

	public String getKomodautora() {
		return komodautora;
	}

	public void setKomodautora(String komodautora) {
		this.komodautora = komodautora;
	}

	public ArrayList<MarkDTO> getOdautorazarez() {
		return odautorazarez;
	}

	public void setOdautorazarez(ArrayList<MarkDTO> odautorazarez) {
		this.odautorazarez = odautorazarez;
	}
	
	
	
	

}
