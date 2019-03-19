package naucnaCentrala.controller;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.service.ScientificAreaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/scientificArea")
public class ScientificAreaController {
	
	@Autowired
	private ScientificAreaService scientificAreaService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/getSA/{taskid}")
	public ResponseEntity<List<ScientificArea>> getSAofMagazine(@PathVariable String taskid){
		System.out.println(taskid);
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String processId=task.getProcessInstanceId();
		
		String magazinId= runtimeService.getVariable(processId, "idMagazina").toString();
		Long id = Long.parseLong(magazinId);
		System.out.println(id+ "    id jeeeeeee\n");
		List<ScientificArea> sca = scientificAreaService.getSA(id);
		
		if(sca==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(sca, HttpStatus.OK);
	}

}
