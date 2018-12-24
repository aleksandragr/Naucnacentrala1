package naucnaCentrala.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public Magazine checkMembership(Long id) {
		
		List<Magazine> magazines = magazineRepository.findAll();
		for(int i=0; i<magazines.size(); i++) {
			if(magazines.get(i).getId()==id) {
				if(!((magazines.get(i).getPaymentMethod()).equals("open-access"))) {
					System.out.println("autori ne placaju, citaoci placaju");
					return null;
				}
				else {
					System.out.println("citaoci ne placaju, autori placaju");
				}
			}
		}
		
		
		return null;
	}

}
