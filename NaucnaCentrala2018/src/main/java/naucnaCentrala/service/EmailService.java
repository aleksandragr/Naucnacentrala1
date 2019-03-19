package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.User;


import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	
	public void sendUser(User user, Labor l) throws MailException, InterruptedException {
		
		
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(user.getEmail());
		email.setSubject("Prijava novog rada");
		
		String text = "Obavestava se autor "+user.getName()+" "+user.getSurname()+" da se prijavljuje novi rad pod nazivom '"+l.getHeading()+"' u casopis '"+l.getMagazine().getName()+"'.";
		
		email.setText(text);
		
		javaMailSender.send(email);
		
		
	}
	
	
	public void sendER(EditorReviewer er, Labor l) throws MailException, InterruptedException {
		
		
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(er.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(er.getEmail());
		email.setSubject("Prijava novog rada");
		String text = "Obavestava se glavni urednik "+er.getName()+" "+er.getSurname()+" casopisa '"+l.getMagazine().getName()+"' da se prijavljuje novi rad pod nazivom '"+l.getHeading()+"' u casopis '"+l.getMagazine().getName()+"'.";
				
		email.setText(text);
		
		javaMailSender.send(email);
		
		
	}
	

}
