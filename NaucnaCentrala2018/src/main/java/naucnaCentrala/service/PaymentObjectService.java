package naucnaCentrala.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.PaymentObject;
import naucnaCentrala.model.Transaction;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.MembershipFeeRepository;
import naucnaCentrala.repository.TransactionRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class PaymentObjectService {
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private MembershipFeeRepository membershipFeeRepository;
	
	public String createPO(Long idm, String type) {
		
		
		
		Magazine magazine = new Magazine();
		Labor labor = new Labor();
		
		if(type.equals("rad")) {
			labor = laborRepository.findByIdEquals(idm);
			magazine = labor.getMagazine();
		
		}
		else {
			magazine = magazineRepository.findByIdEquals(idm);
			
		}
		
		if(magazine!=null) {
			
			PaymentObject po = new PaymentObject();
			
			if(type.equals("clanarina")) {
				po.setAmount(4);
				po.setDescription("Korisnik placa clanarinu u iznosu od 4EUR");
				po.setTitle("Placanje clanarine za '"+magazine.getName()+"'");
				
			}
			else if(type.equals("magazin")) {
				po.setAmount(magazine.getAmountmag());
				po.setDescription("Korisnik placa magazin u iznosu od "+magazine.getAmountmag()+ "EUR");
				po.setTitle("Kupovina magazina '"+magazine.getName()+"'");
			}
			else if(type.equals("rad")) {
				po.setAmount(labor.getAmountlabor());
				po.setDescription("Korisnik placa rad u iznosu od "+labor.getAmountlabor()+ "EUR");
				po.setTitle("Kupovina rada '"+labor.getHeading()+"'");
			}
			
			po.setMerchantid(magazine.getMerchant_id());
			po.setMerchantpassword(magazine.getMerchant_password());
			po.setMerchantmail(magazine.getName());
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username="";

			if (principal instanceof UserDetails) {
				username = ((UserDetails)principal).getUsername();
			} else {
				username = principal.toString();
			}
			
			po.setPayermail(username);
			
			InetAddress ip;
	        String hostname;
	        try {
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostName();
	            String s =ip.toString();
	            String s1 = s.split("/")[1];
	            System.out.println("EVOOOO " + s1);
	            po.setSuccessUrl("http://" + s1 + ":8048/payment/savetransaction");
	            System.out.println("Your current IP address : " + ip);
	            System.out.println("Your current Hostname : " + hostname);
	 
	        } catch (UnknownHostException e) {
	 
	            e.printStackTrace();
	        }
			
			
			po.setFronturl("http://localhost:8082/#/mainpage/profile");
			po.setBitcointoken(magazine.getBitcointoken());

			HttpHeaders header = new HttpHeaders();
			HttpEntity entity = new HttpEntity(po,header);
			
			String response = restTemplate.postForObject("http://192.168.0.26:8051/objectpayment/savepaymentobject", entity, String.class);
			
			
			System.out.println("ID iz kp je : "+ response);
			
			return response;
		}
		
		
		return null;
	}
	
	public void savetrans(Transaction transaction) {
		System.out.println("SSSSSSSSSSSSSAAAAAA");
		Transaction t = transactionRepository.save(transaction);
		
		if(t.getDescription().contains("magazin") && t.getTitle().contains("magazin")) {
			
			User user = userRepository.findByUsername(t.getPayermail());
			
			Magazine magazine = magazineRepository.findByMerchantIdEquals(t.getMerchantid());
			if(magazine!=null) {
				
				user.getMagazine().add(magazine);
				
				userRepository.save(user);
			}
			
		}
		else if(t.getDescription().contains("rad") && t.getTitle().contains("rad")){
			
			User user = userRepository.findByUsername(t.getPayermail());
			
			String[] delovi = t.getTitle().split("'");
			
			String rad = delovi[1];
			
			
			Labor labor = laborRepository.findByHeadingEquals(rad);
			
			if(labor!=null) {
				
				user.getLabor().add(labor);
				
				userRepository.save(user);
			}
			
		}
		else if(t.getDescription().contains("clan") && t.getTitle().contains("clan")) {
			
			User user = userRepository.findByUsername(t.getPayermail());
			
			Magazine magazine = magazineRepository.findByMerchantIdEquals(t.getMerchantid());
			
			if(user!=null && magazine!=null) {
				
				
				MembershipFee membershipfee = membershipFeeRepository.findByMagazine_idEqualsAndUser_idEquals(user.getId(), magazine.getId());
				
				
				if(membershipfee==null) {
					
					MembershipFee mf = new MembershipFee();
					mf.setUser(user);
					mf.setMagazine(magazine);
					mf.setPrice(t.getAmount());
					
					//kad se formira clanarina kao pocetan datum uzima se trenutan datum
					String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = null;
					try {
						startDate = formatter1.parse(timeStamp1);
						} catch (ParseException e) {
						  e.printStackTrace();
						}
					mf.setStartDate(startDate);
					
					//kad se formira clanarina kao krajnji datum uzima se trenutan datum+1 meseca
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, +1);
					Date end = cal.getTime();
					String endString = formatter1.format(end);					
					Date endDate=null;
					try {
						endDate = formatter1.parse(endString);
						} catch (ParseException e) {
						  e.printStackTrace();
						}				
					mf.setEndDate(endDate);
					
					membershipFeeRepository.save(mf);
				}
				else {
					//danasnji datum
					String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = null;
					try {
						startDate = formatter1.parse(timeStamp1);
						} catch (ParseException e) {
						  e.printStackTrace();
						}
					
					membershipfee.setStartDate(startDate);
					
					//datum za 1 meseca
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, +1);
					Date end = cal.getTime();
					String endString = formatter1.format(end);					
					Date endDate=null;
					try {
						endDate = formatter1.parse(endString);
						} catch (ParseException e) {
						  e.printStackTrace();
						}
					
					membershipfee.setEndDate(endDate);
					
					membershipFeeRepository.save(membershipfee);
				}
				
				
			}
		}
			
	}
	
	public List<Transaction> getTrans(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		List<Transaction> t = transactionRepository.findByPayermailEquals(username);
		
		if(t.size()==0) {
			
			return null;
		}
		
		
		for(int i=0; i<t.size(); i++) {
			System.out.println(t.get(i).getType());
			if(!(t.get(i).getType().equals("bank"))) {
				String d = t.get(i).getDatetime();
				String[] delovi = d.split("T");
				String[] delovi2 = delovi[1].split("\\+");			
				t.get(i).setDatetime(delovi[0]+" "+delovi2[0]);
			}
			
		}
		
		
		return t;
	}
	

}
