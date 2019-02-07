package naucnaCentrala.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class MembershipFee {
	
	



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonFormat(pattern="dd.MM.yyyy", shape=JsonFormat.Shape.STRING, timezone="Europe/Madrid")
	private Date startDate;
	
	@JsonFormat(pattern="dd.MM.yyyy", shape=JsonFormat.Shape.STRING, timezone="Europe/Madrid")
	private Date endDate;
	
	private double price;
	
	@ManyToOne
	private Magazine magazine;
	
	@ManyToOne
	private User user;
	
	public MembershipFee() {
		
		
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



	public Magazine getMagazine() {
		return magazine;
	}



	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}





}
