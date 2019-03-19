package naucnaCentrala.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class SlanjeMejlaLosegFormata implements JavaDelegate{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("dosao u slanje mejla o losem formatu");
		
		Long idmag=(Long) execution.getVariable("idMagazina");
		
		
		Magazine magazine = magazineRepository.findByIdEquals(idmag);
		
		String u = (String) execution.getVariable("username");
		
		User user = userRepository.findByUsername(u);
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(user.getEmail());
		email.setSubject("Los format rada");
		
		String text = "Obavestava se autor "+user.getName()+" "+user.getSurname()+" da njegov rad pod nazivom '"+execution.getVariable("nazivrada")+"' nije dobro formatiran i da treba da koriguje PDF dokument.";
		
		email.setText(text);
		
		javaMailSender.send(email);
		
	}

}
