package naucnaCentrala.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class MagazineService {
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	
	public ArrayList<Magazine> findAll(){
		
		ArrayList<Magazine> magazines = (ArrayList<Magazine>) magazineRepository.findAll();
		
		if(magazines==null) {
			return null;
		}
		
		return magazines;
	}

}
