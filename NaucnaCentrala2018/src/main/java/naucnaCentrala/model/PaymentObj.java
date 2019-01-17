package naucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PaymentObj {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User customer;
	
	@ManyToOne
	private Magazine seller;
	
	private double amount;
	
	private String title;
	
	public PaymentObj() {
		
	}
	
	public PaymentObj(Long id, User customer, Magazine seller, double amount, String title) {
		
		this.id = id;
		this.customer = customer;
		this.seller = seller;
		this.amount = amount;
		this.title = title;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Magazine getSeller() {
		return seller;
	}

	public void setSeller(Magazine seller) {
		this.seller = seller;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
