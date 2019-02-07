package naucnaCentrala.dto;

public class MagazineDTO {
	
	
	private Long id;
	
	private String name;
	
	private String ISSNnumber;
	
	private String mainEditor;
	
	private double amount;
	
	private String url;
	
	private String paymentMethod;
	
	private String role;
	
	private String validmembership;
	
	public MagazineDTO() {
		
		
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

	public String getISSNnumber() {
		return ISSNnumber;
	}

	public void setISSNnumber(String iSSNnumber) {
		ISSNnumber = iSSNnumber;
	}

	public String getMainEditor() {
		return mainEditor;
	}

	public void setMainEditor(String mainEditor) {
		this.mainEditor = mainEditor;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getValidmembership() {
		return validmembership;
	}

	public void setValidmembership(String validmembership) {
		this.validmembership = validmembership;
	}

	

	
	

}
