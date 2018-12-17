package naucnaCentrala.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.service.MagazineService;

@Controller
@RequestMapping("/magazine")
@CrossOrigin(origins = "http://localhost:8082/#/")
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;
	
	@GetMapping("/findAll")
	public ResponseEntity<ArrayList<Magazine>> findAllMagazines (){
		
		ArrayList<Magazine> magazines = magazineService.findAll();
		
		return new ResponseEntity<>(magazines, HttpStatus.OK);
	}
	

}
