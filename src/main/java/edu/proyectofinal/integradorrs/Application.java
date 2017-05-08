package edu.proyectofinal.integradorrs;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@SpringBootApplication
@EnableEmailTools
@RestController
public class Application implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/echo")
	public String echo(@RequestParam String comm) {
		return "ping";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;		
	}
}
