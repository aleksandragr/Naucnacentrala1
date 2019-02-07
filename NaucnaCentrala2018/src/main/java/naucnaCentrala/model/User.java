package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="user_author")
public class User {




	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String surname;
	private String city;
	private String country;
	private String email;
	private boolean isAuthor;
	
	private String username;
	private String password;
	private String confirmP;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.MERGE)
	private Set<Role> roles= new HashSet<>();
	
	
	
	//lista kupljenih magazina
	@ManyToMany
	private List<Magazine> magazine;
	
	//lista kupljenih radova
	@ManyToMany
	private List<Labor> labor;
	
	public User() {
		
		
		
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isAuthor() {
		return isAuthor;
	}


	public void setAuthor(boolean isAuthor) {
		this.isAuthor = isAuthor;
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public String getConfirmP() {
		return confirmP;
	}




	public void setConfirmP(String confirmP) {
		this.confirmP = confirmP;
	}




	public List<Role> getRoles() {
		List<Role> role = new ArrayList<>(roles);
		if(role.size()>0) {
			return role;
		}
		return null;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	


	public List<Magazine> getMagazine() {
		return magazine;
	}


	public void setMagazine(List<Magazine> magazine) {
		this.magazine = magazine;
	}


	


	public List<Labor> getLabor() {
		return labor;
	}


	


	


	public void setLabor(List<Labor> labor) {
		this.labor = labor;
	}







}
