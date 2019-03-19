package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import naucnaCentrala.dto.FormFieldsDTO;
import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;
import naucnaCentrala.service.MagazineService;

@Controller
@RequestMapping("/magazine")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private FormService formService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/findAll")
	public ResponseEntity<ArrayList<MagazineDTO>> findAllMagazines (){
		
		ArrayList<MagazineDTO> magazines = magazineService.findAll();
		
		return new ResponseEntity<>(magazines, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/checkMembership/{id}")
	public ResponseEntity<String> checkMembership(@PathVariable Long id){
		
		String message = null;//magazineService.checkMembership(id);
		
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/checkMagazine/{id}/{valid}")
	public ResponseEntity<FormFieldsDTO> checkMagazine(@PathVariable Long id, @PathVariable String valid){
		
		System.out.println(valid+ " jel ima clanarinu i jel validna");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		/*
		//provera da li je casopis open-access ili ne
		Magazine mag = magazineRepository.findByIdEquals(id);
		boolean isOA = true;
		if(mag.getPaymentMethod().equals("open-access")) {
			isOA=true;
		}else {
			isOA=false;
		}
		
		boolean isValid = true;
		if(valid.equals("valid")) {
			isValid=true;
		}else {
			isValid=false;
		}
		*/
		
		
		Map<String, Object> variables = new HashMap<>();
		
		
		//variables.put("isOpenAccess",isOA);
		//variables.put("isPlacena", isValid);
		variables.put("username", username);
		variables.put("idMagazina", id);
		ProcessInstance pi =runtimeService.startProcessInstanceByKey("AGmodel", variables);
		
		
		/*
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("AGmodel");
		
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		String taskId = task.getId();
		String piId = pi.getId();
		System.out.println(taskId + "  aaaaaaaaaaaaaaaa");
		taskService.claim(taskId, username);
		
		
		Map<String, Object> retVal = new HashMap<>();
		
		
		retVal.put("isOpenAccess",isOA);
		System.out.println("preeeeeeeeeeeeeeeeeeee");
		//taskService.complete(taskId,retVal);
 		
		
		
		
		
		
		System.out.println(task.getName()+ "   task jeeee");
		*/
		if(taskService.createTaskQuery().processInstanceId(pi.getId()).list().size()!=0) {
			Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
			
			TaskFormData tfd = formService.getTaskFormData(task.getId());
			
			ArrayList<FormField> properties = (ArrayList<FormField>)tfd.getFormFields();
			
			
			
			return new ResponseEntity<>(new FormFieldsDTO(task.getId(),pi.getId(),properties),HttpStatus.OK);
		}
		else {
			return null;
		}
	}
	

}
