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

import naucnaCentrala.dto.DataReviewDTO;
import naucnaCentrala.dto.FormFieldsAddDTO;
import naucnaCentrala.dto.FormFieldsDTO;
import naucnaCentrala.dto.TaskDTO;
import naucnaCentrala.model.DBFile;
import naucnaCentrala.model.Labor;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.service.DBFileService;
import naucnaCentrala.service.TaskEditorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	//@Autowired
	//private TaskService taskService;
	
	@Autowired
	private TaskEditorService taskEditorService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private DBFileService dbFileService;
	
	@Autowired
	private LaborRepository laborRepository;
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/addtask/{idlabor}")
	public void addTask(@PathVariable Long idlabor) {
		
		String res = taskEditorService.addTaskToEditor(idlabor);
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/getTasks")
	public ResponseEntity<List<TaskDTO>> getTasks() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		ArrayList<TaskDTO> Utasks = new ArrayList<>();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
		
		for(Task t:tasks) {
			TaskDTO tdto = new TaskDTO( t.getId(), t.getName(),null);
			Utasks.add(tdto);
		}
		
		
		
		return new ResponseEntity<>(Utasks,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/redirectTO/{taskId}")
	public ResponseEntity<TaskDTO> redirectTo(@PathVariable String taskId) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
		TaskDTO taskdto = new TaskDTO();
		for(Task t:tasks) {
			if(t.getId().equals(taskId)) {
				taskdto.setTaskId(taskId);
				taskdto.setPutanja(path(t.getName()));
				return new ResponseEntity<>(taskdto, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
	
	public String path(String nameTask) {
		
		switch (nameTask) {
		case "Autor unosi informacije o radu":
			 return "addlabor";
		
		case "Glavni urednik pregleda naziv rada, kljucne reci i apstrakt":
			 return "review";
		
		case "Glavni urednik pregleda PDF":
			 return "reviewpdf";
		
		case "Izmena PDF-a i podataka":
			 return "changedata";
		}
		
		return null;
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/fields/{taskId}")
	public ResponseEntity<FormFieldsDTO> getFields(@PathVariable String taskId){
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		TaskFormData tfd = formService.getTaskFormData(taskId);
		ArrayList<FormField> fields = (ArrayList<FormField>) tfd.getFormFields();
		
		
		return new ResponseEntity<>(new FormFieldsDTO(taskId, null, fields),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/data/{taskId}")
	public ResponseEntity<DataReviewDTO> getDataReview(@PathVariable String taskId){
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		String processId=task.getProcessInstanceId();
		
		String nazivrada= runtimeService.getVariable(processId, "nazivrada").toString();
		String apstrakt= runtimeService.getVariable(processId, "abstrakt").toString();
		String kljucnereci =runtimeService.getVariable(processId, "kljucnereci").toString();
		
		DataReviewDTO dr = new DataReviewDTO();
		dr.setNaziv(nazivrada);
		dr.setApstrakt(apstrakt);
		dr.setKljucnereci(kljucnereci);
		
		return new ResponseEntity<>(dr,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/relevant/{taskId}/{relevantornot}")
	public void setRelevantOrNot(@PathVariable String taskId, @PathVariable String relevantornot) {
		
		Map<String, Object> variables = new HashMap<>();
		
		if(relevantornot.equals("relevant")) {
			
			variables.put("isRelevant", true);
		}
		else {
			
			variables.put("isRelevant", false);
		}
		
		taskService.complete(taskId, variables);
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/pdf/{taskid}")
	public String getPdf(@PathVariable String taskid) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		
		String processId=task.getProcessInstanceId();
		
		String pdfurl= runtimeService.getVariable(processId, "pdf").toString();
		
		return pdfurl;
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@PostMapping("/reviewpdf/{taskid}/{formattedornot}")
	public ResponseEntity<Boolean> reviewPDF(@RequestBody FormFieldsAddDTO comment, @PathVariable String taskid, @PathVariable String formattedornot) {
		
		Map<String, Object> variables = new HashMap<>();
		
		if(formattedornot.equals("good")) {
			variables.put("isFormatiran", true);
			variables.put("komentarurednika","");
		}else {
			variables.put("isFormatiran", false);
			variables.put("komentarurednika",comment.getValue());
		}
		taskService.complete(taskid, variables);
		return new ResponseEntity<>(true, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('EDITOR') or hasRole('AUTHOR')")
	@GetMapping("/datachange/{taskid}")
	public ResponseEntity<List<FormFieldsAddDTO>> getDataChange(@PathVariable String taskid){
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult(); 
		String processInstanceId = task.getProcessInstanceId(); 
		
		ArrayList<FormFieldsAddDTO> retval = new ArrayList<FormFieldsAddDTO>();
		
		FormFieldsAddDTO polje1 = new FormFieldsAddDTO("nazivrada", runtimeService.getVariable(processInstanceId, "nazivrada").toString());
		FormFieldsAddDTO polje2 = new FormFieldsAddDTO("koautori", runtimeService.getVariable(processInstanceId, "koautori").toString());
		FormFieldsAddDTO polje3 = new FormFieldsAddDTO("kljucnereci", runtimeService.getVariable(processInstanceId, "kljucnereci").toString());
		FormFieldsAddDTO polje4 = new FormFieldsAddDTO("naucnaoblast", runtimeService.getVariable(processInstanceId, "naucnaoblast").toString());
		FormFieldsAddDTO polje5 = new FormFieldsAddDTO("abstrakt", runtimeService.getVariable(processInstanceId, "abstrakt").toString());
		FormFieldsAddDTO polje6 = new FormFieldsAddDTO("pdf", runtimeService.getVariable(processInstanceId, "pdf").toString());
		FormFieldsAddDTO polje7 = new FormFieldsAddDTO("komentarurednika", runtimeService.getVariable(processInstanceId, "komentarurednika").toString());
		
		retval.add(polje1);
		retval.add(polje2);
		retval.add(polje3);
		retval.add(polje4);
		retval.add(polje5);
		retval.add(polje6);
		retval.add(polje7);
		
		return new ResponseEntity<>(retval, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/uploadpdf/{taskid}")
	public String uploadPDF(@RequestParam("file") MultipartFile file, @PathVariable String taskid) throws MailException, InterruptedException {
		
		DBFile dbfile = dbFileService.storeFile(file);
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult(); 
		String processInstanceId = task.getProcessInstanceId(); 
		
		String laborname = runtimeService.getVariable(processInstanceId, "nazivrada").toString();
		
		Labor labor = laborRepository.findByHeadingEquals(laborname);
		
		labor.setDbfile(dbfile);
		
		laborRepository.save(labor);
		
		
		HashMap<String, Object> mapp = new HashMap<>();
		mapp.put("pdf", "http://localhost:8048/dbfile/downloadFile=" + labor.getDbfile().getId());
		
		taskService.complete(taskid,mapp); 
		
		return null;
	}
	
	

}
