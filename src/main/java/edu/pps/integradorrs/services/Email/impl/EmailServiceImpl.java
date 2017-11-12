package edu.pps.integradorrs.services.Email.impl;

import static com.google.common.collect.Lists.newArrayList;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pps.integradorrs.services.Email.EmailServiceSocialFocus;
import it.ozimov.springboot.mail.model.InlinePicture;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailServiceImpl implements EmailServiceSocialFocus {

	@Autowired
	private EmailService emailService;

	@Value("${emailCustom.emailSender}")
	private String emailSender;

	@Value("${emailCustom.emailSenderDescription}")
	private String emailSenderDescription;

	@Value("${emailCustom.notificationTitle}")
	private String notificationPWChanged;

	/**
	 * Este email es para el aviso del usuario que se olvido la clave o que hace
	 * esenviarle un email al mismo para que a traves de ese link pueda ingresar
	 * un nuevo pasword
	 */
	@Override
	public void sendEmailChangePassword(String emailTo, String body) 
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {
		


	}
	
    private InlinePicture createGalaxyInlinePicture() throws URISyntaxException {

		return null;
    }

	/**
	 * Se le avisa la usuario mediante un email que contiene una url y un token que clikee para cambia la pw
	 */
	@Override
	public void sendEmailLinkTokenGenerate(String emailTo, String body, String token)
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {

	}

}
