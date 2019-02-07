package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.PurchasedItemsDTO;
import naucnaCentrala.model.Labor;
import naucnaCentrala.service.LaborService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/labor")
public class LaborController {
	
	@Autowired
	private LaborService laborService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/getLabors/{id}")
	public ResponseEntity<List<LaborDTO>> getLabors(@PathVariable Long id){
		
		List<LaborDTO> labors =laborService.getLabors(id);
		
		
		return new ResponseEntity<>(labors, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/getLaborsMagazines")
	public ResponseEntity<List<PurchasedItemsDTO>> getLaborsMagazines(){
		
		ArrayList<PurchasedItemsDTO> items = laborService.getLM();
		
		if(items==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

}
