package naucnaCentrala.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MembershipFeeRepository;
import naucnaCentrala.repository.UserRepository;
import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.PurchasedItemsDTO;

@Service
public class LaborService {
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MembershipFeeRepository membershipFeeRepository;
	
	
	public ArrayList<LaborDTO> getLabors(Long id){
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		
		String valid="";
		
		if(user!=null) {
			
			
			MembershipFee msf = membershipFeeRepository.findByMagazine_idEqualsAndUser_idEquals(id, user.getId());
			
			
			if(msf==null) {
				valid="invalid";
			}
			else {
				
				
				//uzima sa trenutan datum
				String timeStamp = new SimpleDateFormat("dd.MM.yyyy.").format(Calendar.getInstance().getTime());
				DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
				
				Date now=null;
				try {
					now = formatter.parse(timeStamp);
					} catch (ParseException e) {
					  e.printStackTrace();
					}
				
				if(!(now.compareTo(msf.getStartDate())>=0) || !(now.compareTo(msf.getEndDate())<=0)) {
					valid="invalid";
				}
				else {
					valid="valid";
				}
						
			}
					
			
		}
		

		
		ArrayList<Labor> labors = laborRepository.findByMagazine_idEquals(id);
		
		if(labors.size()==0) {
			return null;
		}
		
		ArrayList<LaborDTO> l = new ArrayList<>();
		
		for(int i=0; i<labors.size(); i++) {
			

			LaborDTO lab = new LaborDTO();
			lab.setId(labors.get(i).getId());
			lab.setHeading(labors.get(i).getHeading());
			lab.setAmount(labors.get(i).getAmountlabor());
			lab.setPaymentMethod(labors.get(i).getMagazine().getPaymentMethod());		
			lab.setUrl("http://localhost:8083/dbfile/downloadFile="+labors.get(i).getDbfile().getId());
			lab.setValidmembership(valid);
			
			
			l.add(lab);
		}
		
		
		
		return l;
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
