package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.User;


import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	
	public void sendUser(User user) throws MailException, InterruptedException {
		
		System.out.println("aloo glavonja");
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("grujica995@gmail.com");
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(user.getEmail());
		email.setSubject("Poziv na predstavu/film");
		System.out.println("aaaaaa");
		String text = "Salje se mejl"+user.getName();
		System.out.println("bbbbbb");	
		email.setText(text);
		System.out.println("cccccccc");
		javaMailSender.send(email);
		System.out.println("ddddd");
		
	}
	/*
	
	public void sendER(EditorReviewer er) throws MailException, InterruptedException {
		
		System.out.println("aloo glavonja");
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(er.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		System.out.println(er.getEmail());
		email.setSubject("Poziv na predstavu/film");
		String text = "Salje se mejl"+er.getName();
				
		email.setText(text);
		
		javaMailSender.send(email);
		
		
	}
	*/

}
