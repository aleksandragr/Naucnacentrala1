package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Magazine {



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String ISSNnumber;
	
	@ManyToMany
	private List<ScientificArea> scientificArea;
	private String paymentMethod;
	
	@OneToOne
	private EditorReviewer mainEditor;
	
	@OneToMany
	private Set<EditorReviewer> otherEditors = new HashSet<>();
	
	@ManyToMany
	private Set<EditorReviewer> reviewers = new HashSet<>();
	
	private String merchant_id;
	
	private String merchant_password;
	
	private double amountmag;
	
	//PDF dokument
	@OneToOne
	private DBFile dbfile;
	
	private String bitcointoken;
	
	private String clientId;
	
	private String clientSecret;
	
	
	public String getBitcointoken() {
		return bitcointoken;
	}


	public void setBitcointoken(String bitcointoken) {
		this.bitcointoken = bitcointoken;
	}


	public Magazine() {
		
		
		
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

	

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public EditorReviewer getMainEditor() {
		return mainEditor;
	}



	public void setMainEditor(EditorReviewer mainEditor) {
		this.mainEditor = mainEditor;
	}



	public Set<EditorReviewer> getOtherEditors() {
		return otherEditors;
	}



	public void setOtherEditors(Set<EditorReviewer> otherEditors) {
		this.otherEditors = otherEditors;
	}



	public Set<EditorReviewer> getReviewers() {
		return reviewers;
	}



	public void setReviewers(Set<EditorReviewer> reviewers) {
		this.reviewers = reviewers;
	}


	



	public List<ScientificArea> getScientificArea() {
		return scientificArea;
	}


	public void setScientificArea(List<ScientificArea> scientificArea) {
		this.scientificArea = scientificArea;
	}


	public String getMerchant_id() {
		return merchant_id;
	}


	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}


	public String getMerchant_password() {
		return merchant_password;
	}


	public void setMerchant_password(String merchant_password) {
		this.merchant_password = merchant_password;
	}






	public double getAmountmag() {
		return amountmag;
	}






	public void setAmountmag(double amountmag) {
		this.amountmag = amountmag;
	}


	public DBFile getDbfile() {
		return dbfile;
	}


	public void setDbfile(DBFile dbfile) {
		this.dbfile = dbfile;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getClientSecret() {
		return clientSecret;
	}


	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}


	
	

}
