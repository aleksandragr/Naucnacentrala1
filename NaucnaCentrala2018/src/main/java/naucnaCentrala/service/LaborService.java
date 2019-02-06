package naucnaCentrala.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.UserRepository;
import naucnaCentrala.dto.PurchasedItemsDTO;

@Service
public class LaborService {
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ArrayList<Labor> getLabors(Long id){
		System.out.println("stigo");
		ArrayList<Labor> labors = laborRepository.findByMagazine_idEquals(id);
		
		if(labors.size()==0) {
			return null;
		}
		
		System.out.println(labors.size());
		
		return labors;
	}
	
	public ArrayList<PurchasedItemsDTO> getLM(){
		
		ArrayList<PurchasedItemsDTO> items = new ArrayList<>();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		
		List<Magazine> magazines =  user.getMagazine();
		
		if(magazines!=null) {
			for(int i=0; i<magazines.size(); i++) {
				PurchasedItemsDTO p = new PurchasedItemsDTO();
				p.setName(magazines.get(i).getName());
				p.setType("magazine");
				p.setDownloadurl("http://localhost:8083/dbfile/downloadFile="+magazines.get(i).getDbfile().getId());
				items.add(p);
			}
		}
		
		
		List<Labor> labors =  user.getLabor();
		
		if(labors!=null) {
			for(int j=0; j<labors.size(); j++) {
				PurchasedItemsDTO p = new PurchasedItemsDTO();
				p.setName(labors.get(j).getHeading());
				p.setType("labor");
				p.setDownloadurl("http://localhost:8083/dbfile/downloadFile="+labors.get(j).getDbfile().getId());
				items.add(p);
			}
		}
		
		
		
		return items;
	}

}
