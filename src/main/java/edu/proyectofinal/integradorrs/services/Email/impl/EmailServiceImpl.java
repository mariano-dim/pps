package edu.proyectofinal.integradorrs.services.Email.impl;

import static com.google.common.collect.Lists.newArrayList;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.services.Email.EmailServiceSocialFocus;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

@Service
public class EmailServiceImpl implements EmailServiceSocialFocus {

	
    @Autowired
    private EmailService emailService;
    
    
    
    
    @Override
	public void sendEmailWithoutTemplating() throws UnsupportedEncodingException {
		final Email email = DefaultEmail.builder().from(new InternetAddress("testsocialfocus@gmail.com", "Test Social Focus Sender"))
				.to(newArrayList(new InternetAddress("mariano.dim@gmail.com", "Test Social Focus Reciver")))
				.subject("You shall die! It's not me, it's Psychohistory").body("Hello Planet!").encoding("UTF-8")
				.build();

		emailService.send(email);

	}

}
