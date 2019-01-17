package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import naucnaCentrala.dto.PaymentMFDTO;
import naucnaCentrala.service.PaymentObjService;

@Controller
@RequestMapping("/paymentobj")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentObjController {
	
	@Autowired
	private PaymentObjService paymentObjService;
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/paymentOfMF/{id}")
	public ResponseEntity<Long> paymentOfMf(@PathVariable Long id){
		
		Long rez = paymentObjService.paymentOfMem(id);
		
		return new ResponseEntity<>(rez,HttpStatus.OK);
	}
	
	
	@GetMapping("/getPaymentObj/{id}")
	public ResponseEntity<PaymentMFDTO> getPaymentObj(@PathVariable Long id){
		
		PaymentMFDTO p = paymentObjService.getPO(id);
		
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}
