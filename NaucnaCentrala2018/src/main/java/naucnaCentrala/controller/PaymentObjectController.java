package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.service.PaymentObjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payment")
public class PaymentObjectController {
	
	@Autowired
	private PaymentObjectService paymentObjectService;
	
	@GetMapping("/createpo/{idm}")
	public String createPaymentObject(@PathVariable Long idm) {
		
		String res = paymentObjectService.createPO(idm);
		
		if(res!=null) {
			return res;
		}
		
		return null;
	}

}
