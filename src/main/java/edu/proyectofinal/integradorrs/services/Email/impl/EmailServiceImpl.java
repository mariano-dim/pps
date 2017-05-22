package edu.proyectofinal.integradorrs.services.Email.impl;

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import edu.proyectofinal.integradorrs.services.Email.EmailServiceSocialFocus;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.ImageType;
import it.ozimov.springboot.mail.model.InlinePicture;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultInlinePicture;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.apache.commons.io.FileUtils;

@Service
public class EmailServiceImpl implements EmailServiceSocialFocus {

	@Autowired
	private EmailService emailService;
	private static final String EmailSender = "testsocialfocus@gmail.com";
	private static final String EmailSenderDescription = "Test Social Focus Sender";
	private static final String NotificationPWChanged = "Modificaste tu clave de Social Focus";

	/**
	 * Este email es para el aviso del usuario que se olvido la clave o que hace
	 * esenviarle un email al mismo para que a traves de ese link pueda ingresar
	 * un nuevo pasword
	 */
	@Override
	public void sendEmailChangePassword(String emailTo, String body) 
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {
		
        InlinePicture inlinePicture = createGalaxyInlinePicture();

		final Email email = DefaultEmail.builder()
				.from(new InternetAddress(this.EmailSender, this.EmailSenderDescription))
				.to(newArrayList(new InternetAddress(emailTo, ""))).subject(this.NotificationPWChanged).body(body)
				.encoding("UTF-8").build();

		String template = "emailTemplate.ftl";

		Map<String, Object> modelObject = ImmutableMap.of("title", "Emperor", "name", "Cleon I");

		emailService.send(email, template, modelObject,inlinePicture);

	}
	
    private InlinePicture createGalaxyInlinePicture() throws URISyntaxException {
    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    	InputStream is  = classloader.getResourceAsStream("images" + File.separator + "galaxy.jpeg");
    	
        File pictureFile = new File("images" + File.separator + "galaxy.jpeg");	
     
        try{
            FileUtils.copyInputStreamToFile(is, pictureFile);

        }catch(IOException io){
        	System.out.println("Error copiando streams");
        }
        
        Preconditions.checkState(pictureFile.exists(), "There is not picture %s", pictureFile.getName());

        return DefaultInlinePicture.builder()
                .file(pictureFile)
                .imageType(ImageType.JPG)
                .templateName("galaxy.jpeg").build();
    }

	/**
	 * Este email es para el aviso al usuario de que se ha registrado un cambio
	 * de password y que en caso de no haber sido el que se contacte con el
	 * centro de atencion al cliente
	 */
	@Override
	public void sendEmailChangePWSuccessfully(String emailTo, String subject, String body)
			throws UnsupportedEncodingException {
		final Email email = DefaultEmail.builder()
				.from(new InternetAddress("testsocialfocus@gmail.com", "Test Social Focus Sender"))
				.to(newArrayList(new InternetAddress("mariano.dim@gmail.com", "Test Social Focus Reciver")))
				.subject("You shall die! It's not me, it's Psychohistory").body("Hello Planet!").encoding("UTF-8")
				.build();

		emailService.send(email);

	}

}
