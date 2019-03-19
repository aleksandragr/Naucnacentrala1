package naucnaCentrala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
//import org.camunda.bpm.engine.RuntimeService;
//import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;


//import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;

import org.springframework.context.event.EventListener;


@SpringBootApplication
//@EnableProcessApplication
public class NaucnaCentrala2018Application {
	
	
	//@Autowired
	//private RuntimeService runtimeService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NaucnaCentrala2018Application.class, args);
	}
	
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
}
