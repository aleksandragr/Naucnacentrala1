package naucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private double amount;
	
	private String merchantid;
	
	private String merchantpassword;
	
	private String magazinename;
	
	private String payermail;
	
	private String successUrl;
	
	private String merchantmail;
	
	private String fronturl;
	
	private String bitcointoken;
	
	
	public String getBitcointoken() {
		return bitcointoken;
	}

	public void setBitcointoken(String bitcointoken) {
		this.bitcointoken = bitcointoken;
	}

	public PaymentObject() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getMerchantpassword() {
		return merchantpassword;
	}

	public void setMerchantpassword(String merchantpassword) {
		this.merchantpassword = merchantpassword;
	}

	public String getMagazinename() {
		return magazinename;
	}

	public void setMagazinename(String magazinename) {
		this.magazinename = magazinename;
	}

	public String getPayermail() {
		return payermail;
	}

	public void setPayermail(String payermail) {
		this.payermail = payermail;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getMerchantmail() {
		return merchantmail;
	}

	public void setMerchantmail(String merchantmail) {
		this.merchantmail = merchantmail;
	}

	public String getFronturl() {
		return fronturl;
	}

	public void setFronturl(String fronturl) {
		this.fronturl = fronturl;
	}

}
