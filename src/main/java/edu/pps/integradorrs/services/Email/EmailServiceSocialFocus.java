package edu.pps.integradorrs.services.Email;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

public interface EmailServiceSocialFocus {

	
	public void sendEmailChangePassword(String emailTo, String body) 
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException;

	public void sendEmailLinkTokenGenerate(String emailTo, String body, String token)
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException;
	
	
}
