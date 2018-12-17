package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import naucnaCentrala.model.User;
import naucnaCentrala.service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8082/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/sign-up")
	public ResponseEntity<HttpStatus> singUp(@RequestBody User user) {
		System.out.println("sign-up");
		User user1 = userService.singUp(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<HttpStatus> logIn(@RequestBody User user) { 
		
		//User user1 = userService.logIn(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/gagi")
	public void proba() {
		System.out.println("gagi ljubaviii");
	}

}
