package naucnaCentrala.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import naucnaCentrala.model.Magazine;


@Entity
public class Editor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String surname;
	private String city;
	private String country;
	private String title;
	private String email;
	private ArrayList<String> sections;
	private ArrayList<Magazine> magazines;   // ako je urednik size=1, ako je recezent size>=1
	private boolean isReviewer;  //ako je urednik onda je false, ako je recezent onda je true
	
	private String username;
	private String password;
	
	public Editor() {
		
		
		
	}
	
	
	public Editor(String name, String surname, String city, String country, String title, String email,
			ArrayList<String> sections, ArrayList<Magazine> magazines, boolean isReviewer, String username,
			String password) {
		
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.title = title;
		this.email = email;
		this.sections = new ArrayList<String>();
		this.magazines = new ArrayList<Magazine>();
		this.isReviewer = true;
		this.username = username;
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public ArrayList<String> getSections() {
		return sections;
	}


	public void setSections(ArrayList<String> sections) {
		this.sections = sections;
	}


	public ArrayList<Magazine> getMagazines() {
		return magazines;
	}


	public void setMagazines(ArrayList<Magazine> magazines) {
		this.magazines = magazines;
	}


	public boolean isReviewer() {
		return isReviewer;
	}


	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

}
