package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import naucnaCentrala.model.User;
import naucnaCentrala.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("sign-up")
	public ResponseEntity<HttpStatus> singUp(@RequestBody User user) {
		System.out.println("controller1");
		User user1 = userService.singUp(user);
		System.out.println("controller2");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<HttpStatus> logIn(@RequestBody User user) { 
		System.out.println("mmmmmmmmmmmmmmmm");
		//User user1 = userService.logIn(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
