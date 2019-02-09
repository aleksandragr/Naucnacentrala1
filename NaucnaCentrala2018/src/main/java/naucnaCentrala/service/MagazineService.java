package naucnaCentrala.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.MembershipFeeRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class MagazineService {
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MembershipFeeRepository membershipFeeRepository;
	
	
	public ArrayList<MagazineDTO> findAll(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		
		ArrayList<Magazine> magazines =  (ArrayList<Magazine>) magazineRepository.findAll();
		
		if(magazines==null) {
			return null;
		}
		
		ArrayList<MagazineDTO> m = new ArrayList<>();
		
		for(int i=0; i<magazines.size(); i++) {
			MagazineDTO mag = new MagazineDTO();
			mag.setId(magazines.get(i).getId());
			mag.setName(magazines.get(i).getName());
			mag.setISSNnumber(magazines.get(i).getISSNnumber());
			mag.setMainEditor(magazines.get(i).getMainEditor().getName());
			mag.setAmount(magazines.get(i).getAmountmag());
			mag.setPaymentMethod(magazines.get(i).getPaymentMethod());
			mag.setRole(user.getRoles().get(0).getDescription());
			mag.setUrl("http://localhost:8048/dbfile/downloadFile="+magazines.get(i).getDbfile().getId());
			
			
			
			MembershipFee membershipfee = membershipFeeRepository.findByMagazine_idEqualsAndUser_idEquals(magazines.get(i).getId(), user.getId());
			
			if(membershipfee==null) {
				mag.setValidmembership("invalid");
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
				
				if(!(now.compareTo(membershipfee.getStartDate())>=0) || !(now.compareTo(membershipfee.getEndDate())<=0)) {
					mag.setValidmembership("invalid");
				}
				else {
					mag.setValidmembership("valid");
				}
						
			}
			
			if(user.getMagazine().size()!=0) {
				for(int j=0; j<user.getMagazine().size(); j++) {
					if(magazines.get(i).getMerchant_id().equals(user.getMagazine().get(j).getMerchant_id())) {
						System.out.println("iffff");
						mag.setBought("yes");
						break;
					}
					else {
						System.out.println("elseee");
						mag.setBought("no");
					}
				}
			}
			else {
				System.out.println("odma else");
				mag.setBought("no");
			}
			
			
			System.out.println(mag.getBought());
			
			m.add(mag);
		}
		

		
		return m;
	}
	
	public String checkMembershipSomething(Long id) {//za pravljenje clanarine
		
		Magazine magazine = magazineRepository.findByIdEquals(id);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);		
		
		
		if(((magazine.getPaymentMethod()).equals("open-access") && (user.getRoles().get(0).getName().equals("USER"))) ||
			((magazine.getPaymentMethod()).equals("no open-access") && (user.getRoles().get(0).getName().equals("AUTHOR")))) {
			
				System.out.println("Citaoc je pa ne placa ili autor ne placa");
				return "ok";
		}
		else {
	
				System.out.println("Autor je pa placa ili citao pa placa");
				
				//uzima sa trenutnan datum
				String timeStamp = new SimpleDateFormat("dd.MM.yyyy.").format(Calendar.getInstance().getTime());
				System.out.println(timeStamp);
				DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
				
				Date now=null;
				try {
					now = formatter.parse(timeStamp);
					} catch (ParseException e) {
					  e.printStackTrace();
					}
				
				//MembershipFee mf1 = user.getMembershipFee();
				
				/*
				if(mf1==null || !(now.compareTo(mf1.getStartDate())>=0) || !(now.compareTo(mf1.getEndDate())<=0)) {
					MembershipFee mf = new MembershipFee();
					mf.setUser(user);
					mf.setPrice("2.000");
					
					//kad se formira clanarina kao pocetan datum uzima se trenutan datum
					String timeStamp1 = new SimpleDateFormat("dd.MM.yyyy.").format(Calendar.getInstance().getTime());
					DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
					Date startDate = null;
					try {
						startDate = formatter1.parse(timeStamp1);
						} catch (ParseException e) {
						  e.printStackTrace();
						}
					mf.setStartDate(startDate);
					
					//kad se formira clanarina kao krajnji datum uzima se trenutan datum+3 meseca
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, +3);
					Date end = cal.getTime();
					String endString = formatter1.format(end);					
					Date endDate=null;
					try {
						endDate = formatter.parse(endString);
						} catch (ParseException e) {
						  e.printStackTrace();
						}				
					mf.setEndDate(endDate);
					
					membershipFeeRepository.save(mf);
					
					user.setMembershipFee(mf);
					userRepository.save(user);
					
					return "proces";
										
				}
				else {
					System.out.println("ima vec clanarinu i vazeca je");
					return "ima_clanarinu";
				}
					*/	
				
				return null;
				
		}
					
	}
	
	/*
	public String checkMembership(Long id) {
		
		Magazine magazine = magazineRepository.findByIdEquals(id);
		
		if(magazine.getPaymentMethod().equals("open-access")) {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username="";

			if (principal instanceof UserDetails) {
				username = ((UserDetails)principal).getUsername();
			} else {
				username = principal.toString();
			}
			
			User user = userRepository.findByUsername(username);
			
			if(user.getMembershipFee()!=null) {
				
				//uzima sa trenutnan datum
				String timeStamp = new SimpleDateFormat("dd.MM.yyyy.").format(Calendar.getInstance().getTime());
				DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
				
				Date now=null;
				try {
					now = formatter.parse(timeStamp);
					} catch (ParseException e) {
					  e.printStackTrace();
					}
				
				if(!(now.compareTo(user.getMembershipFee().getStartDate())>=0) || !(now.compareTo(user.getMembershipFee().getEndDate())<=0)) {
					return "novalidmembershipfree";//mora da plati
				}
				else {
					return "validmembershipfee";//ne mora da plati
				}
				
			}
			else {
				return "donthavemembershipfree";//mora da plati
			}
	
		}
		else {
			return "noopenaccess";//ne mora da plati
		}

	}*/

}
