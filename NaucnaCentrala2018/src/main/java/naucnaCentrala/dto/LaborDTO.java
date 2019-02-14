package naucnaCentrala.dto;

public class LaborDTO {
	
	private Long id;
	
	private String heading;
	
	private double amount;
	
	private String url;
	
	private String paymentMethod;
	
	private String validmembership;
	
	private String bought;
	
	private String role;
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LaborDTO() {
		
		
	}
	
	public String getBought() {
		return bought;
	}

	public void setBought(String bought) {
		this.bought = bought;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getValidmembership() {
		return validmembership;
	}

	public void setValidmembership(String validmembership) {
		this.validmembership = validmembership;
	}
	

}
