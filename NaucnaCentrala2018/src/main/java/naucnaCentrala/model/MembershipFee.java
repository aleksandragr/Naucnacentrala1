package naucnaCentrala.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class MembershipFee {
	
	



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private User user;
	
	@JsonFormat(pattern="dd.MM.yyyy", shape=JsonFormat.Shape.STRING, timezone="Europe/Madrid")
	private Date startDate;
	
	@JsonFormat(pattern="dd.MM.yyyy", shape=JsonFormat.Shape.STRING, timezone="Europe/Madrid")
	private Date endDate;
	
	private String price;
	
	public MembershipFee() {
		
		
	}


	public MembershipFee(Long id, User user, Date startDate, Date endDate, String price) {
		
		this.id = id;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


}
