package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import naucnaCentrala.model.User;
import naucnaCentrala.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String singUp(User user) {
		
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		System.out.println("aaaa");
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
		long id = user.getRoles().get(0).getId();
		if(id==2) {
			user.setAuthor(true);
		}
		
		User u = userRepository.save(user);		
		return "Successful registration!";
		
	}

	//ova metoda se koristi prilikom logovanja
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
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
	

}
