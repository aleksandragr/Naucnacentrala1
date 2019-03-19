package naucnaCentrala.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.TaskDTO;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Task;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.TaskRepository;

@Service
public class TaskEditorService {
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@Autowired
	private LaborRepository laborRepository; 
	
	@Autowired
	private TaskRepository taskRepository;
	
	public String addTaskToEditor(Long idlabor) {
		System.out.println(idlabor);
		
		Labor l = laborRepository.findByIdEquals(idlabor);
		
		if(l!=null) {
			EditorReviewer er = l.getMagazine().getMainEditor();
			
			Task task = new Task();
			task.setEditorreviewer(er);
			task.setLabor(l);
			taskRepository.save(task);
			
			
			return null;
		}
		
		return null;
	}
	
	/*
	public List<TaskDTO> getT(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		EditorReviewer er = editorReviewerRepository.findByUsername(username);
		List<TaskDTO> newList = new ArrayList<>();
		if(er!=null) {
			
			List<Task> tasks = taskRepository.findAll();
			
			for(int i=0; i<tasks.size(); i++) {
				if(tasks.get(i).getEditorreviewer().getId().equals(er.getId())) {
					
					LaborDTO l = new LaborDTO();
					l.setId(tasks.get(i).getLabor().getId());
					l.setHeading(tasks.get(i).getLabor().getHeading());
					l.setKeyTerms(tasks.get(i).getLabor().getKeyTerms());
					l.setScientificarea(tasks.get(i).getLabor().getScientificarea().getName());
					l.setAbstracttext(tasks.get(i).getLabor().getAbstracttext());
					l.setDbfile(tasks.get(i).getLabor().getDbfile().getFileName());
					l.setUrl("http://localhost:8048/dbfile/downloadFile="+tasks.get(i).getLabor().getDbfile().getId());
					TaskDTO taskdto = new TaskDTO();
					taskdto.setEditorreviewer(er);
					taskdto.setLabor(l);
					taskdto.setId(tasks.get(i).getId());
					
					newList.add(taskdto);
				}
			}
		}
		
		
		
		
		
		return newList;
	}*/

}
