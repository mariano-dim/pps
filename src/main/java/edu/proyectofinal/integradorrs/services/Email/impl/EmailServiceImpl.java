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
		
        InlinePicture inlinePicture = createGalaxyInlinePicture();

		final Email email = DefaultEmail.builder()
				.from(new InternetAddress(this.emailSender, this.emailSenderDescription))
				.to(newArrayList(new InternetAddress(emailTo, ""))).subject(this.notificationPWChanged).body(body)
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
	 * Se le avisa la usuario mediante un email que contiene una url y un token que clikee para cambia la pw
	 */
	@Override
	public void sendEmailLinkTokenGenerate(String emailTo, String body, String token)
			throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {

		InlinePicture inlinePicture = createGalaxyInlinePicture();

		final Email email = DefaultEmail.builder()
				.from(new InternetAddress(this.emailSender, this.emailSenderDescription))
				.to(newArrayList(new InternetAddress(emailTo, ""))).subject(this.notificationPWChanged).body(body)
				.encoding("UTF-8").build();

		String template = "emailTemplateLinkToken.ftl";

		Map<String, Object> modelObject = ImmutableMap.of("token", token, "email", emailTo);
		emailService.send(email, template, modelObject, inlinePicture);

	}

}
