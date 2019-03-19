package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import naucnaCentrala.dto.FormFieldsAddDTO;
import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.LaborESDTO;
import naucnaCentrala.dto.PurchasedItemsDTO;
import naucnaCentrala.model.DBFile;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.ScientificAreaRepository;
import naucnaCentrala.repository.UserRepository;
import naucnaCentrala.response.UploadFileResponse;
import naucnaCentrala.service.DBFileService;
import naucnaCentrala.service.EmailService;
import naucnaCentrala.service.LaborService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/labor")
public class LaborController {
	
	@Autowired
	private LaborService laborService;
	
	@Autowired
	private DBFileService dbFileService;
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/getLabors/{id}")
	public ResponseEntity<List<LaborDTO>> getLabors(@PathVariable Long id){
		
		List<LaborDTO> labors =laborService.getLabors(id);
		
		
		return new ResponseEntity<>(labors, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/getLaborsMagazines")
	public ResponseEntity<List<PurchasedItemsDTO>> getLaborsMagazines(){
		
		ArrayList<PurchasedItemsDTO> items = laborService.getLM();
		
		if(items==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping("/addLabor")
	public Long addlabor(@RequestBody LaborESDTO dto){
		
		Long response = laborService.addL(dto);
		
		if(response==null) {
			return null;
		}
		
		return response;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addPdfInLabor/{idlabor}/{task}")
	public String addPdfInLabor(@RequestParam("file") MultipartFile file, @PathVariable Long idlabor, @PathVariable String task) throws MailException, InterruptedException {
		System.out.println(task);
		DBFile dbfile = dbFileService.storeFile(file);
		
		Labor l = laborRepository.findByIdEquals(idlabor);
		if(l!=null) {
			l.setDbfile(dbfile);
			
			Labor saveL =laborRepository.save(l);
			
			if(saveL!=null) {
				
				
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String username="";

				if (principal instanceof UserDetails) {
					username = ((UserDetails)principal).getUsername();
				} else {
					username = principal.toString();
				}
				
				//FUNKCIJA DA POPUNI FORMU U CAMUNDAAAAAAAA
				laborService.addLaborTask(saveL, task);
				
				//User user = userRepository.findByUsername(username);
				
				//emailService.sendUser(user,l);
				
				//EditorReviewer er = l.getMagazine().getMainEditor();
				
				//emailService.sendER(er,l);	
				
				
				
				return "uspesno";
			}
			else {
				laborRepository.delete(l);
			}
		}
		
		
		return "neuspesno";
				
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addNewLabor/{taskid}")
	public Long addNewLabor(@PathVariable String taskid, @RequestBody ArrayList<FormFieldsAddDTO> listFields) {
		
		Map<String, Object> variables = new HashMap<>();
		
		for(FormFieldsAddDTO f: listFields) {
			System.out.println(f.getName()+ "   "+f.getValue());
			variables.put(f.getName(), f.getValue());
		}
		
		
		Labor l = new Labor();
		l.setHeading(variables.get("nazivrada").toString());
		l.setCoauthors(variables.get("koautori").toString());
		l.setKeyTerms(variables.get("kljucnereci").toString());
		
		ScientificArea s = scientificAreaRepository.findByNameEquals(variables.get("naucnaoblast").toString());
		
		l.setScientificarea(s);
		
		l.setAbstracttext(variables.get("abstrakt").toString());
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String processId=task.getProcessInstanceId();
		
		String magazinId= runtimeService.getVariable(processId, "idMagazina").toString();
		Long id = Long.parseLong(magazinId);
		
		Magazine m = magazineRepository.findByIdEquals(id);
		
		l.setMagazine(m);
		
		
		Labor saveL =laborRepository.save(l);
		
		return saveL.getId();
	}
	
	
	
	

}
