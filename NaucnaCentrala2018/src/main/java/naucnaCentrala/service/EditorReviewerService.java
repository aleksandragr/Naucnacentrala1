package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class EditorReviewerService {
	
	@Autowired
	private EditorReviewerRepository EditorReviewerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public String whoisloggedin() {
		

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		if(user==null) {
			EditorReviewer er = EditorReviewerRepository.findByUsername(username);
			if(er==null) {
				return "nista";
			}
			else {
				return "editor";
			}
		}
		else {
			return "author";
		}
		
		
	}

}
