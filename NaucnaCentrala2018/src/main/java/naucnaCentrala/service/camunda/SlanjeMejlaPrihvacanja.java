package naucnaCentrala.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class SlanjeMejlaPrihvacanja implements JavaDelegate{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private LaborRepository laborRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("dosao u slanje mejla o prihvacanju\n");
				
		Long idmag=(Long) execution.getVariable("idMagazina");
				
				
		Magazine magazine = magazineRepository.findByIdEquals(idmag);
				
		String u = (String) execution.getVariable("username");
				
		User user = userRepository.findByUsername(u);
				
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(user.getEmail());
		email.setSubject("Objavljivanje rada");
				
		String text = "Obavestava se autor "+user.getName()+" "+user.getSurname()+" da ce se njegov rad pod nazivom '"+execution.getVariable("nazivrada")+"' objaviti u casopisu '"+magazine.getName()+"'.";
				
		email.setText(text);
				
		javaMailSender.send(email);
				
				
		String naslovrada = (String) execution.getVariable("nazivrada");
				
		Labor l = laborRepository.findByHeadingEquals(naslovrada);	
		
		l.setState("verified");
		
		laborRepository.save(l);
		
		user.getLabor().add(l);
		
		userRepository.save(user);
		
	}

}
