package naucnaCentrala.dto;

public class LaborDTO {
	
	private Long id;
	
	private String heading;
	
	private double amount;
	
	private String url;
	
	private String paymentMethod;
	
	private String validmembership;
	
	public LaborDTO() {
		
		
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
