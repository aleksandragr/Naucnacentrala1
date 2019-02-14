package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.service.EditorReviewerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/editorreviewer")
public class EditorReviewerController {
	
	@Autowired
	private EditorReviewerService editorReviewerService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/whoIsLoggedIn")
	public String whoIsLoggedIn() {
		
		String response = editorReviewerService.whoisloggedin();
		
		return response;
	}

}
