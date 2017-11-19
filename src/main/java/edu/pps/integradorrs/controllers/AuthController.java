package edu.pps.integradorrs.controllers;

import com.google.common.base.Strings;
import edu.pps.integradorrs.services.Email.EmailServiceSocialFocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.ArrayUtils;

import edu.pps.integradorrs.model.Usuario;
import edu.pps.integradorrs.model.Llave;
import edu.pps.integradorrs.model.Puerta;

import edu.pps.integradorrs.services.usuario.UsuarioService;
import edu.pps.integradorrs.services.llave.LlaveService;
import edu.pps.integradorrs.services.puerta.PuertaService;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import edu.pps.integradorrs.exceptions.EmptyResultException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class AuthController extends AbstractController<Usuario> {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LlaveService llaveService;

	@Autowired
	private PuertaService puertaService;

	@Autowired
	public EmailServiceSocialFocus emailService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<Collection<Usuario>> getAll() {

		System.out.println("getAll");

		Collection<Usuario> usuarios = usuarioService.getAllUsuarios();

		return super.collectionResult(usuarios);

	}

	// fix
	// http://stackoverflow.com/questions/3526523/spring-mvc-pathvariable-getting-truncated
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

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

		usuarioService.save(usuario);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/email/{email:.+}")
	public ResponseEntity<Usuario> patchUsuario(@Validated @PathVariable("email") String email,
			@RequestBody Usuario usuarioData)
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
		if (!Strings.isNullOrEmpty(usuarioData.getEmail())) {

			usuarioNuevo = usuarioService.getByEmail(usuarioData.getEmail());
			if (null == usuarioNuevo) {
				throw new EmptyResultException(Usuario.class);
			}

		} else {
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

	@RequestMapping(method = RequestMethod.PATCH, value = "/addkeytouser/usuarioemail/{usuarioEmail:.+}/llavepublicIdentification/{llavepublicIdentification}")
	public ResponseEntity<Usuario> addKeyToUser(@Validated @PathVariable("usuarioEmail") String usuarioEmail,
			@Validated @PathVariable("llavepublicIdentification") String llavepublicIdentification) {

		System.out.println("addKeyToUser");
		System.out.println("usuarioEmail: " + usuarioEmail);
		System.out.println("llavepublicIdentification: " + llavepublicIdentification);

		// Busco al usuario, en caso de no encontrarlo doy error
		Usuario usuario = usuarioService.getByEmail(usuarioEmail);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == usuario) {
			System.out.println("usuario no encontrado");
			throw new EmptyResultException(Usuario.class);
		}

		// Busco la llave, a traves de su identificacion publica, en caso de no
		// encontrarla doy error
		Llave llave = llaveService.getByPublicIdentification(llavepublicIdentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llave) {
			System.out.println("llave no encontrada");
			throw new EmptyResultException(Puerta.class);
		}
		// verifico si el usuario ya tiene esa llave asignada, en cuyo caso retorno
		if (ArrayUtils.contains(usuario.getLlaves(), llavepublicIdentification)) {
			System.out.println("el usuario ya posee esa llave");
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}

		// Le agrego la llave al usuario
		usuarioService.addLlave(usuarioEmail, llavepublicIdentification);

		// Busco al usuario, para retornar la informacion que se persistio en la db
		Usuario usuarioUpdated = usuarioService.getByEmail(usuarioEmail);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == usuarioUpdated) {
			throw new EmptyResultException(Usuario.class);
		}
		return new ResponseEntity<Usuario>(usuarioUpdated, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/removekeytouser/usuarioemail/{usuarioEmail:.+}/llavepublicidentification/{llavepublicidentification}")
	public ResponseEntity<Usuario> removeKeyToUser(@Validated @PathVariable("usuarioEmail") String usuarioEmail,
			@Validated @PathVariable("llavepublicIdentification") String llavepublicIdentification) {

		System.out.println("removeKeyToUser");
		System.out.println("usuarioEmail: " + usuarioEmail);
		System.out.println("llavepublicIdentification: " + llavepublicIdentification);

		// Busco al usuario, en caso de no encontrarlo doy error
		Usuario usuario = usuarioService.getByEmail(usuarioEmail);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == usuario) {
			System.out.println("usuario no encontrado");
			throw new EmptyResultException(Usuario.class);
		}

		// Busco la llave, a traves de su identificacion publica, en caso de no
		// encontrarla doy error
		Llave llave = llaveService.getByPublicIdentification(llavepublicIdentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llave) {
			System.out.println("llave no encontrada");
			throw new EmptyResultException(Puerta.class);
		}

		// verifico si el usuario tiene esa llave asignada, en cuyo caso retorno
		if (!ArrayUtils.contains(usuario.getLlaves(), llavepublicIdentification)) {
			System.out.println("el usuario no posee esa llave");
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}

		// Le borro la llave al usuario
		usuarioService.removeLlave(usuarioEmail, llavepublicIdentification);

		// Busco al usuario, para retornar la informacion que se persistio en la db
		Usuario usuarioUpdated = usuarioService.getByEmail(usuarioEmail);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == usuarioUpdated) {
			throw new EmptyResultException(Usuario.class);
		}
		return new ResponseEntity<Usuario>(usuarioUpdated, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/hasaccess/usuarioemail/{usuarioemail:.+}/puertapublicidentification/{puertapublicidentification}/llavepublicidentification/{llavepublicidentification}")
	public ResponseEntity<String> getUsuarioHasAccess(@Validated @PathVariable("usuarioemail") String usuarioEmail,
			@Validated @PathVariable("puertapublicidentification") String puertaPublicIdentification, 
			@Validated @PathVariable("llavepublicidentification") String llavePublicIdentification) {

		System.out.println("getUsuarioHasAccess");
		System.out.println("usuarioemail: " + usuarioEmail);
		System.out.println("llavepublicidentification:       " + llavePublicIdentification);
		System.out.println("puertapublicidentification:      " + puertaPublicIdentification);
		
		
		// Busco al usuario, en caso de no encontrarlo retorno error
		Usuario usuario = usuarioService.getByEmail(usuarioEmail);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == usuario) {
			System.out.println("Usuario no encontrado");
			return new ResponseEntity<String>("ERROR", HttpStatus.OK);
		}
		
		// Busco la llave, a traves de su identificacion publica, en caso de no
		// encontrarla retorno error
		Llave llave = llaveService.getByPublicIdentification(llavePublicIdentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llave) {
			System.out.println("Llave no encontrada");
			return new ResponseEntity<String>("ERROR", HttpStatus.OK);
		}
		
		// Busco la puerta, a traves de su identificacion publica, en caso de no
		// encontrarla retorno error
        Puerta puerta = puertaService.getByPublicIdentification(puertaPublicIdentification);
        if (null == puerta) {
			System.out.println("Puerta no encontrada");
        	return new ResponseEntity<String>("ERROR", HttpStatus.OK);
        }
		// Verifico si el usuario tiene esa llave asignada, en cuyo caso retorno error
		if (!ArrayUtils.contains(usuario.getLlaves(), llavePublicIdentification)) {
			System.out.println("El usuario no posee esa llave");
			return new ResponseEntity<String>("ERROR", HttpStatus.OK);
		}        
		// Verifico si la llave  tiene esa puerta asignada, en cuyo caso retorno error
		if (!ArrayUtils.contains(llave.getPuertas(), puertaPublicIdentification)) {
			System.out.println("La llave indicada no posee esa puerta");
			return new ResponseEntity<String>("ERROR", HttpStatus.OK);
		}        


		return new ResponseEntity<String>("OK", HttpStatus.OK);

	}

}
