package naucnaCentrala.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Magazine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String ISSNnumber;
	private ArrayList<String> sections;
	private String paymentMethod;
	
	
	public Magazine() {
		
		
		
	}
	
	public Magazine(String name, String iSSNnumber, ArrayList<String> sections, String paymentMethod) {
		
		this.name = name;
		this.ISSNnumber = iSSNnumber;
		this.sections = new ArrayList<String>();
		this.paymentMethod = paymentMethod;
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

	public ArrayList<String> getSections() {
		return sections;
	}

	public void setSections(ArrayList<String> sections) {
		this.sections = sections;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

}
