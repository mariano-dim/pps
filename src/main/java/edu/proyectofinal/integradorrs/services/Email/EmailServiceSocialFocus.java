package edu.proyectofinal.integradorrs.services.Email;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

public interface EmailServiceSocialFocus {

	
	public void sendEmailChangePassword(String emailTo, String body) 
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException;

	public void sendEmailChangePWSuccessfully(String emailTo, String subject, String body) 
			throws UnsupportedEncodingException;
	
	
}
