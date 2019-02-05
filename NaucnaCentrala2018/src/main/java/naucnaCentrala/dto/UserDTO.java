package naucnaCentrala.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {
	
	private String name;
	
	private String surname;
	
	private String username;
	
	@JsonFormat(pattern="dd.MM.yyyy", shape=JsonFormat.Shape.STRING, timezone="Europe/Madrid")
	private Date startDate;
	
	@JsonFormat(pattern="dd.MM.yyyy", shape=JsonFormat.Shape.STRING, timezone="Europe/Madrid")
	private Date endDate;
	
	private String price;
	
	public UserDTO() {
		
		
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
