package naucnaCentrala.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class SlanjeMejlovaPrijave implements JavaDelegate{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MagazineRepository magazineRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("dosao u slanje mejlova\n");
		Long idmag=(Long) execution.getVariable("idMagazina");
		
		
		Magazine magazine = magazineRepository.findByIdEquals(idmag);
		
		//slanje mejla autoru
		
		String u = (String) execution.getVariable("username");
		
		User user = userRepository.findByUsername(u);
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(user.getEmail());
		email.setSubject("Prijava rada");
		
		String text = "Obavestava se autor "+user.getName()+" "+user.getSurname()+" da se prijavljuje novi rad pod nazivom '"+execution.getVariable("nazivrada")+"' u casopis '"+magazine.getName()+"'.";
		
		email.setText(text);
		
		javaMailSender.send(email);
		
		//slanje mejla editoru
		
		String e = (String) execution.getVariable("maineditor");
		
		EditorReviewer er= editorReviewerRepository.findByUsername(e);
		
		SimpleMailMessage email2 = new SimpleMailMessage();
		email2.setTo(er.getEmail());
		email2.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(er.getEmail());
		email2.setSubject("Prijava novog rada");
		String text2 = "Obavestava se glavni urednik "+er.getName()+" "+er.getSurname()+" casopisa '"+magazine.getName()+"' da se prijavljuje novi rad pod nazivom '"+execution.getVariable("nazivrada")+"' u casopis '"+magazine.getName()+"'.";
				
		email2.setText(text2);
		
		javaMailSender.send(email2);
		
	}

}
