package edu.proyectofinal.integradorrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.services.Email.EmailServiceSocialFocus;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import edu.proyectofinal.integradorrs.exceptions.EmptyResultException;
import edu.proyectofinal.integradorrs.model.Token;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;
import org.springframework.http.HttpHeaders;



@RestController
@RequestMapping("/api/usuario")
public class LoginController extends AbstractController<Usuario> {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public EmailServiceSocialFocus emailService;
    
    
    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<Collection<Usuario>> getAll() {

        System.out.println("getAll");

        Collection<Usuario> usuarios = usuarioService.getAllUsuarios();

        return super.collectionResult(usuarios);

    }

    // fix http://stackoverflow.com/questions/3526523/spring-mvc-pathvariable-getting-truncated
    @RequestMapping(method = RequestMethod.GET, value = "/email/{email:.+}")
    public ResponseEntity<Usuario> getByEmail(@Validated @PathVariable("email") String email) {

        System.out.println("getByEmail");
        System.out.println("Email: " + email);

        Usuario usuario = usuarioService.getByEmail(email);

        if (null == usuario) {
            throw new EmptyResultException(Usuario.class);
        }
        return super.singleResult(usuario);

    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/{rs}/email/{email:.+}")
    public ResponseEntity<Token> getTokenByEmail(@Validated @PathVariable("rs") String rs, @Validated @PathVariable("email") String email) {
        //Acomode algunas cosas porque no estaba obteniendo el token correctamente.
        //Token token = new Token();
        //socialnetwork = "Twitter";
       
        if (rs.equals("fb")){
            rs = "Facebook";
        } else if (rs.equals("twitter")){
            rs = "Twitter";
        }
        System.out.println("getTokenByEmailAndSN");
        System.out.println("Email: " + email);
        System.out.println("Social Network: " + rs);
        
        Token token = usuarioService.getTokenByEmailAndSN(email, rs);
        //Token token = usuarioService.getTokenByEmail(email);
       /*Collection<Token> tokens = usuarioService.getTokenByEmail(email);
       for(Token atoken: tokens)
       {
           if(atoken.getSocialnetwork().equals(socialnetwork))
           {
               token = atoken;
           }
       }*/
       //System.out.println(token.getToken());
        if (null == token) {
            throw new EmptyResultException(Usuario.class);
        }
        
       return super.singleResult(token);

    }
    


    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public ResponseEntity<Usuario> getById(@Validated @PathVariable("id") String id) {

        System.out.println("getById");
        System.out.println("Id: " + id);

        Usuario usuario = usuarioService.getById(id);
        if (null == usuario) {
            throw new EmptyResultException(Usuario.class);
        }
        return super.singleResult(usuario);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

        usuarioService.save(usuario);

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

    }
    
    @RequestMapping(method = RequestMethod.POST, value="/push")
    public ResponseEntity<Token> saveToken(@RequestBody Token token) {

        usuarioService.saveToken(token);

        return new ResponseEntity<Token>(token, HttpStatus.OK);

    }
    
    /*
    @RequestMapping(method = RequestMethod.DELETE, value="/token")
    public ResponseEntity<Token> removeToken(@RequestBody Token token) {

        usuarioService.deleteToken(token);

        return new ResponseEntity<Token>(token, HttpStatus.OK);

    }
    */
    
    //Borrar token
    
     @RequestMapping(method = RequestMethod.DELETE, value="/token/{email:.+}/{socialnetwork}")
    //public ResponseEntity<String> removeToken(@Validated @PathVariable("email") String email) {
    public void removeToken(@Validated @PathVariable("email") String email, @Validated @PathVariable("socialnetwork") String socialnetwork) {
    
        usuarioService.deleteToken(email,socialnetwork);

       // return new ResponseEntity<String>(email, HttpStatus.OK);
    }
     
     
     
     
	@RequestMapping(method = RequestMethod.PATCH, 
			        value = "/email/modify/{email:.+}")
	public ResponseEntity<Usuario> patchUsuarioChangePW(@Validated @PathVariable("email") String email,
			@RequestBody Usuario usuariop) 
		throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {

		System.out.println("patchUsuarioChangePW");
		System.out.println("email" + email);
		
		/**
		 * El usuario original es para envarle una notificacion al mismo, antes
		 * de que hayan sido modificados los datos
		 */
		Usuario usuarioDBOriginal = usuarioService.getByEmail(email);
		
		Usuario usuario = usuarioService.patch(usuariop, email);

		// En el caso que se requiera pasar algo en el Header
		HttpHeaders headers = new HttpHeaders();

		Usuario usuarioDB = usuarioService.getByEmail(email);
		
		/**
		 * Esto es para retornar los datos del usuario ya modificados
		 */
		if (null == usuarioDB) {
			throw new EmptyResultException(Usuario.class);
		}

		/**
		 *  Enviando email de prueba En caso de fallar el email se pierde
		 *  Es importante agregar el nombre y apellido del usuario al codigo
		 */
		emailService.sendEmailChangePassword(usuarioDBOriginal.getEmail(),"Cambiaste tu password, en caso de no ser el caso por favor contactanos");

		
		return new ResponseEntity<Usuario>(usuarioDB, HttpStatus.OK);	
	}
     

	@RequestMapping(method = RequestMethod.PATCH, value = "/email/temp{email:.+}")
	public ResponseEntity<Usuario> patchUsuarioPWChanged(@Validated @PathVariable("email") String email,
			@RequestBody Usuario usuariop) throws UnsupportedEncodingException {

		System.out.println("patchUsuario");
		System.out.println("email" + email);

		Usuario usuario = usuarioService.patch(usuariop, email);

		// En el caso que se requiera pasar algo en el Header
		HttpHeaders headers = new HttpHeaders();

		Usuario usuarioDB = usuarioService.getByEmail(email);

		if (null == usuarioDB) {
			throw new EmptyResultException(Usuario.class);
		}

		// Enviandpo email de prueba
		// En caso de fallar el email se pierde
		emailService.sendEmailChangePWSuccessfully("", "", "");

		return new ResponseEntity<Usuario>(usuarioDB, HttpStatus.OK);
	}
	
    
}
