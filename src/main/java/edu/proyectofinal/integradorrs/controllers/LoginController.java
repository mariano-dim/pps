package edu.proyectofinal.integradorrs.controllers;

import com.google.common.base.Strings;
import edu.proyectofinal.integradorrs.model.TokenEmail;
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
import edu.proyectofinal.integradorrs.exceptions.InvalidTokenException;
import edu.proyectofinal.integradorrs.model.Token;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
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
     
     
     
     
	@RequestMapping(method = RequestMethod.POST,
			        value = "/email/{email:.+}/token/{token}")
	public ResponseEntity<Usuario> patchUsuarioChangePW(@Validated @PathVariable("email") String email,
                                                        @Validated @PathVariable("token") String token,
                                                        @RequestBody Usuario usuariop
        ) 
		throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {

		System.out.println("patchUsuarioChangePW");
		System.out.println("email" + email);
		
		/**
		 * El usuario original es para enviarle una notificacion al mismo, antes
		 * de que hayan sido modificados los datos
		 */
		Usuario usuarioDBOriginal = usuarioService.getByEmail(email);

		// Antes de actualizar los datos del usuario verifico que se trate de un token valido
        // Es decir que no este expirado y que exista
        TokenEmail tokenEmail  = usuarioService.getByEmailAndToken(email, token);
        if (null == tokenEmail) {
            throw new InvalidTokenException(Usuario.class);
        }
        // Verifico que el token no se encuentre expirado
        if(usuarioService.isExpirateToken(tokenEmail)){
            throw new InvalidTokenException(Usuario.class);
        }

		usuarioService.patch(usuariop, email);

		// En el caso que se requiera pasar algo en el Header
		HttpHeaders headers = new HttpHeaders();

		Usuario usuarioDB = usuarioService.getByEmail(email);
		
		/**
		 * Esto es para retornar los datos del usuario ya modificados
		 */
		if (null == usuarioDB) {
			throw new EmptyResultException(Usuario.class);
		}

		// Elimino el Token, ya que el mismo fue utilizado
        usuarioService.deleteEmailToken(tokenEmail);


		/**
		 *  Enviando email de prueba En caso de fallar el email se pierde
		 *  Es importante agregar el nombre y apellido del usuario al codigo
		 */
		emailService.sendEmailChangePassword(usuarioDBOriginal.getEmail(),
                                             "");

		return new ResponseEntity<Usuario>(usuarioDB, HttpStatus.OK);	
	}
     

	@RequestMapping(method = RequestMethod.POST, value = "/token/email/{email:.+}")
	public ResponseEntity<TokenEmail> generateTokenChangePassword(@Validated @PathVariable("email") String email)
            throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException{

		System.out.println("generateTokenChangePassword");
		System.out.println("email" + email);

		Usuario usuario =  usuarioService.getByEmail(email);
		// Si no se encuentra el usuario a traves de su email
        // Retorno error para que el front end pueda determinarlo y mostrar el mensaje correspondiente
        if (null == usuario) {
			throw new EmptyResultException(Usuario.class);
		}

        // Verifico que el usuario no tenga un token vivo, en cuyo caso lo debo desabilitar y generar uno nuevo
        TokenEmail tokenEmailEnabled  = usuarioService.getByEmailAndExpired(email);
        if (! (null == tokenEmailEnabled)) {
            // desabilito el Token anterior, asumo que como mucho existe un solo token habilitado, ya
            // que cada vez que genero uno nuevo para el mismo usuario, desabilito, si hay uno, en anterior
            usuarioService.deletePrevToken(tokenEmailEnabled);
        }

        // Envio email con link + token para el cambio
        TokenEmail tokenEmail = usuarioService.recoveryPasswordGenerateToken(email);

        // En el caso que se requiera pasar algo en el Header
        HttpHeaders headers = new HttpHeaders();

        // Envio email con el link
        emailService.sendEmailLinkTokenGenerate(tokenEmail.getTokenEmail(),
                                                "",
                                                tokenEmail.getToken());

        return new ResponseEntity<TokenEmail>(tokenEmail, HttpStatus.OK);
	}




    @RequestMapping(method = RequestMethod.POST,
            value = "/email/{email:.+}")
    public ResponseEntity<Usuario> patchUsuario(@Validated @PathVariable("email") String email,
                                                        @RequestBody Usuario usuarioData )
            throws UnsupportedEncodingException, CannotSendEmailException, URISyntaxException {

        System.out.println("patchUsuario");
        System.out.println("email" + email);

        Usuario usuarioToUpdate = usuarioService.getByEmail(email);
        if (null == usuarioToUpdate) {
            throw new EmptyResultException(Usuario.class);
        }

        System.out.println("UsuarioViejo" + usuarioToUpdate.toString());

        usuarioService.patch(usuarioData, email);

        Usuario usuarioNuevo;
        if(!Strings.isNullOrEmpty(usuarioData.getEmail())){

            usuarioNuevo = usuarioService.getByEmail(usuarioData.getEmail());
            if (null == usuarioNuevo) {
                throw new EmptyResultException(Usuario.class);
            }

        } else{
            usuarioNuevo = usuarioService.getByEmail(email);
            if (null == usuarioNuevo) {
                throw new EmptyResultException(Usuario.class);
            }
        }

        System.out.println("UsuarioNuevo" + usuarioNuevo.toString());

        // En el caso que se requiera pasar algo en el Header
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Usuario>(usuarioNuevo, HttpStatus.OK);
    }



}


