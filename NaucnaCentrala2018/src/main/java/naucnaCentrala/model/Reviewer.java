package naucnaCentrala.model;

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
import javax.persistence.OneToOne;

@Entity
public class Reviewer{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String surname;
	private String city;
	private String country;
	private String email;
	
	
	private String username;
	private String password;
	
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.MERGE)
	private Set<Role> roles= new HashSet<>();
	
	@ManyToMany
	private List<ScientificArea> scientificArea; 
	
	public Reviewer() {
		
		
	}
	

	public Reviewer(Long id, String name, String surname, String city, String country, String email, String username,
			String password, Set<Role> roles, List<ScientificArea> scientificArea) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.scientificArea = scientificArea;
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public List<ScientificArea> getScientificArea() {
		return scientificArea;
	}


	public void setScientificArea(List<ScientificArea> scientificArea) {
		this.scientificArea = scientificArea;
	}

	
	

}
