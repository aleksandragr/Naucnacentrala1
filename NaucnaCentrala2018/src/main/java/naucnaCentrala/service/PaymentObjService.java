package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.PaymentMFDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObj;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.PaymentObjRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class PaymentObjService {
	
	@Autowired
	private PaymentObjRepository paymentObjRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	
	public Long paymentOfMem(Long id) {
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		
		Magazine magazine = magazineRepository.findByIdEquals(id);
		
		PaymentObj newPayment = new PaymentObj();
		newPayment.setCustomer(user);
		newPayment.setAmount(1);
		newPayment.setTitle("Payment of membership fee");
		newPayment.setSeller(magazine);
		
		paymentObjRepository.save(newPayment);
		
		return newPayment.getId();
	}
	
	public PaymentMFDTO getPO(Long id) {
		
		PaymentObj po = paymentObjRepository.findByIdEquals(id);
		
		PaymentMFDTO newObject = new PaymentMFDTO();
		
		if(po!=null) {
			
			newObject.setIdPaymentObj(po.getId());
			
			newObject.setIdCustomer(po.getCustomer().getId());
			newObject.setNameCustomer(po.getCustomer().getName());
			newObject.setSurnameCustomer(po.getCustomer().getSurname());
			newObject.setEmailCustomer(po.getCustomer().getUsername());
			
			newObject.setIdSeller(po.getSeller().getId());
			newObject.setNameSeller(po.getSeller().getName());
			newObject.setIssnumberSeller(po.getSeller().getISSNnumber());
			newObject.setMerchant_id(po.getSeller().getMerchant_id());
			newObject.setMerchant_password(po.getSeller().getMerchant_password());
			newObject.setAmountMag(po.getSeller().getAmountMag());
			
			newObject.setAmount(po.getAmount());
			
			return newObject;
			
		}
		else {
			return null;
		}
		
		
	}

}
