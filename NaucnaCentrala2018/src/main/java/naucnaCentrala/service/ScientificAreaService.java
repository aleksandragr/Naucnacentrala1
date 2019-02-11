package naucnaCentrala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class ScientificAreaService {
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	
	public List<ScientificArea> getSA(Long id){
		
		Magazine magazine = magazineRepository.findByIdEquals(id);
		
		if(magazine!=null) {
			
			List<ScientificArea> sca = magazine.getScientificArea();
			
			return sca;
					
		}
		
		return null;
	}

}
