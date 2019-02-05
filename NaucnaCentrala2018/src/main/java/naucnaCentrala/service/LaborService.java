package naucnaCentrala.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Labor;
import naucnaCentrala.repository.LaborRepository;

@Service
public class LaborService {
	
	@Autowired
	private LaborRepository laborRepository;
	
	public ArrayList<Labor> getLabors(Long id){
		System.out.println("stigo");
		ArrayList<Labor> labors = laborRepository.findByMagazine_idEquals(id);
		
		if(labors.size()==0) {
			return null;
		}
		
		System.out.println(labors.size());
		
		return labors;
	}

}
