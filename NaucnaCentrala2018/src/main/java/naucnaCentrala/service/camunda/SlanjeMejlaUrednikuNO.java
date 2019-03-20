package naucnaCentrala.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.EditorSA;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.EditorSARepository;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class SlanjeMejlaUrednikuNO implements JavaDelegate{
	
	@Autowired
	private EditorSARepository editorSARepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String e = (String) execution.getVariable("editorSA");
		
		EditorSA er= editorSARepository.findByUsername(e);
		
		if(er!=null) {
			Long idmag=(Long) execution.getVariable("idMagazina");
			
			
			Magazine magazine = magazineRepository.findByIdEquals(idmag);
			
			SimpleMailMessage email2 = new SimpleMailMessage();
			email2.setTo(er.getEmail());
			email2.setFrom(env.getProperty("spring.mail.username"));
			System.out.println(er.getEmail());
			email2.setSubject("Obavestenje uredniku NO");
			String text2 = "Obavestava se urednik naucne oblasti "+er.getName()+" "+er.getSurname()+" da se prijavljuje novi rad pod nazivom '"+execution.getVariable("nazivrada")+"' u casopis '"+magazine.getName()+"'.";
					
			email2.setText(text2);
			
			javaMailSender.send(email2);
		}
		else {
			
			EditorReviewer er2 = editorReviewerRepository.findByUsername(e);
			
			Long idmag=(Long) execution.getVariable("idMagazina");
			
			
			Magazine magazine = magazineRepository.findByIdEquals(idmag);
			
			SimpleMailMessage email2 = new SimpleMailMessage();
			email2.setTo(er2.getEmail());
			email2.setFrom(env.getProperty("spring.mail.username"));
			System.out.println(er2.getEmail());
			email2.setSubject("Obavestenje glavnom uredniku");
			String text2 = "Obavestava se glavni urednik "+er2.getName()+" "+er2.getSurname()+" da se prijavljuje novi rad pod nazivom '"+execution.getVariable("nazivrada")+"' u casopis '"+magazine.getName()+"'.";
					
			email2.setText(text2);
			
			javaMailSender.send(email2);
		}
	}
	
	

}
