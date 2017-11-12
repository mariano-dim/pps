package edu.pps.integradorrs.controllers;

import com.google.common.base.Strings;
import edu.pps.integradorrs.services.Email.EmailServiceSocialFocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(method = RequestMethod.GET, value="/hasAccess/usuarioEmail/{usuarioEmail:.+}/puerta/{puerta}/llave/{llave}")
    public ResponseEntity<String> getUsuarioHasAccess(@Validated @PathVariable("usuarioEmail") String usuarioEmail,
                                                                  @Validated @PathVariable("puerta") String puerta,
                                                                  @Validated @PathVariable("llave") String llave) {

        System.out.println("getUsuarioHasAccess");
        System.out.println("UsuarioEmail: " + usuarioEmail);
        System.out.println("Puerta:       " + puerta);
        System.out.println("Llave:        " + llave);

        return new ResponseEntity<String>("OK", HttpStatus.OK);

    }



}


