package naucnaCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.Transaction;
import naucnaCentrala.service.PaymentObjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payment")
public class PaymentObjectController {
	
	@Autowired
	private PaymentObjectService paymentObjectService;
	
	@GetMapping("/createpo/{idm}/{type}")
	public String createPaymentObject(@PathVariable Long idm, @PathVariable String type) {
		
		String res = paymentObjectService.createPO(idm,type);
		
		if(res!=null) {
			return res;
		}
		
		return null;
	}
	
	
	@PostMapping("/savetransaction")
	public String savetransaction(@RequestBody Transaction transaction) {
				
		paymentObjectService.savetrans(transaction);
		
		return null;		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/getTransaction")
	public List<Transaction> getTransactions(){
		
		List<Transaction> trans = paymentObjectService.getTrans();
		
		return trans;
	}

}
