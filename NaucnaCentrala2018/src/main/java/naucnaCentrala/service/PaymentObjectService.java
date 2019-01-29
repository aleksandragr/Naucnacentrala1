package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObject;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class PaymentObjectService {
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	public String createPO(Long idm) {
		
		Magazine magazine = magazineRepository.findByIdEquals(idm);
		
		if(magazine!=null) {
			
			PaymentObject po = new PaymentObject();
			po.setAmount(1);
			po.setDescription("Placanje clanarine");
			po.setTitle("Korisnik placa clanarinu u iznosu od 1$");
			po.setMerchantid(magazine.getMerchant_id());
			po.setMerchantpassword(magazine.getMerchant_password());
			
			HttpHeaders header = new HttpHeaders();
			HttpEntity entity = new HttpEntity(po,header);
			
			String response = restTemplate.postForObject("http://localhost:8051/objectpayment/savepaymentobject", entity, String.class);
			
			
			System.out.println("ID iz kp je : "+ response);
			
			return response;
		}
		
		
		return null;
	}

}
