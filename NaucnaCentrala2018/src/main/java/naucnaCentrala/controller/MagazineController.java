package naucnaCentrala.controller;

import java.util.ArrayList;

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

import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.service.MagazineService;

@Controller
@RequestMapping("/magazine")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/findAll")
	public ResponseEntity<ArrayList<MagazineDTO>> findAllMagazines (){
		
		ArrayList<MagazineDTO> magazines = magazineService.findAll();
		
		return new ResponseEntity<>(magazines, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/checkMembership/{id}")
	public ResponseEntity<String> checkMembership(@PathVariable Long id){
		
		String message = null;//magazineService.checkMembership(id);
		
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	

}
