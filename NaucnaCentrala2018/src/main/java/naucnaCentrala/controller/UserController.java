package naucnaCentrala.controller;

import java.util.List;

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

import naucnaCentrala.dto.UserDTO;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.User;
import naucnaCentrala.service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/sign-up")
	public ResponseEntity<String> singUp(@RequestBody User user) {
		
		String user1 = userService.singUp(user);
		if(user1==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user1, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<HttpStatus> logIn(@RequestBody User user) { 
		
		//User user1 = userService.logIn(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/gagi")
	public ResponseEntity<HttpStatus> proba() {
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR') or hasRole('REVIEWER')")
	@GetMapping("/getInfo")
	public ResponseEntity<UserDTO> getUserInfo(){
		
		UserDTO user = userService.getInfoOfUser();
		
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/getMSF")
	public ResponseEntity<List<MembershipFee>> getMembershipfees(){
		
		List<MembershipFee> mfs = userService.getAllMF();
		
		if(mfs==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<>(mfs,HttpStatus.OK);
	}

}
