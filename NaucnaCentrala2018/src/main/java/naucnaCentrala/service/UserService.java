package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import naucnaCentrala.dto.UserDTO;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.EditorSA;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.Reviewer;
import naucnaCentrala.model.Role;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.EditorSARepository;
import naucnaCentrala.repository.MembershipFeeRepository;
import naucnaCentrala.repository.ReviewerRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MembershipFeeRepository membershipFeeRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@Autowired
	private EditorSARepository editorSARepository;
	
	@Autowired
	private ReviewerRepository reviewerRepository;
	
	public String singUp(User user) {
		
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getUsername().equals(user.getUsername())) {				
				//return "User with this username exists!";
				return null;
			}
		}
		
		if(user.getConfirmP()!=null) {
			if(!user.getPassword().equals(user.getConfirmP())) {				
				//return "Password and confirm password aren't equals!";
				return null;
			}
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
		/*System.out.println("aaaaa "+user.getRoles().get(0));
		if(user.getRoles().get(0).getId()!=null) {
			System.out.println("eeee");
			long id = user.getRoles().get(0).getId();
			if(id==2) {
				user.setAuthor(true);
			}
		}*/
		
		
		User u = userRepository.save(user);		
		return "Successful registration!";
		
	}

	//ova metoda se koristi prilikom logovanja
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);		
		if(user == null) {
			EditorReviewer er = editorReviewerRepository.findByUsername(username);
			if(er==null) {
				//
				EditorSA esa = editorSARepository.findByUsername(username);
				if(esa==null) {
					Reviewer r = reviewerRepository.findByUsername(username);
					if(r==null) {
						throw new UsernameNotFoundException(username);
					}
					return new org.springframework.security.core.userdetails.User(r.getUsername(), r.getPassword(), getAuthority(r));
				}
				//
				//throw new UsernameNotFoundException(username);
				return new org.springframework.security.core.userdetails.User(esa.getUsername(), esa.getPassword(), getAuthority(esa));
			}
			return new org.springframework.security.core.userdetails.User(er.getUsername(), er.getPassword(), getAuthority(er));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	
	private Set<SimpleGrantedAuthority> getAuthority(EditorReviewer user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(EditorSA user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(Reviewer user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	public UserDTO getInfoOfUser() {
		
		UserDTO userdto = new UserDTO();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		Role role= new Role();

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
			
		} else {
			username = principal.toString();
		}
		
		
		User user = userRepository.findByUsername(username);
		if(user!=null) {
			userdto.setName(user.getName());
			userdto.setSurname(user.getSurname());
			userdto.setUsername(user.getUsername());
		}
		
		EditorReviewer er = editorReviewerRepository.findByUsername(username);
		if(er!=null) {
			userdto.setName(er.getName());
			userdto.setSurname(er.getSurname());
			userdto.setUsername(er.getUsername());
		}
		
		EditorSA esa = editorSARepository.findByUsername(username);
		if(esa!=null) {
			userdto.setName(esa.getName());
			userdto.setSurname(esa.getSurname());
			userdto.setUsername(esa.getUsername());
		}
		
		Reviewer r = reviewerRepository.findByUsername(username);
		if(r!=null) {
			userdto.setName(r.getName());
			userdto.setSurname(r.getSurname());
			userdto.setUsername(r.getUsername());
		}
			
		
		return userdto;
	}
	
	
	public List<MembershipFee> getAllMF(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		
		User user = userRepository.findByUsername(username);
		List<MembershipFee> msf = new ArrayList<>();
		
		if(user!=null) {
			msf= membershipFeeRepository.findByUser_idEquals(user.getId());
			
			if(msf==null) {
				return null;
			}
			

		}
		
		
		return msf;
		
	}
	

}
