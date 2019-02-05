package naucnaCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.Labor;
import naucnaCentrala.service.LaborService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/labor")
public class LaborController {
	
	@Autowired
	private LaborService laborService;
	
	@GetMapping("/getLabors/{id}")
	public ResponseEntity<List<Labor>> getLabors(@PathVariable Long id){
		
		List<Labor> labors =laborService.getLabors(id);
		
		
		return new ResponseEntity<>(labors, HttpStatus.OK);
	}

}
